package com.g12.toddlearn.app;

import android.app.Application;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class MyApp extends Application {
    public static AtomicLong userID = new AtomicLong();
    //here you store the highest ID you have until that moment.
    public static AtomicLong childID = new AtomicLong();

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();

        Realm realm = Realm.getDefaultInstance();
        userID = getIdByTable(realm, UsersDB.class);
        childID = getIdByTable(realm,ChildsDB.class);
        realm.close(); //close when the app is closed for free resources.
    }

    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);


    }

    private <T extends RealmObject> AtomicLong getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new
                AtomicLong(results.max("id").intValue()) : new AtomicLong();
    }
}
