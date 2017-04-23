package com.github.williams.matt.routemaster.network;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Action1;


public class ApiClient {
    private static final String TAG = ApiClient.class.getSimpleName();
    private final  Retrofit retrofit;
    private RouteMasterService routeMasterService;

    public ApiClient() {

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("")
                .build();
    }

    public Observable getAccount(String contractId)
    {
        return routeMasterService.getAccount(contractId);
    }

}
