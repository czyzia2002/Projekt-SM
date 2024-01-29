package pl.edu.pb.restauracja;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pl.edu.pb.restauracja.database.MenuItem;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private static List<MenuItem> selectedItems;
    private static OnItemClickListener onItemClickListener;
    private String orderType;
    public OrderAdapter(List<MenuItem> selectedItems, OnItemClickListener onItemClickListener, String orderType) {
        this.selectedItems = selectedItems;
        this.onItemClickListener = onItemClickListener;
        this.orderType = orderType;
    }

    public interface OnItemClickListener {
        void onItemClick(MenuItem selectedItem, String orderType);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem selectedItem = selectedItems.get(position);
        holder.bind(selectedItem);
    }

    @Override
    public int getItemCount() {
        return selectedItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        MenuItem clickedItem = selectedItems.get(position);
                        onItemClickListener.onItemClick(clickedItem, "pickup");
                    }
                }
            });
        }

        public void bind(MenuItem selectedItem) {
            itemNameTextView.setText(selectedItem.getItemName());
        }
    }
}
