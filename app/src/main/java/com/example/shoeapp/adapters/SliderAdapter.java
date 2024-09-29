package com.example.shoeapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.shoeapp.R;
import java.util.ArrayList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {
    private final Context context;
    private final ArrayList<Integer> sliderItems;


    public SliderAdapter(Context context, ArrayList<Integer> sliderItems) {
        this.context = context;
        this.sliderItems = sliderItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_slider, parent, false));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageSlide;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSlide = itemView.findViewById(R.id.imageSlide);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Integer sliderItem = sliderItems.get(position);
        holder.imageSlide.setImageResource(sliderItem);
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }
}
