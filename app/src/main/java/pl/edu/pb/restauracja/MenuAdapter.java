package pl.edu.pb.restauracja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import pl.edu.pb.restauracja.database.MenuItem;
import pl.edu.pb.restauracja.database.Restaurant;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>{
    private List<MenuItem> menuItems ;
    private Context context;

    public MenuAdapter(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);

        // Display category header if it's the first item in the category
        if (position == 0 || !menuItem.getCategory().equals(menuItems.get(position - 1).getCategory())) {
            holder.categoryTextView.setVisibility(View.VISIBLE);
            holder.categoryTextView.setText(menuItem.getCategory());
        } else {
            holder.categoryTextView.setVisibility(View.GONE);
        }

        holder.itemNameTextView.setText(menuItem.getItemName());
        holder.priceTextView.setText(String.valueOf(menuItem.getPrice()));
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemNameTextView;
        TextView priceTextView;
        TextView categoryTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.textViewItemName);
            priceTextView = itemView.findViewById(R.id.textViewPrice);
            categoryTextView = itemView.findViewById(R.id.textViewCategory);
        }
    }
}
