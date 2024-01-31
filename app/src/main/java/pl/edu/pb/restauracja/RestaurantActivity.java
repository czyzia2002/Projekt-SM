package pl.edu.pb.restauracja;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.edu.pb.restauracja.database.AppDatabase;
import pl.edu.pb.restauracja.database.Restaurant;
import pl.edu.pb.restauracja.database.RestaurantRepository;

public class RestaurantActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private AppDatabase appDatabase;
    private LocationManager locationManager;
    private Location lastKnownLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        // Inicjalizacja bazy danych
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "restaurant-database").build();



        // Inicjalizacja lokalizacji
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Sprawdzenie uprawnień
        checkLocationPermission();
        // Przykładowe użycie - pobranie i wyświetlenie restauracji
        displayRestaurants();
    }

    private void displayRestaurants() {
        // Pobierz restauracje z RestaurantRepository
        List<Restaurant> restaurants = RestaurantRepository.getInstance().getRestaurants();

        // Pobierz aktualną lokalizację
        double userLatitude = 0.0;
        double userLongitude = 0.0;

        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                // Dodaj sprawdzenie, czy locationManager nie jest null
                if (locationManager != null) {
                    lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if (lastKnownLocation != null) {
                        userLatitude = lastKnownLocation.getLatitude();
                        userLongitude = lastKnownLocation.getLongitude();
                    }
                } else {
                    // Jeśli locationManager jest null, obsłuż tę sytuację
                    Log.e("Location Error", "locationManager is null");
                    return;
                }
            } else {
                // Brak uprawnień - obsłuż tę sytuację (np. poproś ponownie o uprawnienia)
                // Alternatywnie, możesz ustawić tę wartość na coś, co wskazuje na błąd.
                Log.e("Location Error", "Location permission not granted");
                return;
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            // Obsłuż wyjątek SecurityException
            // Alternatywnie, możesz ustawić tę wartość na coś, co wskazuje na błąd.
            Log.e("Location Error", "SecurityException: " + e.getMessage());
            return;
        }


        // Znajdź najbliższą restaurację
        Restaurant nearestRestaurant = findNearestRestaurant(userLatitude, userLongitude, restaurants);

        // Sortuj restauracje według odległości do użytkownika
        Collections.sort(restaurants, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant restaurant1, Restaurant restaurant2) {
                Location location1 = new Location("restaurant1");
                location1.setLatitude(restaurant1.getLatitude());
                location1.setLongitude(restaurant1.getLongitude());

                Location location2 = new Location("restaurant2");
                location2.setLatitude(restaurant2.getLatitude());
                location2.setLongitude(restaurant2.getLongitude());

                float distance1, distance2;

                // Sprawdź uprawnienia przed użyciem lastKnownLocation
                try {
                    if (ContextCompat.checkSelfPermission(RestaurantActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        Location currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (currentLocation != null) {
                            distance1 = (float) currentLocation.distanceTo(location1);
                            distance2 = (float) currentLocation.distanceTo(location2);
                        } else {
                            // Obsłuż przypadek, gdy currentLocation jest null
                            Log.e("Location Error", "Aktualna lokalizacja jest null");
                            return 0;
                        }
                    } else {
                        // Brak uprawnień - obsłuż tę sytuację (np. poproś ponownie o uprawnienia)
                        // Alternatywnie, możesz ustawić tę wartość na coś, co wskazuje na błąd.
                        return 0;
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                    // Obsłuż wyjątek SecurityException
                    // Alternatywnie, możesz ustawić tę wartość na coś, co wskazuje na błąd.
                    return 0;
                }

                return Float.compare(distance1, distance2);
            }
        });

        // Przekazanie wyników do wątku interfejsu użytkownika (UI thread)
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // RecyclerView
                RecyclerView recyclerView = findViewById(R.id.recyclerViewRestaurants);
                LinearLayoutManager layoutManager = new LinearLayoutManager(RestaurantActivity.this);
                recyclerView.setLayoutManager(layoutManager);

                // Adapter
                RestaurantAdapter adapter = new RestaurantAdapter(restaurants);

                // Oznacz najbliższą restaurację specjalnym kolorem
                if (nearestRestaurant != null) {
                    Log.d("RestaurantData", "Restaurants size: " + restaurants.size());

                    adapter.setNearestRestaurant(nearestRestaurant, lastKnownLocation);
                }
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        });
    }

    private void checkLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_LOCATION_PERMISSION);
        } else {
            // Permissions granted, proceed with location-related functionality
            checkLocationEnabled();
        }
    }

    private void checkLocationEnabled() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Lokalizacja jest wyłączona, poproś o włączenie
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        } else {
            // Lokalizacja jest włączona, możesz zacząć korzystać z GPS
            getCurrentLocation();  // Dodane od razu po sprawdzeniu dostępności lokalizacji
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissions granted
                checkLocationEnabled();
            } else {
                // Permissions not granted
                Toast.makeText(this, "Location permissions denied.", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null) {
                double userLatitude = lastKnownLocation.getLatitude();
                double userLongitude = lastKnownLocation.getLongitude();

                // Pobierz listę restauracji z repozytorium
                List<Restaurant> restaurants = RestaurantRepository.getInstance().getRestaurants();

                // Znajdź najbliższą restaurację
                Restaurant nearestRestaurant = findNearestRestaurant(userLatitude, userLongitude, restaurants);

                if (nearestRestaurant != null) {
                    // Tutaj możesz użyć danych najbliższej restauracji (np. wyświetlić na mapie)
                    Log.d("Nearest Restaurant", nearestRestaurant.getName());
                }
                // Log aktualnej lokalizacji
                Log.d("Current Location", "Latitude: " + userLatitude + ", Longitude: " + userLongitude);
            } else {
                // Jeżeli brak dostępnej lokalizacji, możesz poprosić o jej aktualizację
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

    private Restaurant findNearestRestaurant(double userLatitude, double userLongitude, List<Restaurant> restaurants) {
        Location userLocation = new Location("user");
        userLocation.setLatitude(userLatitude);
        userLocation.setLongitude(userLongitude);

        Restaurant nearestRestaurant = null;
        float minDistance = Float.MAX_VALUE;

        for (Restaurant restaurant : restaurants) {
            Location restaurantLocation = new Location("restaurant");
            restaurantLocation.setLatitude(restaurant.getLatitude());
            restaurantLocation.setLongitude(restaurant.getLongitude());

            float distance = userLocation.distanceTo(restaurantLocation);

            if (distance < minDistance) {
                minDistance = distance;
                nearestRestaurant = restaurant;
            }
        }

        return nearestRestaurant;
    }

    private final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            // Aktualizacja lokalizacji
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            // Tutaj możesz użyć współrzędnych do zaznaczenia najbliższej restauracji
            Log.d("Location Update", "Latitude: " + latitude + ", Longitude: " + longitude);
            displayRestaurants();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }
    };
}
