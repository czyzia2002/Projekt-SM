package pl.edu.pb.restauracja;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import pl.edu.pb.restauracja.database.Restaurant;

public class ZamowAdapter extends RecyclerView.Adapter<ZamowAdapter.ViewHolder> {

    private List<Restaurant> restaurants;

    public ZamowAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zamow_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.nameTextView.setText(restaurant.getName());
        holder.addressTextView.setText(restaurant.getCity() + ", " + restaurant.getStreet() + " " + restaurant.getNumber());

        holder.buttonPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // Obsługa kliknięcia "Odbiór własny"
            }
        });

        holder.buttonDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obsługa kliknięcia "Dowóz"
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView addressTextView;
        Button buttonPickup;
        Button buttonDelivery;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            addressTextView = itemView.findViewById(R.id.textViewAddress);
            buttonPickup = itemView.findViewById(R.id.buttonPickup);
            buttonDelivery = itemView.findViewById(R.id.buttonDelivery);
        }
    }
}
