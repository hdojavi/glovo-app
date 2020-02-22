package com.jhernando.glovo.orders;

import com.jhernando.glovo.model.Order;

import java.util.ArrayList;

public class ordersPresenter implements ordersContract.Presenter {
    private ordersContract.View ordersView;
    private ordersModel ordersModel;


    public ordersPresenter(ordersContract.View ordersView) {
        this.ordersView = ordersView;
        ordersModel = new ordersModel();
    }

    @Override
    public void checkOrders(int userId) {
        ordersModel.getOrders(
                new ordersContract.Model.OnOrdersListener() {
                    @Override
                    public void onFinished(ArrayList<Order> orders) {
                        ordersView.successOrders(orders);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        ordersView.failureOrders(t);
                    }
                }, userId);
    }

}
