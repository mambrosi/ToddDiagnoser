package com.mambrosi.todddiagnoser;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by marcosambrosi on 9/17/16.
 */

public class ToddDiagnoserApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Create a RealmConfiguration that saves the Realm file in the app's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
