package com.jhernando.glovo.orders;

import com.jhernando.glovo.model.Order;
import com.jhernando.glovo.model.Product;

import java.util.ArrayList;

public interface ordersContract {

    interface Model {
        interface OnOrdersListener {
            void onFinished(ArrayList<Order> orders);

            void onFailure(Throwable t);
        }

        void getOrders(OnOrdersListener OnOrdersListener, int userId);

    }

    interface View {
        void successOrders(ArrayList<Order> orders);

        void failureOrders(Throwable t);

    }

    interface Presenter {
        void checkOrders(int userId);
    }

}
