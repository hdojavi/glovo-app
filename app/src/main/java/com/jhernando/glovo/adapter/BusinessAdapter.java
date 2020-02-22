package com.jhernando.glovo.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jhernando.glovo.R;
import com.jhernando.glovo.business.businessActivity;
import com.jhernando.glovo.model.Business;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder> {

    private ArrayList<Business> businesses;
    private int card;
    private businessActivity businessesActivity;

    public BusinessAdapter(businessActivity businessesActivity, ArrayList<Business> businesses) {
        this.businesses = businesses;
        card = R.layout.card_business;
        this.businessesActivity = businessesActivity;
    }

    @NonNull
    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(card, viewGroup, false);
        return new BusinessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessAdapter.BusinessViewHolder holder, final int position) {
        Business business = businesses.get(position);
        holder.nameCard.setText(business.getName());
        holder.descCard.setText(business.getDescription());
        String shippingPrice = String.format("%.2f", business.getShippingPrice()).replace('.', ',') + "€";
        holder.priceCard.setText(shippingPrice);
        holder.rateCard.setText(business.getRate() + "%");
        Double kmAway = ((double) business.getKmAway() / 1000);
        if (kmAway > 1) {
            holder.kmCard.setText(kmAway + "km");
        } else {
            holder.kmCard.setText(business.getKmAway() + "m");
        }
        Picasso.with(businessesActivity).load(business.getThumb()).into(holder.businessCard);
        holder.businessCard.setColorFilter(Color.rgb(170, 170, 170), PorterDuff.Mode.MULTIPLY);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                businessesActivity.onBusinessItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return businesses.size();
    }

    public class BusinessViewHolder extends RecyclerView.ViewHolder {
        private ImageView businessCard;
        private TextView nameCard;
        private TextView descCard;
        private TextView priceCard;
        private TextView rateCard;
        private TextView kmCard;


        public BusinessViewHolder(@NonNull View itemView) {
            super(itemView);

            businessCard = itemView.findViewById(R.id.imgBusiness);
            nameCard = itemView.findViewById(R.id.nameCard);
            descCard = itemView.findViewById(R.id.descCard);
            priceCard = itemView.findViewById(R.id.priceCard);
            rateCard = itemView.findViewById(R.id.rateCard);
            kmCard = itemView.findViewById(R.id.kmCard);
        }
    }
}
