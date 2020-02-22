package com.jhernando.glovo.product;

import com.jhernando.glovo.model.Business;
import com.jhernando.glovo.model.Product;

import java.util.ArrayList;

public interface productsContract {

    interface Model {
        interface OnProductsListener {
            void onFinished(ArrayList<Product> products);

            void onFailure(Throwable t);
        }

        void getProducts(OnProductsListener OnProductsListener, int id);

        void getSearchProducts(OnProductsListener OnProductsListener, int id, String name);
    }

    interface View {
        void successProducts(ArrayList<Product> products);

        void failureProducts(Throwable t);

        void successSearch(ArrayList<Product> products);

        void failureSearch(Throwable t);

    }

    interface Presenter {
        void checkProducts(int id);

        void searchProducts(int id, String name);
    }

}
