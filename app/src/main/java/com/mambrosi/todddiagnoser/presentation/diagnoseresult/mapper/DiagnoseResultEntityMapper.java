package com.mambrosi.todddiagnoser.presentation.diagnoseresult.mapper;

import com.mambrosi.todddiagnoser.data.DiagnoseResultEntity;
import com.mambrosi.todddiagnoser.presentation.diagnoseresult.DiagnoseResultsViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcosambrosi on 9/19/16.
 */

public class DiagnoseResultEntityMapper {

    public static final SimpleDateFormat DATE_FORMAT_HOURS = new SimpleDateFormat("HH:mm");

    private static DiagnoseResultEntityMapper INSTANCE;

    private DiagnoseResultEntityMapper() {
    }

    public static DiagnoseResultEntityMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DiagnoseResultEntityMapper();
        }
        return INSTANCE;
    }

    public List<DiagnoseResultsViewModel> transform(List<DiagnoseResultEntity> entities) {
        List<DiagnoseResultsViewModel> diagnoseResultsViewModelList = new ArrayList<>(entities.size());
        for (DiagnoseResultEntity entity : entities) {
            diagnoseResultsViewModelList.add(transform(entity));
        }
        return diagnoseResultsViewModelList;
    }

    public DiagnoseResultsViewModel transform(DiagnoseResultEntity diagnoseResultEntity) {
        DiagnoseResultsViewModel diagnoseResultsViewModel = new DiagnoseResultsViewModel();
        diagnoseResultsViewModel
                .setDiagnoseResultPercentage(diagnoseResultEntity.getDiagnoseResultPercentage());
        diagnoseResultsViewModel.setCreatedDate(DATE_FORMAT_HOURS
                .format(diagnoseResultEntity.getCreatedDate()));
        return diagnoseResultsViewModel;
    }
}
