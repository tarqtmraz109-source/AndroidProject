package com.example.pricepeek;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Offers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_offers);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.offerPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupOffers();
        setupBottomNav();
    }

    private void setupOffers() {
        List<Object> items = new ArrayList<>();

        items.add("عروض اليوم");
        items.add(new Product("Tide مسحوق غسيل - خصم 20%", "سوبر ماركت غزة", "10.00 ₪", R.drawable.cleaning_detergent2));
        items.add(new Product("حليب طازج - عرض خاص", "سوبر ماركت غزة", "3.99 ₪", R.drawable.dairy_milk));
        items.add(new Product("دجاج كامل - خصم 15%", "ملحمة غزة", "18.70 ₪", R.drawable.meat_chicken));

        items.add("عروض نهاية الأسبوع");
        items.add(new Product("زيت زيتون بكر - 2+1", "سوق غزة", "25.00 ₪", R.drawable.grocery_oil));
        items.add(new Product("Head & Shoulders - خصم 10%", "صيدلية الشفاء", "19.80 ₪", R.drawable.shampoo1));
        items.add(new Product("أرز بسمتي - سعر مخفض", "سوبر ماركت غزة", "9.99 ₪", R.drawable.grocery_rice));
        items.add(new Product("كرسي مكتب دوار - تخفيض", "أثاث غزة", "120.00 ₪", R.drawable.furniture_chair2));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProductAdapter adapter = new ProductAdapter(items, product -> {
            Intent intent = new Intent(Offers.this, PrudectDetails.class);
            intent.putExtra("product_name", product.getName());
            intent.putExtra("product_shop", product.getShop());
            intent.putExtra("product_price", product.getPrice());
            intent.putExtra("product_image", product.getImageRes());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
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
