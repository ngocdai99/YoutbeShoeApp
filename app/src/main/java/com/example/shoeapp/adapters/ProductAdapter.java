package com.example.shoeapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoeapp.ProductDetailActivity;
import com.example.shoeapp.R;
import com.example.shoeapp.models.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private final Context context;
    ArrayList<Product> list;

    public ProductAdapter(Context context, ArrayList<Product> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtProductName, txtPrice, txtRatings, txtCategory;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtRatings = itemView.findViewById(R.id.txtRatings);
            txtCategory = itemView.findViewById(R.id.txtCategory);
        }
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = list.get(position);
        if (position % 2 == 0){
            holder.imageView.setImageResource(R.mipmap.product2);
        }else {
            holder.imageView.setImageResource(R.mipmap.product1);
        }

//        Glide.with(context)
//                .load(product.getImageUrl())
//                .placeholder(R.mipmap.product1)
//                .into(holder.imageView);
        holder.txtProductName.setText(product.getName());
        holder.txtPrice.setText("$" + product.getPrice());
        holder.txtRatings.setText(String.valueOf(product.getRatings()));


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
