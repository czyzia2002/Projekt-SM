package pl.edu.pb.restauracja;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;

import pl.edu.pb.restauracja.database.AppDatabase;
import pl.edu.pb.restauracja.database.MenuDao;
import pl.edu.pb.restauracja.database.MenuItem;
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
                        insertInitialData(appDatabase.restaurantDao());
                        insertInitialData2(appDatabase.menuDao());
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
    public static void insertInitialData(RestaurantDao restaurantDao) {
        /*for(Restaurant restaurant: restaurantDao.getAllRestaurants()) {
            restaurantDao.deleteRestaurant(restaurant.getId());
        }
        restaurantDao.insertRestaurant(new Restaurant("Zwierzyniec", "Białystok", "Zwierzniecka", "6", 53.11966936655787, 23.150193796527187));
        restaurantDao.insertRestaurant(new Restaurant("Obok Rynku", "Bielsk Podlaski", "Mickiewicza", "49", 52.77197652449937, 23.193009153588086));
        restaurantDao.insertRestaurant(new Restaurant("Centrum", "Białystok", "Sienkiewicza", "3", 53.13281011504806, 23.160434781185547));
        restaurantDao.insertRestaurant(new Restaurant("Zielone Wzgórza", "Białystok", "Wrocławska", "45", 53.12446534452306, 23.095771011870312));
    */
    }

    public static void insertInitialData2(MenuDao menuDao) {
        for(MenuItem menuItem: menuDao.getAllMenuItems()) {
            menuDao.deleteMenuItem(menuItem.getId());
        }
        menuDao.insertMenuItem(new MenuItem(1, "Rosół", "zupa", 9.99, new String[]{"kurczak", "marchewka", "pietruszka"}));
        menuDao.insertMenuItem(new MenuItem(2, "Bigos", "danie główne", 18.50, new String[]{"kapusta", "kiełbasa", "grzyby"}));
        menuDao.insertMenuItem(new MenuItem(3, "Pierogi ruskie", "danie główne", 15.99, new String[]{"ziemniaki", "ser", "cebula"}));
        menuDao.insertMenuItem(new MenuItem(4, "Żurek", "zupa", 10.99, new String[]{"kiełbasa", "jajko", "chleb"}));
        menuDao.insertMenuItem(new MenuItem(5, "Kotlet schabowy", "danie główne", 22.75, new String[]{"schab", "jajko", "bułka tarta"}));
        menuDao.insertMenuItem(new MenuItem(6, "Sernik", "deser", 14.99, new String[]{"ser", "jajko", "cukier"}));
        menuDao.insertMenuItem(new MenuItem(7, "Kiełbasa grillowana", "danie główne", 16.80, new String[]{"kiełbasa", "musztarda", "chleb"}));
        menuDao.insertMenuItem(new MenuItem(8, "Placki ziemniaczane", "danie główne", 12.25, new String[]{"ziemniaki", "cebula", "jajko"}));
        menuDao.insertMenuItem(new MenuItem(9, "Barszcz czerwony", "zupa", 11.50, new String[]{"burak", "żurawina", "kiełbasa"}));
        menuDao.insertMenuItem(new MenuItem(10, "Pierogi z mięsem", "danie główne", 17.25, new String[]{"mięso mielone", "cebula", "jajko"}));
        menuDao.insertMenuItem(new MenuItem(11, "Kapuśniak", "zupa", 10.99, new String[]{"kapusta", "kiszone ogórki", "kielbasa"}));
        menuDao.insertMenuItem(new MenuItem(12, "Gołąbki", "danie główne", 19.99, new String[]{"mięso mielone", "ryż", "kapusta"}));
        menuDao.insertMenuItem(new MenuItem(13, "Kaszanka", "danie główne", 14.50, new String[]{"krew", "kasza", "cebula"}));
        menuDao.insertMenuItem(new MenuItem(14, "Kopytka", "danie główne", 13.25, new String[]{"ziemniaki", "mąka", "jajko"}));
        menuDao.insertMenuItem(new MenuItem(15, "Ziemniaki puree", "danie główne", 11.99, new String[]{"ziemniaki", "masło", "mleko"}));
        menuDao.insertMenuItem(new MenuItem(16, "Kapusta z grochem", "danie główne", 16.25, new String[]{"kapusta", "groch", "kielbasa"}));
        menuDao.insertMenuItem(new MenuItem(17, "Kisiel", "deser", 6.99, new String[]{"cukier", "kisiel", "woda"}));
        menuDao.insertMenuItem(new MenuItem(18, "Pieczeń rzymska", "danie główne", 24.50, new String[]{"mięso", "przyprawy", "warzywa"}));
        menuDao.insertMenuItem(new MenuItem(19, "Racuchy", "deser", 9.99, new String[]{"jabłka", "cukier", "mąka"}));
        menuDao.insertMenuItem(new MenuItem(20, "Zrazy wieprzowe", "danie główne", 20.75, new String[]{"mięso", "cebula", "przyprawy"}));
        menuDao.insertMenuItem(new MenuItem(21, "Leczo", "danie główne", 18.99, new String[]{"papryka", "ziemniaki", "cebula"}));
        menuDao.insertMenuItem(new MenuItem(22, "Tort makowy", "deser", 16.50, new String[]{"mak", "cukier", "jajko"}));
        menuDao.insertMenuItem(new MenuItem(23, "Ryba po grecku", "danie główne", 22.25, new String[]{"ryba", "marchewka", "por"}));
        menuDao.insertMenuItem(new MenuItem(24, "Zupa ogórkowa", "zupa", 12.99, new String[]{"ogórek", "ziemniaki", "koper"}));
        menuDao.insertMenuItem(new MenuItem(25, "Placek po wegiersku", "danie główne", 14.75, new String[]{"ziemniaki", "cebula", "papryka"}));
        menuDao.insertMenuItem(new MenuItem(26, "Zupa pomidorowa", "zupa", 11.50, new String[]{"pomidory", "cebula", "bazylia"}));
        menuDao.insertMenuItem(new MenuItem(27, "Paszteciki z mięsem", "danie główne", 15.99, new String[]{"mięso", "cebula", "ciasto"}));
        menuDao.insertMenuItem(new MenuItem(28, "Jabłecznik", "deser", 13.25, new String[]{"jabłka", "cynamon", "ciasto"}));
    }



}
