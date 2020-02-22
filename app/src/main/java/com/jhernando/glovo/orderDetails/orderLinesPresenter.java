package com.jhernando.glovo.orderDetails;


import com.jhernando.glovo.model.Order;
import com.jhernando.glovo.model.OrderLine;

import java.util.ArrayList;

public class orderLinesPresenter implements orderLinesContract.Presenter {
    private orderLinesContract.View productsView;
    private orderLinesModel orderLinesModel;


    public orderLinesPresenter(orderLinesContract.View productsView) {
        this.productsView = productsView;
        orderLinesModel = new orderLinesModel();
    }

    @Override
    public void getOrderLines(int orderId) {
        orderLinesModel.getOrderLines(
                new orderLinesContract.Model.OnGetOrderLineListener() {
                    @Override
                    public void onFinished(ArrayList<OrderLine> orderLine) {
                        productsView.successOrderLine(orderLine);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        productsView.failureOrderLine(t);
                    }
                }, orderId);
    }
}
