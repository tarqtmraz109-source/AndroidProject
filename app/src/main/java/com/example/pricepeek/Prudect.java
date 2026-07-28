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

public class Prudect extends AppCompatActivity {

    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_prudect);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.prudectPage), (v, insets) -> {
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

        // ==================== لحوم ====================
        items.add("لحوم وأسماك");
        items.add(new Product("لحم بقر طازج", "ملحمة غزة", "55.00 ₪", R.drawable.meat_beef));
        items.add(new Product("دجاج كامل", "ملحمة غزة", "22.00 ₪", R.drawable.meat_chicken));
        items.add(new Product("سمك طازج", "سوق غزة", "30.00 ₪", R.drawable.meat_fish));
        items.add(new Product("كفتة جاهزة", "ملحمة غزة", "28.00 ₪", R.drawable.meat_section));

        // ==================== ألبان ====================
        items.add("ألبان ومشتقاتها");
        items.add(new Product("جبنة بيضاء", "سوبر ماركت غزة", "14.00 ₪", R.drawable.dairy_cheese));
        items.add(new Product("جبنة شيدر", "هيبو ماركت", "16.50 ₪", R.drawable.dairy_cheese2));
        items.add(new Product("حليب طازج", "سوبر ماركت غزة", "5.50 ₪", R.drawable.dairy_milk));
        items.add(new Product("زبادي كامل الدسم", "هيبو ماركت", "3.75 ₪", R.drawable.dairy_yogurt1));
        items.add(new Product("زبادي يوناني", "صيدلية الشفاء", "6.00 ₪", R.drawable.dairy_yogurt2));
        items.add(new Product("لبنة طبيعية", "سوبر ماركت غزة", "4.50 ₪", R.drawable.dairy_section));

        // ==================== مشروبات ====================
        items.add("مشروبات");
        items.add(new Product("عصير برتقال طازج", "سوبر ماركت غزة", "8.00 ₪", R.drawable.drinck_juice));
        items.add(new Product("بيبسي عبوة", "هيبو ماركت", "3.50 ₪", R.drawable.drinck_soda));
        items.add(new Product("شاي ليبتون", "سوبر ماركت غزة", "7.25 ₪", R.drawable.drinck_tea));
        items.add(new Product("ماء معدني", "هيبو ماركت", "1.50 ₪", R.drawable.drinck_water));
        items.add(new Product("قهوة عربية", "سوق غزة", "25.00 ₪", R.drawable.drinck_coffee));
        items.add(new Product("بيبسي عبوة كبيرة", "سوبر ماركت غزة", "5.00 ₪", R.drawable.drinck_section));

        // ==================== خضروات ====================
        items.add("خضروات وفواكه");
        items.add(new Product("زيت زيتون بكر", "سوق غزة", "35.00 ₪", R.drawable.grocery_oil));
        items.add(new Product("معكرونة بريلا", "هيبو ماركت", "4.00 ₪", R.drawable.grocery_pasta));
        items.add(new Product("بطاطا طازجة", "سوق غزة", "3.00 ₪", R.drawable.grocery_potato));
        items.add(new Product("أرز بسمتي", "سوبر ماركت غزة", "12.00 ₪", R.drawable.grocery_rice));
        items.add(new Product("موز طازج", "سوق غزة", "6.50 ₪", R.drawable.produce_banana));
        items.add(new Product("طماطم طازجة", "سوق غزة", "2.50 ₪", R.drawable.produce_tomato));
        items.add(new Product("خضار مشكلة", "هيبو ماركت", "8.00 ₪", R.drawable.grocery_section));

        // ==================== مواد تنظيف ====================
        items.add("مواد التنظيف");
        items.add(new Product("Tide مسحوق غسيل", "سوبر ماركت غزة", "12.50 ₪", R.drawable.cleaning_detergent2));
        items.add(new Product("Tide سائل غسيل", "هيبو ماركت", "15.00 ₪", R.drawable.cleaning_detergent3));
        items.add(new Product("Head & Shoulders", "صيدلية الشفاء", "22.00 ₪", R.drawable.shampoo1));
        items.add(new Product("Morning Fresh", "سوبر ماركت غزة", "9.50 ₪", R.drawable.cleaning_soap1));
        items.add(new Product("Sunlight سائل", "هيبو ماركت", "8.00 ₪", R.drawable.cleaning_soap2));
        items.add(new Product("Fysof رمّاد", "سوبر ماركت غزة", "6.50 ₪", R.drawable.cleaning_spray1));

        // ==================== أثاث ====================
        items.add("أثاث");
        items.add(new Product("كرسي مطبخ خشبي", "أثاث غزة", "85.00 ₪", R.drawable.furniture_chair1));
        items.add(new Product("كرسي مكتب دوار", "أثاث غزة", "150.00 ₪", R.drawable.furniture_chair2));
        items.add(new Product("نابض مكتب LED", "أثاث غزة", "45.00 ₪", R.drawable.furniture_lamp1));
        items.add(new Product("طاولة طعام 6 أشخاص", "أثاث غزة", "350.00 ₪", R.drawable.furniture_table1));
        items.add(new Product("طاولة قهوة زجاج", "معرض الأثاث", "180.00 ₪", R.drawable.furniture_table2));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter(items, product -> {
            Intent intent = new Intent(Prudect.this, PrudectDetails.class);
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
