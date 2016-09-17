package com.mambrosi.todddiagnoser.presentation.question;

import com.mambrosi.todddiagnoser.presentation.common.BasePresenter;
import com.mambrosi.todddiagnoser.presentation.common.BaseView;

/**
 * Created by marcosambrosi on 9/17/16.
 */

public class QuestionContract {

    public interface View extends BaseView<Presenter> {
        void onPositiveButtonClicked();
        void onNegativeButtonClicked();
        void loadQuestionText();
    }

    interface Presenter extends BasePresenter {
        void setQuestionAnswered(int questionIndex, boolean isPositiveAnswer);
    }
}
