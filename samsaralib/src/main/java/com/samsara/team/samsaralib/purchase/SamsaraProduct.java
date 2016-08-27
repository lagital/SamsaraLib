package com.samsara.team.samsaralib.purchase;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pborisenko on 6/26/2016.
 */
public class SamsaraProduct implements Parcelable{

    private static final String TAG = "SamsaraProduct";
    private static Parcelable.Creator<SamsaraProduct> CREATOR;

    public String productId;
    public String price;
    public String price_currency_code;
    public String price_amount_micros;
    public String type;
    public String title;
    public String description;

    public double price_numeric;
    public double price_micros_numeric;

    public SamsaraProduct(String i_productId,
                          String i_price,
                          String i_price_currency_code,
                          String i_price_amount_micros,
                          String i_type,
                          String i_title,
                          String i_description) {
        productId = i_productId;
        price = i_price;
        price_currency_code = i_price_currency_code;
        price_amount_micros = i_price_amount_micros;
        type = i_type;
        title = i_title;
        description = i_description;

        price_numeric = Double.parseDouble(price);
        price_micros_numeric = Double.parseDouble(price_amount_micros);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
