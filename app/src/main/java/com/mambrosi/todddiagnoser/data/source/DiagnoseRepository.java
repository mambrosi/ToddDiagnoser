package com.mambrosi.todddiagnoser.data.source;

import android.support.annotation.NonNull;

import com.mambrosi.todddiagnoser.data.DiagnoseResultEntity;
import com.mambrosi.todddiagnoser.data.source.local.DiagnoseLocalDataSource;

import java.util.List;

import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by marcosambrosi on 9/17/16.
 */

public class DiagnoseRepository implements DiagnoseDataSource {

    private static DiagnoseRepository INSTANCE;

    @NonNull
    private DiagnoseDataSource diagnoseRepository;

    private DiagnoseRepository() {
    }

    public static DiagnoseRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DiagnoseRepository();
            //Set local repository as it is the only one existent for now
            //If a remote one is needed, it can be done by changing this line
            INSTANCE.setDiagnoseRepository(DiagnoseLocalDataSource.getInstance());
        }
        return INSTANCE;
    }


    @Override
    public void saveDiagnoseResult(DiagnoseResultEntity diagnoseResultEntity) {
        diagnoseRepository.saveDiagnoseResult(diagnoseResultEntity);
    }

    @Override
    public Observable<List<DiagnoseResultEntity>> getAll() {
        return diagnoseRepository.getAll();
    }

    @NonNull
    public DiagnoseDataSource getDiagnoseRepository() {
        return diagnoseRepository;
    }

    public void setDiagnoseRepository(@NonNull DiagnoseDataSource diagnoseRepository) {
        checkNotNull(diagnoseRepository);
        this.diagnoseRepository = diagnoseRepository;
    }
}
