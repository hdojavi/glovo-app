package com.jhernando.glovo.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jhernando.glovo.R;
import com.jhernando.glovo.business.businessActivity;

public class HomeFragment extends Fragment {
    ImageView searchBtn;
    TextView searchQuery;

    ImageView supermarketBtn;
    ImageView breakfastBtn;
    ImageView restaurantBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_fragment, null);
        searchQuery = view.findViewById(R.id.searchQuery);
        searchBtn = view.findViewById(R.id.buttonSearch);
        supermarketBtn = view.findViewById(R.id.buttonSupermarket);
        restaurantBtn = view.findViewById(R.id.buttonRestaurant);
        breakfastBtn = view.findViewById(R.id.buttonBreakfast);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goSearch();
            }
        });
        supermarketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCategory(1);
            }
        });
        restaurantBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCategory(2);
            }
        });
        breakfastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goCategory(3);
            }
        });

        return view;
    }

    public void goSearch() {
        String query = searchQuery.getText().toString();
        if (query.isEmpty()) {
            Toast.makeText(getContext(), "Nada que buscar...", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(getContext(), businessActivity.class);
            intent.putExtra("id", 0);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    }

    public void goCategory(int id) {
        Intent intent = new Intent(getContext(), businessActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
