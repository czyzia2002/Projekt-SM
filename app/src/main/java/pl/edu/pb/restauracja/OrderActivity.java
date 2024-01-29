package pl.edu.pb.restauracja;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import pl.edu.pb.restauracja.database.MenuItem;

public class OrderActivity extends AppCompatActivity {
    private TextView titleTextView;
    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private EditText editTextStreet, editTextHouseNumber, editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        titleTextView = findViewById(R.id.textViewTitle);
        editTextStreet = findViewById(R.id.editTextStreet);
        editTextHouseNumber = findViewById(R.id.editTextHouseNumber);
        editTextPhone = findViewById(R.id.editTextPhone);

        titleTextView = findViewById(R.id.textViewTitle);
        List<MenuItem> selectedItems = getIntent().getParcelableArrayListExtra("selectedItems");

        recyclerView = findViewById(R.id.recyclerViewOrder);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        OrderAdapter.OnItemClickListener onItemClickListener = new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MenuItem selectedItem, String orderType) {

            }
        };

        adapter = new OrderAdapter(selectedItems, onItemClickListener, "pickup");
        recyclerView.setAdapter(adapter);

        Button confirmOrderButton = findViewById(R.id.buttonConfirmOrder);
        confirmOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String street = editTextStreet.getText().toString().trim();
                String houseNumber = editTextHouseNumber.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();

                Toast.makeText(OrderActivity.this, getString(R.string.doneorder), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(OrderActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }


}
