package com.example.shoeapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoeapp.R;
import com.example.shoeapp.models.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private final Context context;
    ArrayList<Category> list;
    int selectedPosition = -1;

    public CategoryAdapter(Context context, ArrayList<Category> list, int selectedPosition) {
        this.context = context;
        this.list = list;
        this.selectedPosition = selectedPosition;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category, parent, false));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCategory = itemView.findViewById(R.id.txtCategory);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = list.get(holder.getAdapterPosition());
        holder.txtCategory.setText(category.getCategoryName());


        if (selectedPosition == holder.getAdapterPosition()) {
            decorateSelectedTextView(context, holder.txtCategory);
        } else {
            decorateUnselectedTextView(context, holder.txtCategory);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousSelectedPosition = selectedPosition;
                selectedPosition = holder.getAdapterPosition();

                notifyItemChanged(previousSelectedPosition);
                notifyItemChanged(selectedPosition);

            }
        });
    }

    public static void decorateSelectedTextView(Context context, TextView textView){
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.yellow));
        textView.setTextColor(ContextCompat.getColor(context, R.color.black));
    }
    public static void decorateUnselectedTextView(Context context, TextView textView){
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.light_gray));
        textView.setTextColor(ContextCompat.getColor(context, R.color.black));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
