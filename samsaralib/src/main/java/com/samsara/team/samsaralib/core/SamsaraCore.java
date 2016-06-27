package com.samsara.team.samsaralib.core;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by pborisenko on 6/27/2016.
 */
public class SamsaraCore {
    private static final String TAG = "SamsaraCore";

    public static void setLocale (Context context, String language_code, Integer resource) {

        if (resource != null) {
            language_code = context.getResources().getString(resource);
        }

        Resources res = context.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(language_code.toLowerCase());
        res.updateConfiguration(conf, dm);

    }
}
