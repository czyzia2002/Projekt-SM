package pl.edu.pb.restauracja;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import pl.edu.pb.restauracja.database.MenuItem;

public class OrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        TextView titleTextView = findViewById(R.id.textViewTitle);
        List<MenuItem> selectedItems = getIntent().getParcelableArrayListExtra("selectedItems");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewOrder);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        OrderAdapter adapter = new OrderAdapter(selectedItems);
        recyclerView.setAdapter(adapter);
    }
}
