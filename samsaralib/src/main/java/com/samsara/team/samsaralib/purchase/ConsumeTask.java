package com.samsara.team.samsaralib.purchase;

import android.content.Context;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.util.Log;

import com.android.vending.billing.IInAppBillingService;

/**
 * Created by pborisenko on 8/28/2016.
 */
class ConsumeTask extends AsyncTask<Void,Void,Void> {
    private static final String TAG = "ConsumeTask";

    private Context mContext;
    private String mToken;
    private IInAppBillingService mService;

    public ConsumeTask (Context context, String token, IInAppBillingService service) {
        mToken = token;
        mService = service;
        mContext = context;
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        Log.d(TAG, "doInBackground");

        try {
            int response = mService.consumePurchase(3, mContext.getPackageName(), mToken);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
