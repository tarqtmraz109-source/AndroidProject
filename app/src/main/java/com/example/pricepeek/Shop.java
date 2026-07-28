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

public class Shop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shop);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.shopPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupShops();
        setupBottomNav();
    }

    private void setupShops() {
        List<ShopModel> shops = new ArrayList<>();
        shops.add(new ShopModel("سوبر ماركت غزة", "شارع الشهداء، غزة", "059-123-4567", "⭐ 4.5", "8:00 - 22:00", R.drawable.ic_alamal_market));
        shops.add(new ShopModel("هيبو ماركت", "شارع النصر، غزة", "059-234-5678", "⭐ 4.2", "9:00 - 21:00", R.drawable.ic_alnada_market));
        shops.add(new ShopModel("سوق السلام", "شارع الجلاء، غزة", "059-345-6789", "⭐ 4.0", "7:00 - 23:00", R.drawable.ic_alsalam_market));
        shops.add(new ShopModel("صيدلية الشفاء", "شارع عزالدين، غزة", "059-456-7890", "⭐ 4.8", "24 ساعة", R.drawable.ic_alsham_market));
        shops.add(new ShopModel("سوق غزة للمواد الغذائية", "شارع الوحدة، غزة", "059-567-8901", "⭐ 4.3", "6:00 - 22:00", R.drawable.ic_alamal_market));
        shops.add(new ShopModel("ملحمة غزة الطازجة", "شارع التريques، غزة", "059-678-9012", "⭐ 4.6", "8:00 - 20:00", R.drawable.ic_alsham_market));
        shops.add(new ShopModel("معرض الأثاث الحديث", "شارع الصناعة، غزة", "059-789-0123", "⭐ 4.1", "9:00 - 19:00", R.drawable.ic_alwaha_furn));
        shops.add(new ShopModel("أثاث القدس", "شارع الم开出، غزة", "059-890-1234", "⭐ 4.4", "10:00 - 20:00", R.drawable.ic_alquds_furn));
        shops.add(new ShopModel("أثاث روتو", "شارع الرشيد، غزة", "059-901-2345", "⭐ 4.3", "9:00 - 21:00", R.drawable.ic_ruto_furn));

        RecyclerView recyclerView = findViewById(R.id.Shops);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ShopAdapter adapter = new ShopAdapter(shops, shop -> {
            Intent intent = new Intent(Shop.this, ShopDetails.class);
            intent.putExtra("shop_name", shop.getName());
            intent.putExtra("shop_address", shop.getAddress());
            intent.putExtra("shop_phone", shop.getPhone());
            intent.putExtra("shop_rating", shop.getRating());
            intent.putExtra("shop_hours", shop.getHours());
            intent.putExtra("shop_image", shop.getImageRes());
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
