package com.mambrosi.todddiagnoser.presentation.diagnoseresult;


import android.support.annotation.NonNull;
import android.util.Log;

import com.mambrosi.todddiagnoser.data.DiagnoseResultEntity;
import com.mambrosi.todddiagnoser.data.source.DiagnoseDataSource;
import com.mambrosi.todddiagnoser.presentation.diagnoseresult.mapper.DiagnoseResultEntityMapper;
import com.mambrosi.todddiagnoser.presentation.diagnoseresult.mapper.DiagnoseResultViewModelMapper;
import com.mambrosi.todddiagnoser.presentation.question.QuestionViewModel;
import com.marcosambrosi.diseasediagnoser.DiseaseDiagnoser;
import com.marcosambrosi.diseasediagnoser.criteria.DiagnoseCriteria;
import com.marcosambrosi.diseasediagnoser.criteria.DiagnoseCriteriaException;

import java.util.List;

import rx.functions.Action1;
import rx.functions.Func1;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by marcosambrosi on 9/17/16.
 */

public class DiagnoseResultsPresenter implements DiagnoseResultsContract.Presenter {


    @NonNull
    private final DiagnoseResultsContract.View view;
    @NonNull
    private final DiagnoseDataSource diagnoseDataSource;
    @NonNull
    private final List<QuestionViewModel> questionViewModelList;

    public DiagnoseResultsPresenter(@NonNull DiagnoseResultsContract.View view,
                                    @NonNull DiagnoseDataSource diagnoseDataSource,
                                    @NonNull List<QuestionViewModel> questionViewModelList) {
        checkNotNull(view);
        checkNotNull(diagnoseDataSource);
        checkNotNull(questionViewModelList);

        this.view = view;
        this.view.setPresenter(this);
        this.diagnoseDataSource = diagnoseDataSource;
        this.questionViewModelList = questionViewModelList;
    }

    @Override
    public void saveDiagnose(DiagnoseResultsViewModel diagnoseViewModel) {
        //Transforms the view model to an entity before saving
        diagnoseDataSource.saveDiagnoseResult(DiagnoseResultViewModelMapper
                .getInstance().transform(diagnoseViewModel));
    }

    @Override
    public void loadCurrentDiagnose(List<QuestionViewModel> questionViewModelList) {

        //Add the criteria to the library which will make the calculations
        for (final QuestionViewModel questionViewModel : questionViewModelList) {
            try {
                DiseaseDiagnoser.getInstance().addDiagnoseCriteria(new DiagnoseCriteria() {
                    @Override
                    public int getWeight() {
                        return DiagnoseCriteria.DEFAULT_WEIGHT;
                    }

                    @Override
                    public boolean meetCriteria() {
                        return questionViewModel.isPositiveAnswer();
                    }
                });
            } catch (DiagnoseCriteriaException e) {
                Log.e(DiagnoseResultsPresenter.class.getSimpleName(), e.getMessage());
            }
        }

        int diagnoseProbability = DiseaseDiagnoser.getInstance().getDiagnoseProbability();
        view.showCurrentDiagnoseResult(diagnoseProbability);

        DiagnoseResultsViewModel diagnoseResultsViewModel = new DiagnoseResultsViewModel();
        diagnoseResultsViewModel.setDiagnoseResultPercentage(diagnoseProbability);

        saveDiagnose(diagnoseResultsViewModel);
    }

    @Override
    public void loadLatestDiagnoses() {
        diagnoseDataSource.getAll()
                .map(new Func1<List<DiagnoseResultEntity>, List<DiagnoseResultsViewModel>>() {
                    @Override
                    public List<DiagnoseResultsViewModel>
                    call(List<DiagnoseResultEntity> diagnoseResultEntities) {
                        //Transforms entities to view models
                        return DiagnoseResultEntityMapper.getInstance().transform(diagnoseResultEntities);
                    }
                }).subscribe(new Action1<List<DiagnoseResultsViewModel>>() {
            @Override
            public void call(List<DiagnoseResultsViewModel> diagnoseResultsViewModels) {
                view.showLatestDiagnoses(diagnoseResultsViewModels);
            }
        });
    }

    @Override
    public void start() {
        loadLatestDiagnoses();
        loadCurrentDiagnose(questionViewModelList);
    }
}
