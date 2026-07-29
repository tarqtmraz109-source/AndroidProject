package com.example.pricepeek;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupCategories();
        setupBottomNav();
    }

    private void setupCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("لحوم وأسماك", R.drawable.meat_sec_ic, Meat.class));
        categories.add(new Category("ألبان", R.drawable.dairy_sec_ic, Dairy.class));
        categories.add(new Category("مشروبات", R.drawable.drink_sec_ic, Drinck.class));
        categories.add(new Category("خضروات", R.drawable.vegetable_sec_ic, Grocery.class));
        categories.add(new Category("مواد تنظيف", R.drawable.cleaning_sec_ic, CleaningMaterial.class));
        categories.add(new Category("أثاث", R.drawable.furniture_sec_ic, Furniture.class));
        categories.add(new Category("مواد غذائية", R.drawable.grocery_sec_ic, FoodStuffs.class));

        RecyclerView sectionRecyclerView = findViewById(R.id.SectionRecyclerView);
        sectionRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        CategoryAdapter adapter = new CategoryAdapter(categories, category -> {
            Intent intent = new Intent(MainActivity.this, category.getActivityClass());
            startActivity(intent);
        });

        sectionRecyclerView.setAdapter(adapter);
    }

    private void setupBottomNav() {
        findViewById(R.id.btnHome).setOnClickListener(v -> {
            // already on home
        });

        findViewById(R.id.btnShops).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Shop.class));
        });

        findViewById(R.id.btnProducts).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Prudect.class));
        });

        findViewById(R.id.btnAbout).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, About.class));
        });

        findViewById(R.id.btnProfile).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Profile.class));
        });

        findViewById(R.id.btnDiscover).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Offers.class));
        });
        findViewById(R.id.btnNotifications).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Notifications.class));
        });
    }
}
