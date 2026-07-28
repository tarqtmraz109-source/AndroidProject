package com.example.pricepeek;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShopDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shop_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("shop_name");
        String address = intent.getStringExtra("shop_address");
        String phone = intent.getStringExtra("shop_phone");
        String rating = intent.getStringExtra("shop_rating");
        String hours = intent.getStringExtra("shop_hours");
        int imageRes = intent.getIntExtra("shop_image", 0);

        ImageView imgStore = findViewById(R.id.storeImage);
        TextView tvName = findViewById(R.id.shopName);
        TextView tvRating = findViewById(R.id.rating);
        TextView tvAddress = findViewById(R.id.address);
        TextView tvPhone = findViewById(R.id.phone);
        TextView tvTime = findViewById(R.id.time);

        if (imageRes != 0) imgStore.setImageResource(imageRes);
        if (name != null) tvName.setText(name);
        if (rating != null) tvRating.setText(rating);
        if (address != null) tvAddress.setText("📍 " + address);
        if (phone != null) tvPhone.setText("📞 " + phone);
        if (hours != null) tvTime.setText("🕐 " + hours);

        findViewById(R.id.backBtn).setOnClickListener(v -> finish());

        setupFeaturedProducts(imageRes);
        setupBottomNav();
    }

    private void setupFeaturedProducts(int imageRes) {
        RecyclerView recyclerView = findViewById(R.id.recyclerProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Object> items = new ArrayList<>();
        items.add(new Product("منتج مميز 1", "المتجر", "XX.XX ₪", imageRes));
        items.add(new Product("منتج مميز 2", "المتجر", "XX.XX ₪", imageRes));
        items.add(new Product("منتج مميز 3", "المتجر", "XX.XX ₪", imageRes));

        ProductAdapter adapter = new ProductAdapter(items, product -> {
            Intent intent = new Intent(ShopDetails.this, PrudectDetails.class);
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
