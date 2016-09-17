package com.mambrosi.todddiagnoser.presentation.diagnoseresult.mapper;

import com.mambrosi.todddiagnoser.data.DiagnoseResultEntity;
import com.mambrosi.todddiagnoser.presentation.diagnoseresult.DiagnoseResultsViewModel;

import java.util.Date;

/**
 * Created by marcosambrosi on 9/19/16.
 */

public class DiagnoseResultViewModelMapper {

    private static DiagnoseResultViewModelMapper INSTANCE;

    private DiagnoseResultViewModelMapper() {
    }

    public static DiagnoseResultViewModelMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DiagnoseResultViewModelMapper();
        }
        return INSTANCE;
    }

    public DiagnoseResultEntity transform(DiagnoseResultsViewModel diagnoseResultsViewModel) {
        DiagnoseResultEntity diagnoseResultEntity = new DiagnoseResultEntity();
        diagnoseResultEntity
                .setDiagnoseResultPercentage(diagnoseResultsViewModel.getDiagnoseResultPercentage());
        diagnoseResultEntity.setCreatedDate(new Date());
        return diagnoseResultEntity;
    }
}
