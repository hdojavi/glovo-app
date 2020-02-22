package com.jhernando.glovo.product;

import android.widget.ImageView;
import android.widget.TextView;

public interface productsItemClickListener {
    void addProductsItemClick(int position, TextView textView, ImageView imageView);
    void removeProductsItemClick(int position, TextView textView, ImageView imageView);
}
