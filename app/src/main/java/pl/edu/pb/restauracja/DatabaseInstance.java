package pl.edu.pb.restauracja;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import pl.edu.pb.restauracja.database.AppDatabase;
import pl.edu.pb.restauracja.database.Restaurant;
import pl.edu.pb.restauracja.database.RestaurantDao;

public class DatabaseInstance {
    private static AppDatabase appDatabase = null;


    private DatabaseInstance() {
    }

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) throw new RuntimeException("Not initialized DATABASE");
        return appDatabase;
    }

    public static void Initialize(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "restaurant_database").fallbackToDestructiveMigration().addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        // Handle onCreate
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        // Handle onOpen
                    }
                }).allowMainThreadQueries() // For demonstration purposes only; use AsyncTask or background thread for database operations
                .build();
    }
    public static void insertInitialData(RestaurantDao restaurantDao, SupportSQLiteDatabase db) {
        // Dodawanie początkowych restauracji do bazy danych
        restaurantDao.insertRestaurant(new Restaurant("Zwierzyniec", "Białystok", "Zwierzniecka", "6", 53.11966936655787, 23.150193796527187));
        restaurantDao.insertRestaurant(new Restaurant("Obok Rynku", "Bielsk Podlaski", "Mickiewicza", "49", 52.77197652449937, 23.193009153588086));
        restaurantDao.insertRestaurant(new Restaurant("Centrum", "Białystok", "Sienkiewicza", "3", 53.13281011504806, 23.160434781185547));
        restaurantDao.insertRestaurant(new Restaurant("Zielone Wzgórza", "Białystok", "Wrocławska", "45", 53.12446534452306, 23.095771011870312));
    }


}
