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

public class CleaningMaterial extends AppCompatActivity {

    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cleaning_material);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cleaning_materialPage), (v, insets) -> {
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

        // ==================== مساحيق الغسيل ====================
        items.add("مساحيق الغسيل");
        items.add(new Product("Tide مسحوق غسيل", "سوبر ماركت غزة", "12.50 ₪", R.drawable.cleaning_detergent2));
        items.add(new Product("Tide سائل غسيل", "هيبو ماركت", "15.00 ₪", R.drawable.cleaning_detergent3));
        items.add(new Product("Tide حبيبات غسيل", "سوبر ماركت غزة", "18.00 ₪", R.drawable.cleaning_detergent3_2));

        // ==================== الشامبو ====================
        items.add("شامبو");
        items.add(new Product("Head & Shoulders", "صيدلية الشفاء", "22.00 ₪", R.drawable.shampoo1));
        items.add(new Product("Cibu شامبو", "هيبو ماركت", "17.50 ₪", R.drawable.shampoo2));

        // ==================== صابون سائل ====================
        items.add("صابون سائل");
        items.add(new Product("Morning Fresh", "سوبر ماركت غزة", "9.50 ₪", R.drawable.cleaning_soap1));
        items.add(new Product("Sunlight سائل", "هيبو ماركت", "8.00 ₪", R.drawable.cleaning_soap2));
        items.add(new Product("Sunlight جلي", "سوبر ماركت غزة", "10.25 ₪", R.drawable.cleaning_soap3));

        // ==================== صابون قطع ====================
        items.add("صابون قطع");
        items.add(new Product("Soap صابون قطع", "صيدلية الشفاء", "3.50 ₪", R.drawable.soapbar1));
        items.add(new Product("Soap صابون أبيض", "هيبو ماركت", "4.00 ₪", R.drawable.soapbar2));

        // ==================== رمّاد وسبراي ====================
        items.add("رمّاد وسبراي");
        items.add(new Product("Fysof رمّاد", "سوبر ماركت غزة", "6.50 ₪", R.drawable.cleaning_spray1));
        items.add(new Product("Fysof سبراي تنظيف", "هيبو ماركت", "7.75 ₪", R.drawable.cleaning_spray2));
        items.add(new Product("Fysof معقم أرضيات", "سوبر ماركت غزة", "11.00 ₪", R.drawable.cleaning_spray3));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter(items, product -> {
            Intent intent = new Intent(CleaningMaterial.this, PrudectDetails.class);
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
    }
}
