package com.mambrosi.todddiagnoser.data.source;

import com.mambrosi.todddiagnoser.data.DiagnoseResultEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by marcosambrosi on 9/17/16.
 */

public interface DiagnoseDataSource {

    /**
     * Stores the given {@link DiagnoseResultEntity}
     *
     * @param diagnoseResultEntity diagnose result to be stored
     */
    void saveDiagnoseResult(DiagnoseResultEntity diagnoseResultEntity);

    /**
     * Returns all the stored results
     *
     * @return {@link Observable} which emits a list of the {@link DiagnoseResultEntity}
     * stored
     */
    Observable<List<DiagnoseResultEntity>> getAll();
}
