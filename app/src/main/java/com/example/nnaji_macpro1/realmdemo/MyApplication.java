package com.example.nnaji_macpro1.realmdemo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Nnaji-MacPro1 on 10/9/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Initiate Realm Library
        Realm.init(this);
        RealmConfiguration configuration= new RealmConfiguration.Builder()
                .name ("myFirstRealm.realm")
                .build();

    }
}
