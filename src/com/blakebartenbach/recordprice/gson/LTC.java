
package com.blakebartenbach.recordprice.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LTC {

    @SerializedName("USD")
    @Expose
    private Double uSD;

    public Double getUSD() {
        return uSD;
    }

    public void setUSD(Double uSD) {
        this.uSD = uSD;
    }

}
