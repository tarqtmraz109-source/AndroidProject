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

public class PrudectDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prudect_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("product_name");
        String shop = intent.getStringExtra("product_shop");
        String price = intent.getStringExtra("product_price");
        int imageRes = intent.getIntExtra("product_image", 0);

        ImageView imgProduct = findViewById(R.id.imageView);
        TextView tvName = findViewById(R.id.PrudectName);
        TextView tvPrice = findViewById(R.id.Price);
        TextView tvShop = findViewById(R.id.ShopName);

        if (name != null) tvName.setText(name);
        if (price != null) tvPrice.setText(price);
        if (shop != null) tvShop.setText(shop);
        if (imageRes != 0) imgProduct.setImageResource(imageRes);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        setupPriceComparison(name, shop, price, imageRes);
        setupBottomNav();
    }

    private void setupPriceComparison(String productName, String shop, String price, int imageRes) {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewPrices);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Object> items = new ArrayList<>();

        if (productName != null && productName.contains("Tide")) {
            items.add(new Product(productName, "سوبر ماركت غزة", "12.50 ₪", imageRes));
            items.add(new Product(productName, "هيبو ماركت", "13.00 ₪", imageRes));
            items.add(new Product(productName, "سوق غزة", "11.75 ₪", imageRes));
        } else if (productName != null && productName.contains("حليب")) {
            items.add(new Product(productName, "سوبر ماركت غزة", "5.50 ₪", imageRes));
            items.add(new Product(productName, "هيبو ماركت", "5.00 ₪", imageRes));
            items.add(new Product(productName, "سوق غزة", "6.00 ₪", imageRes));
        } else if (productName != null && productName.contains("جبنة")) {
            items.add(new Product(productName, "سوبر ماركت غزة", "14.00 ₪", imageRes));
            items.add(new Product(productName, "هيبو ماركت", "15.50 ₪", imageRes));
        } else if (productName != null && productName.contains("دجاج")) {
            items.add(new Product(productName, "ملحمة غزة", "22.00 ₪", imageRes));
            items.add(new Product(productName, "سوق غزة", "24.00 ₪", imageRes));
        } else if (productName != null && productName.contains("لحم")) {
            items.add(new Product(productName, "ملحمة غزة", "55.00 ₪", imageRes));
            items.add(new Product(productName, "سوق غزة", "58.00 ₪", imageRes));
        } else if (productName != null && productName.contains("أرز")) {
            items.add(new Product(productName, "سوبر ماركت غزة", "12.00 ₪", imageRes));
            items.add(new Product(productName, "هيبو ماركت", "11.50 ₪", imageRes));
        } else if (productName != null && productName.contains("زيت")) {
            items.add(new Product(productName, "سوق غزة", "35.00 ₪", imageRes));
            items.add(new Product(productName, "هيبو ماركت", "37.00 ₪", imageRes));
        } else if (productName != null && productName.contains("بيبسي")) {
            items.add(new Product(productName, "سوبر ماركت غزة", "3.50 ₪", imageRes));
            items.add(new Product(productName, "هيبو ماركت", "3.25 ₪", imageRes));
            items.add(new Product(productName, "سوق غزة", "4.00 ₪", imageRes));
        } else if (productName != null && productName.contains("كرسي")) {
            items.add(new Product(productName, "أثاث غزة", "85.00 ₪", imageRes));
            items.add(new Product(productName, "معرض الأثاث", "95.00 ₪", imageRes));
        } else if (productName != null && productName.contains("Head")) {
            items.add(new Product(productName, "صيدلية الشفاء", "22.00 ₪", imageRes));
            items.add(new Product(productName, "هيبو ماركت", "24.00 ₪", imageRes));
        } else {
            items.add(new Product(productName != null ? productName : "منتج", shop, price, imageRes));
            items.add(new Product(productName != null ? productName : "منتج", "سوبر ماركت غزة", price, imageRes));
        }

        ProductAdapter adapter = new ProductAdapter(items, product -> {
            Intent intent = new Intent(PrudectDetails.this, PrudectDetails.class);
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
