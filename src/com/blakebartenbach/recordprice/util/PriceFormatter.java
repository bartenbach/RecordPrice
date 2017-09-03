package com.blakebartenbach.recordprice.util;

import java.text.DecimalFormat;

public class PriceFormatter {


    public static String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(price);
    }
}
