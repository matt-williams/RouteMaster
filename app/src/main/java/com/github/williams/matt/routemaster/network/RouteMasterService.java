package com.github.williams.matt.routemaster.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Hanson on 23/04/2017.
 */

interface RouteMasterService {

    @POST("/contracts/{contract}")
    Observable<Object> updateUser(
            @Path("contract") String contractId,
            @Query("account") String accountId);


    @GET("/contracts/{contract}")
    Observable<BeaconInfo> getAccount(@Path("contract") String contractId);
}
