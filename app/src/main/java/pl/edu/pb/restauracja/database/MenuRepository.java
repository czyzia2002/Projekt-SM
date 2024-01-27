package pl.edu.pb.restauracja.database;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private static MenuRepository instance;
    private List<MenuItem> menuItems;

    private MenuRepository() {
        menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(1, "Rosół", "zupa", 9.99, new String[]{"kurczak", "marchewka", "pietruszka"}));
        menuItems.add(new MenuItem(2, "Bigos", "danie główne", 18.50, new String[]{"kapusta", "kiełbasa", "grzyby"}));
        menuItems.add(new MenuItem(3, "Pierogi ruskie", "danie główne", 15.99, new String[]{"ziemniaki", "ser", "cebula"}));
        menuItems.add(new MenuItem(4, "Żurek", "zupa", 10.99, new String[]{"kiełbasa", "jajko", "chleb"}));
        menuItems.add(new MenuItem(5, "Kotlet schabowy", "danie główne", 22.75, new String[]{"schab", "jajko", "bułka tarta"}));
        menuItems.add(new MenuItem(6, "Sernik", "deser", 14.99, new String[]{"ser", "jajko", "cukier"}));
        menuItems.add(new MenuItem(7, "Kiełbasa grillowana", "danie główne", 16.80, new String[]{"kiełbasa", "musztarda", "chleb"}));
        menuItems.add(new MenuItem(8, "Placki ziemniaczane", "danie główne", 12.25, new String[]{"ziemniaki", "cebula", "jajko"}));
        menuItems.add(new MenuItem(9, "Barszcz czerwony", "zupa", 11.50, new String[]{"burak", "żurawina", "kiełbasa"}));
        menuItems.add(new MenuItem(10, "Pierogi z mięsem", "danie główne", 17.25, new String[]{"mięso mielone", "cebula", "jajko"}));
        menuItems.add(new MenuItem(11, "Kapuśniak", "zupa", 10.99, new String[]{"kapusta", "kiszone ogórki", "kielbasa"}));
        menuItems.add(new MenuItem(12, "Gołąbki", "danie główne", 19.99, new String[]{"mięso mielone", "ryż", "kapusta"}));
        menuItems.add(new MenuItem(13, "Kaszanka", "danie główne", 14.50, new String[]{"krew", "kasza", "cebula"}));
        menuItems.add(new MenuItem(14, "Kopytka", "danie główne", 13.25, new String[]{"ziemniaki", "mąka", "jajko"}));
        menuItems.add(new MenuItem(15, "Ziemniaki puree", "danie główne", 11.99, new String[]{"ziemniaki", "masło", "mleko"}));
        menuItems.add(new MenuItem(16, "Kapusta z grochem", "danie główne", 16.25, new String[]{"kapusta", "groch", "kielbasa"}));
        menuItems.add(new MenuItem(17, "Kisiel", "deser", 6.99, new String[]{"cukier", "kisiel", "woda"}));
        menuItems.add(new MenuItem(18, "Pieczeń rzymska", "danie główne", 24.50, new String[]{"mięso", "przyprawy", "warzywa"}));
        menuItems.add(new MenuItem(19, "Racuchy", "deser", 9.99, new String[]{"jabłka", "cukier", "mąka"}));
        menuItems.add(new MenuItem(20, "Zrazy wieprzowe", "danie główne", 20.75, new String[]{"mięso", "cebula", "przyprawy"}));
        menuItems.add(new MenuItem(21, "Leczo", "danie główne", 18.99, new String[]{"papryka", "ziemniaki", "cebula"}));
        menuItems.add(new MenuItem(22, "Tort makowy", "deser", 16.50, new String[]{"mak", "cukier", "jajko"}));
        menuItems.add(new MenuItem(23, "Ryba po grecku", "danie główne", 22.25, new String[]{"ryba", "marchewka", "por"}));
        menuItems.add(new MenuItem(24, "Zupa ogórkowa", "zupa", 12.99, new String[]{"ogórek", "ziemniaki", "koper"}));
        menuItems.add(new MenuItem(25, "Placek po wegiersku", "danie główne", 14.75, new String[]{"ziemniaki", "cebula", "papryka"}));
        menuItems.add(new MenuItem(26, "Zupa pomidorowa", "zupa", 11.50, new String[]{"pomidory", "cebula", "bazylia"}));
        menuItems.add(new MenuItem(27, "Paszteciki z mięsem", "danie główne", 15.99, new String[]{"mięso", "cebula", "ciasto"}));
        menuItems.add(new MenuItem(28, "Jabłecznik", "deser", 13.25, new String[]{"jabłka", "cynamon", "ciasto"}));

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
