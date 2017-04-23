package com.github.williams.matt.routemaster.network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Hanson on 23/04/2017.
 */

interface RouteMasterService {

    @FormUrlEncoded
    @POST("/contracts/{contract}")
    Observable<Object> updateUser(
            @Field("account") String accountId,
            @Path("contract") String contractId);


    @GET("/contracts/{contract}")
    Observable<BeaconInfo> getAccount(@Path("contract") String contractId);
}
