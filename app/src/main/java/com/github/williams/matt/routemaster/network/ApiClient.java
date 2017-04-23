package com.github.williams.matt.routemaster.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;


public class ApiClient {
    private static final String TAG = ApiClient.class.getSimpleName();

    private final Gson gson;
    private final RouteMasterService routeMasterService;

    public ApiClient(@NonNull RouteMasterService routeMasterService) {

        gson = new Gson();
        this.routeMasterService = routeMasterService;
    }

    public Observable<Object> updateUser(@NonNull String accountId, @NonNull String contractId) {
        return makeApiRequest(routeMasterService.updateUser(accountId, contractId));
    }

    public Observable<BeaconInfo> getAccount(@NonNull String contractId) {
        return makeApiRequest(routeMasterService.getAccount(contractId));
    }

    private <T> Observable<T> makeApiRequest(@NonNull final Call<T> call) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {

                    Response<T> response = call.execute();

                    if (response.isSuccessful()) {
                        subscriber.onNext(response.body());
                    } else {
                        Log.w(TAG, "Unsuccessful HTTP response (code " + response.code() + ")");


                        subscriber.onError(new Throwable("error"));
                    }
                } catch (Exception e) {
                    if (e instanceof IOException) {
                        Log.w(TAG, "Network request failed with unexpected exception");
                    }

                    subscriber.onError(e);
                }

                subscriber.onCompleted();
            }
        });
    }
}
