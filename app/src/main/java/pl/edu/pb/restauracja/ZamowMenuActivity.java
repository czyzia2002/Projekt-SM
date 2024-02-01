package pl.edu.pb.restauracja;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pl.edu.pb.restauracja.database.AppDatabase;
import pl.edu.pb.restauracja.database.MenuDao;
import pl.edu.pb.restauracja.database.MenuItem;
import pl.edu.pb.restauracja.database.MenuRepository;

public class ZamowMenuActivity extends AppCompatActivity {

    private List<MenuItem> selectedMenuItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zamowmenu);

        selectedMenuItems = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewMenu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        AppDatabase appDatabase = DatabaseInstance.getInstance(this);

        // Uzyskaj instancję interfejsu dostępu do danych (DAO)
        MenuDao menuDao = appDatabase.menuDao();

        DatabaseInstance.insertInitialData2(menuDao);

        // Pobierz listę restauracji z bazy danych
        List<MenuItem> menuItems = menuDao.getAllMenuItems();

        ZamowMenuAdapter adapter = new ZamowMenuAdapter(menuItems);

        adapter.setOnAddItemClickListener(new ZamowMenuAdapter.OnAddItemClickListener() {
            @Override
            public void onAddItemClick(MenuItem menuItem) {
                adapter.toggleSelection(menuItem);
                Toast.makeText(ZamowMenuActivity.this, "Added: " + menuItem.getItemName(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);

        Button buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedMenuItems = adapter.getSelectedItems();

                if (selectedMenuItems.isEmpty()) {
                    Toast.makeText(ZamowMenuActivity.this, "No items selected", Toast.LENGTH_SHORT).show();
                } else {
                    selectedMenuItems = adapter.getSelectedItems();

                    Intent intent = new Intent(ZamowMenuActivity.this, OrderActivity.class);
                    intent.putParcelableArrayListExtra("selectedItems", new ArrayList<>(selectedMenuItems));
                    startActivity(intent);
                }
            }
        });
    }
}
