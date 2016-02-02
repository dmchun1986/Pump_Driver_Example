package com.hypodiabetic.pumpdriverexample;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

/**
 * Created by Tim on 13/01/2016.
 */
public class MainApp extends Application {

    private static MainApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        //Manually initialize ActiveAndroid
        // TODO: 05/11/2015 appears to be a bug in Active Android where DB version is ignored in Manifest, must be added here as well
        // http://stackoverflow.com/questions/33164456/update-existing-database-table-with-new-column-not-working-in-active-android
        Configuration configuration = new Configuration.Builder(this).setDatabaseVersion(2).create();
        ActiveAndroid.initialize(configuration); //// TODO: 06/01/2016 change to this?

        //connect_to_HAPP(this);

    }




    public static MainApp instance() {
        return sInstance;
    }


}
