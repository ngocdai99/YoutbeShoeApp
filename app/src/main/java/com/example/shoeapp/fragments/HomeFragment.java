package com.example.shoeapp.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.shoeapp.CartActivity;
import com.example.shoeapp.R;
import com.example.shoeapp.adapters.CategoryAdapter;
import com.example.shoeapp.adapters.ProductAdapter;
import com.example.shoeapp.adapters.SliderAdapter;
import com.example.shoeapp.daos.ProductDAO;
import com.example.shoeapp.daos.UserDAO;
import com.example.shoeapp.models.Category;
import com.example.shoeapp.models.Product;
import com.example.shoeapp.models.User;
import com.example.shoeapp.utils.CartUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    View view;
    RecyclerView recyclerViewProducts;
    RecyclerView recyclerViewCategories;
    ArrayList<Product> products = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        ViewPager2 viewPager2 = view.findViewById(R.id.viewPager2);
        RelativeLayout layoutCart = view.findViewById(R.id.layoutCart);
        FloatingActionButton btnCreate = view.findViewById(R.id.btnCreate);
        TextView txtFullname = view.findViewById(R.id.txtFullname);

        setUsername(txtFullname);

        SliderAdapter sliderAdapter = new SliderAdapter(requireActivity(), setBanner());
        viewPager2.setAdapter(sliderAdapter);

        layoutCart.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), CartActivity.class);
            requireActivity().startActivity(intent);
        });

        loadProducts();

        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategories.setAdapter(new CategoryAdapter(requireActivity(), getSampleCategory(), -1));

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popUpDialog(requireActivity());
            }
        });
        return view;
    }

    private void setUsername(TextView textView) {
        UserDAO userDAO = new UserDAO(requireActivity());
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", null);
        if (username != null) {
            User user = userDAO.getUser(username);
            textView.setText(new StringBuilder().append("Hello, ").append(user.getFullname()));
        }
    }

    private ArrayList<Integer> setBanner() {
        ArrayList<Integer> banners = new ArrayList<>();
        banners.add(R.mipmap.banner1);
        banners.add(R.mipmap.banner2);
        banners.add(R.mipmap.banner3);
        return banners;
    }

//    private ArrayList<Product> getSampleProduct() {
//        ArrayList<Product> productList = new ArrayList<>();
//        productList.add(new Product(1, "Converse Classic", "Giày thể thao Converse cổ điển", 79.00, "https://example.com/converse_classic.jpg", 4.8));
//        productList.add(new Product(2, "Nike Air Max", "Giày Nike Air Max 270", 99.00, "https://example.com/nike_air_max.jpg", 4.9));
//        productList.add(new Product(3, "Adidas Ultraboost", "Giày chạy bộ Adidas Ultraboost", 69.00, "https://example.com/adidas_ultraboost.jpg", 4.7));
//        productList.add(new Product(4, "Puma Suede", "Giày Puma Suede cổ điển", 79.00, "https://example.com/puma_suede.jpg", 4.5));
//        productList.add(new Product(5, "Vans Old Skool", "Giày Vans Old Skool đen trắng", 90.00, "https://example.com/vans_old_skool.jpg", 4.6));
//        productList.add(new Product(6, "Reebok Classic", "Giày Reebok cổ điển", 99.00, "https://example.com/reebok_classic.jpg", 4.4));
//        productList.add(new Product(7, "Balenciaga Triple S", "Giày Balenciaga Triple S", 69.00, "https://example.com/balenciaga_triple_s.jpg", 4.9));
//        productList.add(new Product(8, "Gucci Ace", "Giày Gucci Ace với họa tiết ong", 79.00, "https://example.com/gucci_ace.jpg", 4.8));
//        productList.add(new Product(9, "New Balance 574", "Giày New Balance 574 màu xám", 90.00, "https://example.com/nb_574.jpg", 4.6));
//        productList.add(new Product(10, "Asics Gel-Lyte III", "Giày Asics Gel-Lyte III", 99.00, "https://example.com/asics_gel_lyte.jpg", 4.7));
//        return productList;
//    }

    private void loadProducts() {
        products.clear();
        ProductDAO productDAO = new ProductDAO(requireActivity());
        products = productDAO.getProducts();
        recyclerViewProducts = view.findViewById(R.id.recyclerViewProducts);
        recyclerViewCategories = view.findViewById(R.id.recyclerViewCategories);

        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewProducts.setAdapter(new ProductAdapter(requireActivity(), products));
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

    public void popUpDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_create_product);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
        }

        ImageView imageView = dialog.findViewById(R.id.imageView);
        EditText edtImageUrl = dialog.findViewById(R.id.edtImageUrl);
        EditText edtName = dialog.findViewById(R.id.edtName);
        EditText edtPrice = dialog.findViewById(R.id.edtPrice);
        EditText edtDesc = dialog.findViewById(R.id.edtDesc);
        Button btnCreate = dialog.findViewById(R.id.btnCreate);

        dialog.setCancelable(true);

        btnCreate.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String description = edtDesc.getText().toString().trim();
            String priceStr = edtPrice.getText().toString().trim();
            double price = Double.parseDouble(priceStr);
            String imageUrl = "https://assets.adidas.com/images/w_320,f_auto,q_auto,fl_lossy,c_fill,g_auto/316297fac2c54c689ec192e376e79540_9366/ultraboost-light-running-shoes.jpg";
            double ratings = 5.0;

            if (name.isEmpty() || description.isEmpty() || priceStr.isEmpty()) {
                Toast.makeText(context, "Fields should not be empty", Toast.LENGTH_SHORT).show();
            } else {
                ProductDAO productDAO = new ProductDAO(context);
                boolean createSuccess = productDAO.createProduct(name, description, price, imageUrl, ratings);
                if (createSuccess) {
                    Toast.makeText(context, "Created Successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    loadProducts();
                } else {
                    Toast.makeText(context, "Created failed", Toast.LENGTH_SHORT).show();
                }
            }


        });

        imageView.setOnClickListener(v -> {
            String imageUrl = "https://assets.adidas.com/images/w_320,f_auto,q_auto,fl_lossy,c_fill,g_auto/316297fac2c54c689ec192e376e79540_9366/ultraboost-light-running-shoes.jpg";
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.mipmap.default_img)
                    .into(imageView);
        });


        dialog.show();
    }


}

