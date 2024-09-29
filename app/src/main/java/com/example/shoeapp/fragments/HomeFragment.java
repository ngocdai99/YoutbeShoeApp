package com.example.shoeapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.shoeapp.CartActivity;
import com.example.shoeapp.R;
import com.example.shoeapp.adapters.CategoryAdapter;
import com.example.shoeapp.adapters.ProductAdapter;
import com.example.shoeapp.adapters.SliderAdapter;
import com.example.shoeapp.models.Category;
import com.example.shoeapp.models.Product;
import com.example.shoeapp.utils.CartUtils;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);
        RecyclerView recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts);
        RecyclerView recyclerViewCategories = view.findViewById(R.id.recyclerViewCategories);
        RelativeLayout layoutCart = view.findViewById(R.id.layoutCart);
        TextView txtCartQuantity = view.findViewById(R.id.txtCartQuantity);

        CartUtils.formatQuantityTextView(txtCartQuantity, CartUtils.getSize());

        SliderAdapter sliderAdapter = new SliderAdapter(requireActivity(), setBanner());
        viewPager2.setAdapter(sliderAdapter);

        layoutCart.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), CartActivity.class);
            requireActivity().startActivity(intent);
        });

        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewProducts.setAdapter(new ProductAdapter(requireActivity(), getSampleProduct()));

        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategories.setAdapter(new CategoryAdapter(requireActivity(), getSampleCategory(), -1));
        return view;
    }

    private ArrayList<Integer> setBanner() {
        ArrayList<Integer> banners = new ArrayList<>();
        banners.add(R.mipmap.banner1);
        banners.add(R.mipmap.banner2);
        banners.add(R.mipmap.banner3);
        return banners;
    }

    private ArrayList<Product> getSampleProduct() {
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Converse Classic", "Giày thể thao Converse cổ điển", 79.00, "https://example.com/converse_classic.jpg", 4.8));
        productList.add(new Product(2, "Nike Air Max", "Giày Nike Air Max 270", 99.00, "https://example.com/nike_air_max.jpg", 4.9));
        productList.add(new Product(3, "Adidas Ultraboost", "Giày chạy bộ Adidas Ultraboost", 69.00, "https://example.com/adidas_ultraboost.jpg", 4.7));
        productList.add(new Product(4, "Puma Suede", "Giày Puma Suede cổ điển", 79.00, "https://example.com/puma_suede.jpg", 4.5));
        productList.add(new Product(5, "Vans Old Skool", "Giày Vans Old Skool đen trắng", 90.00, "https://example.com/vans_old_skool.jpg", 4.6));
        productList.add(new Product(6, "Reebok Classic", "Giày Reebok cổ điển", 99.00, "https://example.com/reebok_classic.jpg", 4.4));
        productList.add(new Product(7, "Balenciaga Triple S", "Giày Balenciaga Triple S", 69.00, "https://example.com/balenciaga_triple_s.jpg", 4.9));
        productList.add(new Product(8, "Gucci Ace", "Giày Gucci Ace với họa tiết ong", 79.00, "https://example.com/gucci_ace.jpg", 4.8));
        productList.add(new Product(9, "New Balance 574", "Giày New Balance 574 màu xám", 90.00, "https://example.com/nb_574.jpg", 4.6));
        productList.add(new Product(10, "Asics Gel-Lyte III", "Giày Asics Gel-Lyte III", 99.00, "https://example.com/asics_gel_lyte.jpg", 4.7));
        return productList;
    }

    public ArrayList<Category> getSampleCategory() {
        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Converse"));
        categoryList.add(new Category(2, "Adidas"));
        categoryList.add(new Category(3, "Nike"));
        categoryList.add(new Category(4, "Puma"));
        categoryList.add(new Category(5, "New Balance"));
        categoryList.add(new Category(6, "Vans"));
        return categoryList;
    }


}

