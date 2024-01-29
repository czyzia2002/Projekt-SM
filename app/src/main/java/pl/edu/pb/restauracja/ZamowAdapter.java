package pl.edu.pb.restauracja;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import pl.edu.pb.restauracja.database.MenuItem;
import pl.edu.pb.restauracja.database.Restaurant;

public class ZamowAdapter extends RecyclerView.Adapter<ZamowAdapter.ViewHolder> {
    private List<Restaurant> restaurants;
    private Context context;

    public ZamowAdapter(List<Restaurant> restaurants, Context context) {
        this.restaurants = restaurants;
        this.context = context;
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

        holder.buttonDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Restaurant restaurant = restaurants.get(holder.getAdapterPosition());
                Intent intent = new Intent(context, ZamowMenuActivity.class);
                context.startActivity(intent);
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
        Button buttonDelivery;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textViewName);
            addressTextView = itemView.findViewById(R.id.textViewAddress);
            buttonDelivery = itemView.findViewById(R.id.buttonDelivery);
        }
    }
}