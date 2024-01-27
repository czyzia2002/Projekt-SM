package pl.edu.pb.restauracja;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pb.restauracja.database.Ingredient;
import pl.edu.pb.restauracja.database.MenuItem;
import pl.edu.pb.restauracja.database.MenuRepository;

public class MenuDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_details);

        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        double price = intent.getDoubleExtra("price", 0.0);

        TextView dishNameTextView = findViewById(R.id.textViewDishName);
        TextView dishPriceTextView = findViewById(R.id.textViewDishPrice);
        ListView listViewIngredients = findViewById(R.id.listViewIngredients);

        dishNameTextView.setText(itemName);
        dishPriceTextView.setText("Price: " + String.valueOf(price));

        List<MenuItem> menuItems = MenuRepository.getInstance().getMenuItems();
        MenuItem selectedItem = null;
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getItemName().equals(itemName)) {
                selectedItem = menuItem;
                break;
            }
        }

        // Display the list of ingredients in the ListView
        if (selectedItem != null) {
            String[] ingredients = selectedItem.getIngredients2();
            List<String> ingredientNames = new ArrayList<>();
            for (String ingredient : ingredients) {
                ingredientNames.add(ingredient);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ingredientNames);
            listViewIngredients.setAdapter(adapter);
        }

    }
}
