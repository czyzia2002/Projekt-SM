package pl.edu.pb.restauracja.database;

import android.content.Context;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private static MenuRepository instance;
    private List<MenuItem> menuItems;

    private MenuRepository(Context context) {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(1, "Rosol", "zupa", 9.99));
        menuItems.add(new MenuItem(2, "Bigos", "danie główne", 18.50));
        menuItems.add(new MenuItem(3, "Pierogi ruskie", "danie główne", 15.99));
        menuItems.add(new MenuItem(4, "Żurek", "zupa", 10.99));
        menuItems.add(new MenuItem(5, "Kotlet schabowy", "danie główne", 22.75));
        menuItems.add(new MenuItem(6, "Sernik", "deser", 14.99));
        menuItems.add(new MenuItem(7, "Kiełbasa grillowana", "danie główne", 16.80));
        menuItems.add(new MenuItem(8, "Placki ziemniaczane", "danie główne", 12.25));
        menuItems.add(new MenuItem(9, "Barszcz czerwony", "zupa", 11.50));
        menuItems.add(new MenuItem(10, "Pierogi z mięsem", "danie główne", 17.25));
        menuItems.add(new MenuItem(11, "Kapuśniak", "zupa", 10.99));
        menuItems.add(new MenuItem(12, "Gołąbki", "danie główne", 19.99));
        menuItems.add(new MenuItem(13, "Kompot owocowy", "napój", 5.99));
        menuItems.add(new MenuItem(14, "Kaszanka", "danie główne", 14.50));
        menuItems.add(new MenuItem(15, "Kopytka", "danie główne", 13.25));
        menuItems.add(new MenuItem(16, "Ziemniaki puree", "danie główne", 11.99));
        menuItems.add(new MenuItem(17, "Kapusta z grochem", "danie główne", 16.25));
        menuItems.add(new MenuItem(18, "Mizeria", "sałatka", 8.75));
        menuItems.add(new MenuItem(19, "Kisiel", "deser", 6.99));
        menuItems.add(new MenuItem(20, "Pieczeń rzymska", "danie główne", 24.50));
        menuItems.add(new MenuItem(21, "Racuchy", "deser", 9.99));
        menuItems.add(new MenuItem(22, "Zrazy wieprzowe", "danie główne", 20.75));
        menuItems.add(new MenuItem(23, "Leczo", "danie główne", 18.99));
        menuItems.add(new MenuItem(24, "Tort makowy", "deser", 16.50));
        menuItems.add(new MenuItem(25, "Ryba po grecku", "danie główne", 22.25));
        menuItems.add(new MenuItem(26, "Zupa ogórkowa", "zupa", 12.99));
        menuItems.add(new MenuItem(27, "Placek po wegiersku", "danie główne", 14.75));
        menuItems.add(new MenuItem(28, "Zupa pomidorowa", "zupa", 11.50));
        menuItems.add(new MenuItem(29, "Paszteciki z mięsem", "danie główne", 15.99));
        menuItems.add(new MenuItem(30, "Jabłecznik", "deser", 13.25));
    }

    public static synchronized MenuRepository getInstance(Context context) {
        if (instance == null) {
            instance = new MenuRepository(context);
        }
        return instance;
    }

    public List<MenuItem> getRestaurants() {
        return menuItems;
    }
}
