package com.jhernando.glovo.business;

import com.jhernando.glovo.model.Business;

import java.util.ArrayList;

public interface businessContract {

    interface Model {
        interface OnBusinessListener {
            void onFinished(ArrayList<Business> businesses);

            void onFailure(Throwable t);
        }

        void getBusinesses(
                OnBusinessListener OnBusinessListener,
                int id);

        void getSearchBusinessesCategory(
                OnBusinessListener OnBusinessListener,
                int id, String name);

        void getSearchBusinesses(
                OnBusinessListener OnBusinessListener,
                String name);
    }

    interface View {
        void successBusiness(ArrayList<Business> businesses);

        void failureBusiness(Throwable t);

        void successSearchBusinessesCategory(ArrayList<Business> businesses);

        void failureSearchBusinessCategory(Throwable t);

        void successSearchBusiness(ArrayList<Business> businesses);

        void failureSearchBusiness(Throwable t);
    }

    interface Presenter {
        void checkBusinesses(int id);

        void searchBusinessesCategory(int id, String name);

        void searchBusinesses(String name);

    }

}
