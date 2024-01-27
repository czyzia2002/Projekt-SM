package pl.edu.pb.restauracja.database;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private static MenuRepository instance;
    private List<MenuItem> menuItems;

    private MenuRepository() {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(1, "Rosół", "zupa", 9.99));
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
        menuItems.add(new MenuItem(13, "Kaszanka", "danie główne", 14.50));
        menuItems.add(new MenuItem(14, "Kopytka", "danie główne", 13.25));
        menuItems.add(new MenuItem(15, "Ziemniaki puree", "danie główne", 11.99));
        menuItems.add(new MenuItem(16, "Kapusta z grochem", "danie główne", 16.25));
        menuItems.add(new MenuItem(17, "Kisiel", "deser", 6.99));
        menuItems.add(new MenuItem(18, "Pieczeń rzymska", "danie główne", 24.50));
        menuItems.add(new MenuItem(19, "Racuchy", "deser", 9.99));
        menuItems.add(new MenuItem(20, "Zrazy wieprzowe", "danie główne", 20.75));
        menuItems.add(new MenuItem(21, "Leczo", "danie główne", 18.99));
        menuItems.add(new MenuItem(22, "Tort makowy", "deser", 16.50));
        menuItems.add(new MenuItem(23, "Ryba po grecku", "danie główne", 22.25));
        menuItems.add(new MenuItem(24, "Zupa ogórkowa", "zupa", 12.99));
        menuItems.add(new MenuItem(25, "Placek po wegiersku", "danie główne", 14.75));
        menuItems.add(new MenuItem(26, "Zupa pomidorowa", "zupa", 11.50));
        menuItems.add(new MenuItem(27, "Paszteciki z mięsem", "danie główne", 15.99));
        menuItems.add(new MenuItem(28, "Jabłecznik", "deser", 13.25));

    }

    public static synchronized MenuRepository getInstance() {
        if (instance == null) {
            instance = new MenuRepository();
        }
        return instance;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
}
