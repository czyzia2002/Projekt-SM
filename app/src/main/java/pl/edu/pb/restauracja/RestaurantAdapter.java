package pl.edu.pb.restauracja;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import pl.edu.pb.restauracja.database.Restaurant;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private List<Restaurant> restaurants;
    private Restaurant nearestRestaurant;
    private Location userLocation;
    private Context context;


    public RestaurantAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
    public void setNearestRestaurant(Restaurant nearestRestaurant, Location userLocation) {
        this.nearestRestaurant = nearestRestaurant;
        this.userLocation = userLocation;
        notifyDataSetChanged();  // Informuje adapter o zmianie danych
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);

        // Ustawianie informacji o restauracji w widoku

        // Sprawdzanie, czy restauracja jest najbliższą
        if (nearestRestaurant != null && restaurant == nearestRestaurant) {
            // Oznacz restaurację jako najbliższą (zmień kolor tła itp.)
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.nearestRestaurantColor));
            holder.headerTextView.setVisibility(View.VISIBLE);
        } else {
            // Resetuj oznaczenie (zmień kolor tła na domyślny)
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
            holder.headerTextView.setVisibility(View.GONE);
        }
        holder.nameTextView.setText(restaurant.getName());
        holder.addressTextView.setText(restaurant.getCity() + ", " + restaurant.getStreet() + " " + restaurant.getNumber());
        holder.distanceTextView.setText(calculateDistance(restaurant));
    }


    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView addressTextView;
        TextView headerTextView;
        TextView distanceTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            addressTextView = itemView.findViewById(R.id.textViewAddress);
            headerTextView = itemView.findViewById(R.id.textViewHeader);
            distanceTextView = itemView.findViewById(R.id.textViewDistance);
        }
    }
    private String calculateDistance(Restaurant restaurant) {
        if (userLocation != null) {
            double restaurantLatitude = restaurant.getLatitude();
            double restaurantLongitude = restaurant.getLongitude();

            Location restaurantLocation = new Location("restaurant");
            restaurantLocation.setLatitude(restaurantLatitude);
            restaurantLocation.setLongitude(restaurantLongitude);

            float distanceInMeters = userLocation.distanceTo(restaurantLocation);
            double distanceInKilometers = distanceInMeters / 1000.0;

            String distancePrefix = context.getString(R.string.distance);
            return String.format("%s %.2f km", distancePrefix, distanceInKilometers);
        } else {
            return ""; // Lub inny tekst w przypadku braku lokalizacji użytkownika
        }
    }

}
