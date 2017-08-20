package com.example.jurgen.petlistdb;

import com.example.jurgen.petlistdb.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by jmone on 5/26/17.
 */

public interface UserApi {

    static final String BASE_URL = "http://hodor.ait.gr:8080/myPets/api/";
    static final String USER_URL = "user/";

    @GET("user/{username}/{password}")
    Call<User> getUser(@Path("username") String username, @Path("password") String password);

    @POST(USER_URL)
    Call<Void> createUser(@Body User user);



}
