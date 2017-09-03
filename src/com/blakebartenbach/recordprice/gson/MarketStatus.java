
package com.blakebartenbach.recordprice.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarketStatus {

    @SerializedName("BTC")
    @Expose
    private BTC bTC;
    @SerializedName("LTC")
    @Expose
    private LTC lTC;
    @SerializedName("ETH")
    @Expose
    private ETH eTH;

    public BTC getBTC() {
        return bTC;
    }

    public void setBTC(BTC bTC) {
        this.bTC = bTC;
    }

    public LTC getLTC() {
        return lTC;
    }

    public void setLTC(LTC lTC) {
        this.lTC = lTC;
    }

    public ETH getETH() {
        return eTH;
    }

    public void setETH(ETH eTH) {
        this.eTH = eTH;
    }

}
