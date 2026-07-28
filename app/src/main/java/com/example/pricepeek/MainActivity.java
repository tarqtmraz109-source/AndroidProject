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
        categories.add(new Category("لحوم وأسماك", android.R.drawable.ic_menu_myplaces, Meat.class));
        categories.add(new Category("ألبان", android.R.drawable.ic_menu_agenda, Dairy.class));
        categories.add(new Category("مشروبات", android.R.drawable.ic_menu_compass, Drinck.class));
        categories.add(new Category("خضروات", android.R.drawable.ic_menu_gallery, Grocery.class));
        categories.add(new Category("مواد تنظيف", android.R.drawable.ic_menu_manage, CleaningMaterial.class));
        categories.add(new Category("أثاث", android.R.drawable.ic_menu_sort_by_size, Furniture.class));
        categories.add(new Category("مواد غذائية", android.R.drawable.ic_menu_search, FoodStuffs.class));

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
