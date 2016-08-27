package com.samsara.team.samsaralib.purchase;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import com.android.vending.billing.IInAppBillingService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by pborisenko on 6/26/2016.
 */
public class SamsaraPurchase {

    private static final String TAG = "SamsaraPurchase";

    public static final String INAPP                    = "inapp";
    public static final String SUBSCRIPTION             = "subs";

    public static final String JSON_PRODUCT_ID          = "productId";
    public static final String JSON_PRICE               = "price";
    public static final String JSON_PRICE_CURRENCY_CODE = "price_currency_code";
    public static final String JSON_AMOUUNT_MICROS      = "price_amount_micros";
    public static final String JSON_TYPE                = "type";
    public static final String JSON_TITLE               = "title";
    public static final String JSON_DESCRIPTION         = "description";

    public static final String MART_LIST                = "MART_LIST";

    private static final String DEVELOPER_PAYLOAD_TEMPLATE
            = "abcdefghijklmnopqrstuvwxyz1234567890/+-()&#@%!";

    private static String generateDeveloperPayload(Random rng, String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }

    public static String makePurchase (Activity activity, String sku, IInAppBillingService service,
                                        Integer requestCode) {
        Log.d(TAG, "makePurchase");

        try {
            String developerPayloadString = generateDeveloperPayload(new Random(),
                    DEVELOPER_PAYLOAD_TEMPLATE,
                    20);
            Log.d(TAG, "buyIntentBundle: " + activity.getPackageName() + " " + sku);
            Bundle buyIntentBundle = service.getBuyIntent(3, activity.getPackageName(),
                    sku, "inapp", developerPayloadString);
            PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");
            activity.startIntentSenderForResult(pendingIntent.getIntentSender(),
                    requestCode, new Intent(), 0, 0, 0);
            return developerPayloadString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bundle getSkuDetails (Context context, Bundle skuDetails, ArrayList<String> skuList,
                                  IInAppBillingService service) {
        Log.d(TAG, "getSkuDetails");

        Bundle querySkus = new Bundle();
        querySkus.putStringArrayList("ITEM_ID_LIST", skuList);

        try {
            skuDetails = service.getSkuDetails(3,
                    context.getPackageName(), "inapp", querySkus);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
        return skuDetails;
    }

    public static Bundle getMart (ArrayList<String> responseList, ArrayList<SamsaraProduct> mart) {
        Log.d(TAG, "getMart");

        for (String thisResponse : responseList) {
            try {
                JSONObject object = new JSONObject(thisResponse);
                mart.add(new SamsaraProduct(
                        object.getString(JSON_PRODUCT_ID),
                        object.getString(JSON_PRICE),
                        object.getString(JSON_PRICE_CURRENCY_CODE),
                        object.getString(JSON_AMOUUNT_MICROS),
                        object.getString(JSON_TYPE),
                        object.getString(JSON_TITLE),
                        object.getString(JSON_DESCRIPTION)));
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        Bundle b = new Bundle();
        b.putParcelableArrayList(MART_LIST, mart);

        return b;
    }

    public static void consumePurchase (Context context, String token, IInAppBillingService service) {
        Log.d(TAG, "consumePurchase");

        ConsumeTask consumeTask = new ConsumeTask(context, token, service);
        consumeTask.execute();
    }
}