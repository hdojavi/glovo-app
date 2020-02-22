package com.jhernando.glovo.business;

import com.jhernando.glovo.model.Business;

import java.util.ArrayList;

public class businessPresenter implements businessContract.Presenter {
    private businessContract.View businessView;
    private businessModel businessModel;


    public businessPresenter(businessContract.View businessView) {
        this.businessView = businessView;
        businessModel = new businessModel();
    }

    @Override
    public void checkBusinesses(int id) {
        businessModel.getBusinesses(
                new businessContract.Model.OnBusinessListener() {
                    @Override
                    public void onFinished(ArrayList<Business> businesses) {
                        businessView.successBusiness(businesses);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        businessView.failureBusiness(t);
                    }
                }, id);
    }

    @Override
    public void searchBusinesses(String name) {
        businessModel.getSearchBusinesses(
                new businessContract.Model.OnBusinessListener() {
                    @Override
                    public void onFinished(ArrayList<Business> businesses) {
                        businessView.successSearchBusiness(businesses);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        businessView.failureSearchBusiness(t);
                    }
                }, name);
    }

    @Override
    public void searchBusinessesCategory(int id, String name) {
        businessModel.getSearchBusinessesCategory(
                new businessContract.Model.OnBusinessListener() {
                    @Override
                    public void onFinished(ArrayList<Business> businesses) {
                        businessView.successSearchBusinessesCategory(businesses);
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        businessView.failureSearchBusinessCategory(t);
                    }
                }, id, name);
    }
}
