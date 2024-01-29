package pl.edu.pb.restauracja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import pl.edu.pb.restauracja.database.MenuItem;

public class ZamowMenuAdapter extends RecyclerView.Adapter<ZamowMenuAdapter.ViewHolder> {
    private List<MenuItem> menuItems;
    private List<MenuItem> selectedItems = new ArrayList<>();
    private OnAddItemClickListener onAddItemClickListener;
    public ZamowMenuAdapter(List<MenuItem> menuItems) {
        Collections.sort(menuItems, new Comparator<MenuItem>() {
            @Override
            public int compare(MenuItem menuItem1, MenuItem menuItem2) {
                return menuItem1.getCategory().compareTo(menuItem2.getCategory());
            }
        });

        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zamowmenu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);

        if (position == 0) {
            holder.categoryTextView.setVisibility(View.VISIBLE);
            holder.categoryTextView.setText(menuItem.getCategory());
        } else {
            // Check if the current item's category is different from the previous item
            MenuItem prevMenuItem = menuItems.get(position - 1);
            if (!menuItem.getCategory().equals(prevMenuItem.getCategory())) {
                holder.categoryTextView.setVisibility(View.VISIBLE);
                holder.categoryTextView.setText(menuItem.getCategory());
            } else {
                // Hide the category if it's the same as the previous item
                holder.categoryTextView.setVisibility(View.GONE);
            }
        }

        holder.itemNameTextView.setText(menuItem.getItemName());
        holder.priceTextView.setText(String.valueOf(menuItem.getPrice()));

        holder.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onAddItemClickListener != null) {
                    onAddItemClickListener.onAddItemClick(menuItem);
                }
            }
        });
    }

    public interface OnAddItemClickListener {
        void onAddItemClick(MenuItem menuItem);
    }

    public void setOnAddItemClickListener(OnAddItemClickListener listener) {
        this.onAddItemClickListener = listener;
    }

    public void toggleSelection(MenuItem menuItem) {
        if (selectedItems.contains(menuItem)) {
            selectedItems.remove(menuItem);
        } else {
            selectedItems.add(menuItem);
        }
        notifyDataSetChanged();
    }

    public List<MenuItem> getSelectedItems() {
        return selectedItems;
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView;
        TextView priceTextView;
        TextView categoryTextView;
        Button buttonAdd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.textViewItemName);
            priceTextView = itemView.findViewById(R.id.textViewPrice);
            categoryTextView = itemView.findViewById(R.id.textViewCategory);
            buttonAdd = itemView.findViewById(R.id.buttonAdd);
        }
    }
}
