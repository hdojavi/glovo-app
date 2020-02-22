package com.jhernando.glovo.orderProducts;

import com.jhernando.glovo.model.Order;
import com.jhernando.glovo.model.Product;

import java.util.ArrayList;

public class orderProductsPresenter implements orderProductsContract.Presenter {
    private orderProductsContract.View productsView;
    private orderProductsModel orderProductsModel;


    public orderProductsPresenter(orderProductsContract.View productsView) {
        this.productsView = productsView;
        orderProductsModel = new orderProductsModel();
    }

    @Override
    public void addOrder(Order order) {
        orderProductsModel.addOrder(
                new orderProductsContract.Model.OnAddOrderListener() {
                    @Override
                    public void onFinished(Order order) {
                        productsView.successAddOrder(order);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        productsView.failureAddOrder(t);
                    }
                }, order);
    }

    @Override
    public void addOrderLine(int id, ArrayList<Product> products) {
        orderProductsModel.addOrderLine(
                new orderProductsContract.Model.OnAddOrderLineListener() {
                    @Override
                    public void onFinished(Order order) {
                        productsView.successAddOrderLine(order);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        productsView.failureAddOrderLine(t);
                    }
                },id, products);
    }

    @Override
    public void updateOrder(Order order) {
        orderProductsModel.updateOrder(
                new orderProductsContract.Model.OnUpdateOrderListener() {
                    @Override
                    public void onFinished(Order order) {
                        productsView.successUpdateOrder(order);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        productsView.failureUpdateOrder(t);
                    }
                }, order);
    }
}
