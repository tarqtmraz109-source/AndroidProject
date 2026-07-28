package com.example.pricepeek;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Furniture extends AppCompatActivity {

    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_furniture);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.furniturPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupProductList();
        setupSearch();
        setupBottomNav();
    }

    private void setupProductList() {
        List<Object> items = new ArrayList<>();

        // ==================== كراسي ====================
        items.add("كراسي");
        items.add(new Product("كرسي مطبخ خشبي", "أثاث غزة", "85.00 ₪", R.drawable.furniture_chair1));
        items.add(new Product("كرسي مكتب دوار", "أثاث غزة", "150.00 ₪", R.drawable.furniture_chair2));
        items.add(new Product("كرسي استرخاء", "معرض الأثاث", "200.00 ₪", R.drawable.furniture_chair3));

        // ==================== لمبات ====================
        items.add("إضاءة ولمبات");
        items.add(new Product("نابض مكتب LED", "أثاث غزة", "45.00 ₪", R.drawable.furniture_lamp1));
        items.add(new Product("نابض سقفي", "معرض الأثاث", "120.00 ₪", R.drawable.furniture_lamp2));
        items.add(new Product("لمبة حائط ديكورية", "أثاث غزة", "35.00 ₪", R.drawable.furniture_lamp3));

        // ==================== طاولات ====================
        items.add("طاولات");
        items.add(new Product("طاولة طعام 6 أشخاص", "أثاث غزة", "350.00 ₪", R.drawable.furniture_table1));
        items.add(new Product("طاولة قهوة زجاج", "معرض الأثاث", "180.00 ₪", R.drawable.furniture_table2));
        items.add(new Product("طاولة مكتب خشبية", "أثاث غزة", "220.00 ₪", R.drawable.furniture_table3));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter(items, product -> {
            Intent intent = new Intent(Furniture.this, PrudectDetails.class);
            intent.putExtra("product_name", product.getName());
            intent.putExtra("product_shop", product.getShop());
            intent.putExtra("product_price", product.getPrice());
            intent.putExtra("product_image", product.getImageRes());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }

    private void setupSearch() {
        EditText editText = findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupBottomNav() {
        findViewById(R.id.btnHome).setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
        findViewById(R.id.btnShops).setOnClickListener(v -> {
            startActivity(new Intent(this, Shop.class));
            finish();
        });
        findViewById(R.id.btnProducts).setOnClickListener(v -> {
            startActivity(new Intent(this, Prudect.class));
            finish();
        });
        findViewById(R.id.btnProfile).setOnClickListener(v -> {
            startActivity(new Intent(this, Profile.class));
            finish();
        });
        findViewById(R.id.btnAbout).setOnClickListener(v -> {
            startActivity(new Intent(this, About.class));
            finish();
        });
    }}
