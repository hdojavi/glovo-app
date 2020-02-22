package com.jhernando.glovo.product;

import com.jhernando.glovo.model.Business;
import com.jhernando.glovo.model.Product;

import java.util.ArrayList;

public class productsPresenter implements productsContract.Presenter {
    private productsContract.View productsView;
    private productsModel productsModel;


    public productsPresenter(productsContract.View productsView) {
        this.productsView = productsView;
        productsModel = new productsModel();
    }

    @Override
    public void checkProducts(int id) {
        productsModel.getProducts(
                new productsContract.Model.OnProductsListener() {
                    @Override
                    public void onFinished(ArrayList<Product> products) {
                        productsView.successProducts(products);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        productsView.failureProducts(t);
                    }
                }, id);
    }

    @Override
    public void searchProducts(int id, String name) {
        productsModel.getSearchProducts(
                new productsContract.Model.OnProductsListener() {
                    @Override
                    public void onFinished(ArrayList<Product> products) {
                        productsView.successSearch(products);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        productsView.failureSearch(t);
                    }
                }, id, name);
    }
}
