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

public class Notifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notifications);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.notificationPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupNotifications();
        setupBottomNav();
    }

    private void setupNotifications() {
        List<Object> items = new ArrayList<>();

        items.add("أحدث الإشعارات");
        items.add(new Product("Tide مسحوق غسيل انخفض سعره!", "سوبر ماركت غزة", "10.00 ₪ ⬇️", R.drawable.cleaning_detergent2));
        items.add(new Product("عرض جديد على الدجاج", "ملحمة غزة", "18.70 ₪", R.drawable.meat_chicken));
        items.add(new Product("حليب طازج - سعر مخفض", "سوبر ماركت غزة", "3.99 ₪", R.drawable.dairy_milk));

        items.add("تنبيهات الأسعار");
        items.add(new Product("زيت زيتون بكر - سعر مرتفع", "سوق غزة", "35.00 ₪ ⬆️", R.drawable.grocery_oil));
        items.add(new Product("أرز بسمتي - سعر مستقر", "سوبر ماركت غزة", "12.00 ₪ ➡️", R.drawable.grocery_rice));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ProductAdapter adapter = new ProductAdapter(items, product -> {
            Intent intent = new Intent(Notifications.this, PrudectDetails.class);
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
