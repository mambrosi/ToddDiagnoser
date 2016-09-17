package com.mambrosi.todddiagnoser.presentation.diagnoseresult;

import com.mambrosi.todddiagnoser.presentation.common.BasePresenter;
import com.mambrosi.todddiagnoser.presentation.common.BaseView;
import com.mambrosi.todddiagnoser.presentation.question.QuestionViewModel;

import java.util.List;

/**
 * Created by marcosambrosi on 9/17/16.
 */

public interface DiagnoseResultsContract {

    interface View extends BaseView<Presenter> {
        void showNoResultsError();

        void showLatestDiagnoses(List<DiagnoseResultsViewModel> diagnoseResultsViewModels);

        void showCurrentDiagnoseResult(int diagnoseResultPercentage);
    }

    interface Presenter extends BasePresenter {
        void saveDiagnose(DiagnoseResultsViewModel diagnoseViewModel);

        void loadCurrentDiagnose(List<QuestionViewModel> questionViewModelList);

        void loadLatestDiagnoses();
    }
}
