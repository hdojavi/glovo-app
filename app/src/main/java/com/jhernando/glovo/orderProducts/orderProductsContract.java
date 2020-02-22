package com.jhernando.glovo.orderProducts;

import com.jhernando.glovo.model.Order;
import com.jhernando.glovo.model.Product;

import java.util.ArrayList;

public interface orderProductsContract {

    interface Model {
        interface OnAddOrderListener {
            void onFinished(Order order);

            void onFailure(Throwable t);
        }

        interface OnAddOrderLineListener {
            void onFinished(Order order);

            void onFailure(Throwable t);
        }

        interface OnUpdateOrderListener {
            void onFinished(Order order);

            void onFailure(Throwable t);
        }

        void addOrder(OnAddOrderListener OnAddOrderListener, Order order);

        void addOrderLine(OnAddOrderLineListener OnAddOrderLineListener, int id, ArrayList<Product> products);

        void updateOrder(OnUpdateOrderListener OnUpdateOrderListener, Order order);
    }

    interface View {
        void successAddOrder(Order order);

        void failureAddOrder(Throwable t);

        void successAddOrderLine(Order order);

        void failureAddOrderLine(Throwable t);

        void successUpdateOrder(Order order);

        void failureUpdateOrder(Throwable t);
    }

    interface Presenter {
        void addOrder(Order order);

        void addOrderLine(int id, ArrayList<Product> products);

        void updateOrder(Order order);
    }

}
