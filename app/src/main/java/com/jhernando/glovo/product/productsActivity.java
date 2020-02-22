package com.jhernando.glovo.product;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jhernando.glovo.R;
import com.jhernando.glovo.adapter.ProductsAdapter;
import com.jhernando.glovo.model.Business;
import com.jhernando.glovo.model.Product;
import com.jhernando.glovo.orderProducts.orderProductsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class productsActivity extends Activity implements productsContract.View, productsItemClickListener {
    private SharedPreferences userDetails;
    private productsPresenter productsPresenter;
    private RecyclerView productsRecycler;
    private ArrayList<Product> products;
    private ProductsAdapter productsAdapter;
    private Business thisBusiness;

    private RecyclerView.LayoutManager mLayoutManager;

    private ImageView btnBack;
    private TextView textSearch;
    private TextView nameCard;
    private TextView priceCard;
    private TextView rateCard;
    private TextView kmCard;
    private ImageView imgBusinessProduct;
    private ImageView searchBtn;
    private TextView searchQuery;
    private Button buttonOrder;

    ArrayList<Product> productCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        btnBack = findViewById(R.id.arrowBack);
        textSearch = findViewById(R.id.searchQuery);
        nameCard = findViewById(R.id.nameCard);
        priceCard = findViewById(R.id.priceCard);
        rateCard = findViewById(R.id.rateCard);
        kmCard = findViewById(R.id.kmCard);
        imgBusinessProduct = findViewById(R.id.imgBusinessProduct);

        searchBtn = findViewById(R.id.buttonSearch);
        searchQuery = findViewById(R.id.searchQuery);
        buttonOrder = findViewById(R.id.buttonOrders);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSearch();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        userDetails = getSharedPreferences("userdetails", MODE_PRIVATE);

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userDetails.getInt("userId", 0) > 0) {
                    Intent intent = new Intent(getApplicationContext(), orderProductsActivity.class);
                    intent.putExtra("cart", productCart);
                    intent.putExtra("business", thisBusiness);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Necesitas una cuenta para pedir", Toast.LENGTH_SHORT).show();
                }
            }
        });

        initUI();

        productsPresenter = new productsPresenter(this);

        Intent intent = getIntent();
        thisBusiness = (Business) intent.getSerializableExtra("business");
        initCards();

        productsPresenter.checkProducts(thisBusiness.getId());
    }

    private void initUI() {
        productsRecycler = findViewById(R.id.ProductsRecycler);

        products = new ArrayList<>();
        productCart = new ArrayList<>();
        productsAdapter = new ProductsAdapter(this, products);

        mLayoutManager = new LinearLayoutManager(this);
        productsRecycler.setLayoutManager(mLayoutManager);
        productsRecycler.setItemAnimator(new DefaultItemAnimator());
        productsRecycler.setAdapter(productsAdapter);
    }

    private void initCards() {
        textSearch.setHint("Buscar en " + thisBusiness.getName() + "...");
        nameCard.setText(thisBusiness.getName());
        String shippingPrice = Double.toString(thisBusiness.getShippingPrice()).replace('.', ',') + "€";
        priceCard.setText(shippingPrice);
        rateCard.setText(thisBusiness.getRate() + "%");
        Double kmAway = ((double) thisBusiness.getKmAway() / 1000);
        if (kmAway > 1) {
            kmCard.setText(kmAway + "km");
        } else {
            kmCard.setText(thisBusiness.getKmAway() + "m");
        }
        Picasso.with(this).load(thisBusiness.getThumb()).into(imgBusinessProduct);
        imgBusinessProduct.setColorFilter(Color.rgb(130, 130, 130), PorterDuff.Mode.MULTIPLY);
    }

    private void goSearch() {
        String query = searchQuery.getText().toString();
        if (query.isEmpty()) {
            productsPresenter.checkProducts(thisBusiness.getId());
        } else {
            productsPresenter.searchProducts(thisBusiness.getId(), query);
        }
    }

    @Override
    public void successProducts(ArrayList<Product> products) {
        this.products.clear();
        this.products.addAll(products);
        productsAdapter.notifyDataSetChanged();
    }

    @Override
    public void failureProducts(Throwable t) {
        Toast.makeText(this, "Error en los productos...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successSearch(ArrayList<Product> products) {
        if (!products.isEmpty()) {
            this.products.clear();
            this.products.addAll(products);
            productsAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No hay coincidencias con tu busqueda...", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void failureSearch(Throwable t) {
        Toast.makeText(this, "Error en la busqueda...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addProductsItemClick(int position, TextView numberProduct, ImageView removeProduct) {
        productCart.add(products.get(position));

        int quantity = 0;
        for (Product p : productCart) {
            if (p.getId() == products.get(position).getId()) {
                numberProduct.setText(++quantity + "x");
            }
        }
        removeProduct.setVisibility(View.VISIBLE);

        int quantities = 0;
        double price = 0;
        for (Product p : productCart) {
            quantities++;
            price += p.getPrice();
        }
        buttonOrder.setText("PEDIR " + quantities + " POR " + String.format("%.2f", price).replace('.', ',') + " €");
        buttonOrder.setVisibility(View.VISIBLE);

    }

    @Override
    public void removeProductsItemClick(int position, TextView numberProduct, ImageView removeProduct) {
        productCart.remove(products.get(position));

        if (numberProduct.getText().toString().equals("1x")) {
            numberProduct.setText("");
            removeProduct.setVisibility(View.INVISIBLE);
        } else {
            numberProduct.setText(Integer.valueOf(numberProduct.getText().toString().substring(0, numberProduct.getText().length() - 1)) - 1 + "x");
        }

        if (productCart.isEmpty()) {
            buttonOrder.setVisibility(View.INVISIBLE);
        } else {
            int quantities = 0;
            double price = 0;
            for (Product p : productCart) {
                quantities++;
                price += p.getPrice();
            }
            buttonOrder.setText("PEDIR " + quantities + " POR " + String.format("%.2f", price).replace('.', ',') + " €");
        }
    }
}
