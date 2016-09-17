package com.mambrosi.todddiagnoser.presentation.main;

import android.content.Context;

import com.mambrosi.todddiagnoser.presentation.common.BasePresenter;
import com.mambrosi.todddiagnoser.presentation.common.BaseView;
import com.mambrosi.todddiagnoser.presentation.question.QuestionViewModel;

import java.util.List;

/**
 * Created by marcosambrosi on 9/18/16.
 */

public interface MainContract {

    interface View extends BaseView<Presenter> {
        void setQuestions(List<QuestionViewModel> questionViewModelList);
        Context getContext();
        void showError(String error);
        void moveToNextFragment();
        void showDiagnoseResult(List<QuestionViewModel> questionViewModelList);
    }

    interface Presenter extends BasePresenter {
        void loadQuestions();
        void answerQuestion(int questionIndex, boolean isPositiveAnswer);
    }
}
