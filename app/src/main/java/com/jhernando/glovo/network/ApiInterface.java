package com.jhernando.glovo.network;

import com.jhernando.glovo.model.Business;
import com.jhernando.glovo.model.Category;
import com.jhernando.glovo.model.Order;
import com.jhernando.glovo.model.OrderLine;
import com.jhernando.glovo.model.Product;
import com.jhernando.glovo.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("users/login")
    Call<User> userLogin(@Body User user);

    @PUT("users/register")
    Call<User> userRegister(@Body User user);

    @POST("users/update")
    Call<User> userUpdate(@Body User user);

    @GET("businesses/name/{name}")
    Call<ArrayList<Business>> getSearchBusinesses(@Path("name") String name);

    @GET("businesses/category/{id}")
    Call<ArrayList<Business>> getBusinesses(@Path("id") int id);

    @GET("businesses/category/{id}/{name}")
    Call<ArrayList<Business>> getSearchBusinessesCategory(@Path("id") int id, @Path("name") String name);

    @GET("products/business/{id}")
    Call<ArrayList<Product>> getProducts(@Path("id") int id);

    @GET("products/business/{id}/{name}")
    Call<ArrayList<Product>> getSearchProducts(@Path("id") int id, @Path("name") String name);

    @GET("orders/user/{id}")
    Call<ArrayList<Order>> getOrdersUser(@Path("id") int userId);

    @PUT("orders/addorder")
    Call<Order> addOrders(@Body Order order);

    @POST("orders/updateorder")
    Call<Order> orderUpdate(@Body Order order);

    @PUT("orderlines/addorderlines")
    Call<Order> addOrdersLine(@Query("id") int id, @Body ArrayList<Product> products);

    @GET("orderdetails/order/{id}")
    Call<ArrayList<OrderLine>> getOrderLines(@Path("id") int id);


}
