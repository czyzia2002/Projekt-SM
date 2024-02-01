package pl.edu.pb.restauracja;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pb.restauracja.database.AppDatabase;
import pl.edu.pb.restauracja.database.MenuDao;
import pl.edu.pb.restauracja.database.MenuItem;
import pl.edu.pb.restauracja.database.MenuRepository;

public class MenuDetails extends AppCompatActivity implements GoogleSearchTask.OnSearchCompleteListener{
    private SearchResultAdapter searchResultAdapter;
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

        String priceText = getString(R.string.price) + String.valueOf(price);
        dishPriceTextView.setText(priceText);


        AppDatabase appDatabase = DatabaseInstance.getInstance(this);

        // Uzyskaj instancję interfejsu dostępu do danych (DAO)
        MenuDao menuDao = appDatabase.menuDao();

        DatabaseInstance.insertInitialData2(menuDao);

        // Pobierz listę restauracji z bazy danych
        List<MenuItem> menuItems = menuDao.getAllMenuItems();

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

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSearchResults);
        searchResultAdapter = new SearchResultAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(searchResultAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button searchGoogleButton = findViewById(R.id.buttonSearchGoogle);
        Animation fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        searchGoogleButton.startAnimation(fadeIn);
        searchGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performGoogleSearch();
            }
        });

    }
    private void performGoogleSearch() {
        TextView dishNameTextView = findViewById(R.id.textViewDishName);
        String searchQuery = dishNameTextView.getText().toString().trim();

        // Klucz API i identyfikator wyszukiwarki
        String apiKey = "AIzaSyBRabnj4nUsqFYtxENpUC83DQOjBliuA8Y";
        String searchEngineId = "92bbbac30278e49bc";

        Log.d("GoogleSearchTask", "Performing Google Search for: " + searchQuery);

        GoogleSearchTask googleSearchTask = new GoogleSearchTask(searchQuery, apiKey, searchEngineId, this, this, this);
        googleSearchTask.execute();
    }

    @Override
    public void onSearchComplete(List<SearchResult> searchResults) {
        // Update the RecyclerView with search results
        searchResultAdapter.setSearchResults(searchResults);
    }
}
