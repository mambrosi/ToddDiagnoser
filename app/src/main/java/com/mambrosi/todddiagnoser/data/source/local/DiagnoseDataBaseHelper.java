package com.mambrosi.todddiagnoser.data.source.local;

import com.mambrosi.todddiagnoser.data.DiagnoseResultEntity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;

/**
 * Created by marcosambrosi on 9/17/16.
 */

public class DiagnoseDataBaseHelper {

    private static DiagnoseDataBaseHelper INSTANCE;

    private Realm realm;

    private DiagnoseDataBaseHelper() {
        this.realm = Realm.getDefaultInstance();
    }

    public static DiagnoseDataBaseHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DiagnoseDataBaseHelper();
        }
        return INSTANCE;
    }

    public void save(DiagnoseResultEntity diagnoseResultEntity) {
        try {
            realm.beginTransaction();
            realm.copyToRealm(diagnoseResultEntity);
            realm.commitTransaction();
        } catch (Exception e) {
            realm.cancelTransaction();
        }
    }

    public Observable<List<DiagnoseResultEntity>> getAll() {
        RealmResults<DiagnoseResultEntity> all = realm.where(DiagnoseResultEntity.class).findAll()
                .sort("createdDate", Sort.DESCENDING);

        if (all.isValid()) {
            return Observable.just((List<DiagnoseResultEntity>) all);
        } else {
            return Observable.empty();
        }
    }


}
