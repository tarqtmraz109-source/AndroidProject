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

        setupFeaturedProducts(name, imageRes);
        setupBottomNav();
    }

    private void setupFeaturedProducts(String shopName, int imageRes) {
        RecyclerView recyclerView = findViewById(R.id.recyclerProducts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<Object> items = new ArrayList<>();

        if (shopName != null) {
            switch (shopName) {
                case "الأمل ماركت":
                    items.add(new Product("Tide مسحوق غسيل", shopName, "12.50 ₪", R.drawable.cleaning_detergent2));
                    items.add(new Product("حليب طازج", shopName, "5.50 ₪", R.drawable.dairy_milk));
                    items.add(new Product("جبنة بيضاء", shopName, "14.00 ₪", R.drawable.dairy_cheese));
                    break;
                case "الندى ماركت":
                    items.add(new Product("Tide سائل غسيل", shopName, "15.00 ₪", R.drawable.cleaning_detergent3));
                    items.add(new Product("زبادي كامل الدسم", shopName, "3.75 ₪", R.drawable.dairy_yogurt1));
                    items.add(new Product("Sunlight سائل", shopName, "8.00 ₪", R.drawable.cleaning_soap2));
                    break;
                case "سوق السلام":
                    items.add(new Product("معكرونة بريلا", shopName, "4.00 ₪", R.drawable.grocery_pasta));
                    items.add(new Product("ماء معدني", shopName, "1.50 ₪", R.drawable.drinck_water));
                    items.add(new Product("خضار مشكلة", shopName, "8.00 ₪", R.drawable.grocery_section));
                    break;
                case "الشام":
                    items.add(new Product("Head & Shoulders", shopName, "22.00 ₪", R.drawable.shampoo1));
                    items.add(new Product("Cibu شامبو", shopName, "17.50 ₪", R.drawable.shampoo2));
                    items.add(new Product("Soap صابون قطع", shopName, "3.50 ₪", R.drawable.soapbar1));
                    break;
                case "سوق غزة للمواد الغذائية":
                    items.add(new Product("زيت زيتون بكر", shopName, "35.00 ₪", R.drawable.grocery_oil));
                    items.add(new Product("أرز بسمتي", shopName, "12.00 ₪", R.drawable.grocery_rice));
                    items.add(new Product("بطاطا طازجة", shopName, "3.00 ₪", R.drawable.grocery_potato));
                    break;
                case "ملحمة غزة الطازجة":
                    items.add(new Product("لحم بقر طازج", shopName, "55.00 ₪", R.drawable.meat_beef));
                    items.add(new Product("دجاج كامل", shopName, "22.00 ₪", R.drawable.meat_chicken));
                    items.add(new Product("كفتة جاهزة", shopName, "28.00 ₪", R.drawable.meat_section));
                    break;
                case "الواحة":
                    items.add(new Product("كرسي مطبخ خشبي", shopName, "85.00 ₪", R.drawable.furniture_chair1));
                    items.add(new Product("نابض مكتب LED", shopName, "45.00 ₪", R.drawable.furniture_lamp1));
                    items.add(new Product("طاولة طعام 6 أشخاص", shopName, "350.00 ₪", R.drawable.furniture_table1));
                    break;
                case "القدس":
                    items.add(new Product("كرسي مكتب دوار", shopName, "150.00 ₪", R.drawable.furniture_chair2));
                    items.add(new Product("طاولة قهوة زجاج", shopName, "180.00 ₪", R.drawable.furniture_table2));
                    items.add(new Product("لمبة حائط ديكورية", shopName, "35.00 ₪", R.drawable.furniture_lamp3));
                    break;
                case "أثاث روتو":
                    items.add(new Product("كرسي استرخاء", shopName, "200.00 ₪", R.drawable.furniture_chair3));
                    items.add(new Product("نابض سقفي", shopName, "120.00 ₪", R.drawable.furniture_lamp2));
                    items.add(new Product("طاولة مكتب خشبية", shopName, "220.00 ₪", R.drawable.furniture_table3));
                    break;
                case "سوبر ماركت غزة ":
                    items.add(new Product("زبادي كامل الدسم", shopName, "3.75 ₪", R.drawable.dairy_yogurt1));
                    items.add(new Product("جبنة بيضاء", shopName, "14.00 ₪", R.drawable.dairy_cheese));
                    items.add(new Product("حليب طازج", shopName, "5.50 ₪", R.drawable.dairy_milk));
                    break;
            }
        }

        if (items.isEmpty()) {
            items.add(new Product("منتج مميز", shopName != null ? shopName : "المتجر", "XX.XX ₪", imageRes));
        }

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
