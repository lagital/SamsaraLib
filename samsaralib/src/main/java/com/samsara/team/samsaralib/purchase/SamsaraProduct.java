package com.samsara.team.samsaralib.purchase;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pborisenko on 6/26/2016.
 */
public class SamsaraProduct implements Parcelable{

    private static final String TAG = "SamsaraProduct";
    private static Parcelable.Creator<SamsaraProduct> CREATOR;

    private String ProductId;
    private String Price;
    private String PriceCurrencyCode;
    private String PriceAmountMicros;
    private String Type;
    private String Title;
    private String Description;

    private double PriceNumeric;
    private double PriceMicrosNumeric;

    public SamsaraProduct(String i_productId,
                          String i_price,
                          String i_price_currency_code,
                          String i_price_amount_micros,
                          String i_type,
                          String i_title,
                          String i_description) {
        ProductId = i_productId;
        Price = i_price;
        PriceCurrencyCode = i_price_currency_code;
        PriceAmountMicros = i_price_amount_micros;
        Type = i_type;
        Title = i_title;
        Description = i_description;

        PriceNumeric = Double.parseDouble(Price);
        PriceMicrosNumeric = Double.parseDouble(PriceAmountMicros);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPriceCurrencyCode() {
        return PriceCurrencyCode;
    }

    public void setPriceCurrencyCode(String priceCurrencyCode) {
        PriceCurrencyCode = priceCurrencyCode;
    }

    public String getPriceAmountMicros() {
        return PriceAmountMicros;
    }

    public void setPriceAmountMicros(String priceAmountMicros) {
        PriceAmountMicros = priceAmountMicros;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPriceNumeric() {
        return PriceNumeric;
    }

    public void setPriceNumeric(double priceNumeric) {
        PriceNumeric = priceNumeric;
    }

    public double getPriceMicrosNumeric() {
        return PriceMicrosNumeric;
    }

    public void setPriceMicrosNumeric(double priceMicrosNumeric) {
        PriceMicrosNumeric = priceMicrosNumeric;
    }
}
