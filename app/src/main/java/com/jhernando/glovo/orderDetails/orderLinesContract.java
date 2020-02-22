package com.jhernando.glovo.orderDetails;

import com.jhernando.glovo.model.Order;
import com.jhernando.glovo.model.OrderLine;
import com.jhernando.glovo.model.Product;

import java.util.ArrayList;

public interface orderLinesContract {

    interface Model {
        interface OnGetOrderLineListener {
            void onFinished(ArrayList<OrderLine> orderLines);

            void onFailure(Throwable t);
        }

        void getOrderLines(OnGetOrderLineListener OnGetOrderLineListener, int orderId);

    }

    interface View {
        void successOrderLine(ArrayList<OrderLine> orderLines);

        void failureOrderLine(Throwable t);

    }

    interface Presenter {
        void getOrderLines(int orderId);

    }

}
