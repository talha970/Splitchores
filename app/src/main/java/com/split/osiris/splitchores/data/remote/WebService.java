package com.split.osiris.splitchores.data.remote;

import com.split.osiris.splitchores.data.model.Group;
import com.split.osiris.splitchores.data.model.User;
import com.split.osiris.splitchores.data.model.api.LoginRequest;


import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebService {
    @POST("/user/login")
    Single<User> login(@Body LoginRequest request);

    @GET("/groups")
    Single<List<Group>> getGroups(@Query("id") List<String> grpIds);

}
