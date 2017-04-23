package com.github.williams.matt.routemaster.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Hanson on 23/04/2017.
 */

class BeaconInfo {

    @SerializedName("account") String account;

    public String getAccount() {
        return account;
    }

}
