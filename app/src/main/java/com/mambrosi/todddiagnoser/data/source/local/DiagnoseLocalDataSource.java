package com.mambrosi.todddiagnoser.data.source.local;

import android.support.annotation.NonNull;

import com.mambrosi.todddiagnoser.data.DiagnoseResultEntity;
import com.mambrosi.todddiagnoser.data.source.DiagnoseDataSource;

import java.util.List;

import rx.Observable;

/**
 * Created by marcosambrosi on 9/19/16.
 */

public class DiagnoseLocalDataSource implements DiagnoseDataSource {

    private static DiagnoseLocalDataSource INSTANCE;

    @NonNull
    private DiagnoseDataBaseHelper databaseHelper;

    private DiagnoseLocalDataSource() {
        databaseHelper = DiagnoseDataBaseHelper.getInstance();
    }

    public static DiagnoseLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DiagnoseLocalDataSource();
        }
        return INSTANCE;
    }


    @Override
    public void saveDiagnoseResult(DiagnoseResultEntity diagnoseResultEntity) {
        databaseHelper.save(diagnoseResultEntity);
    }

    @Override
    public Observable<List<DiagnoseResultEntity>> getAll() {
        return databaseHelper.getAll();
    }
}
