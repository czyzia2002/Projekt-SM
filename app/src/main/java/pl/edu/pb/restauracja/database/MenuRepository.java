package pl.edu.pb.restauracja.database;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {

    private static MenuRepository instance;
    private List<MenuItem> menuItems;

    private MenuRepository() {
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
