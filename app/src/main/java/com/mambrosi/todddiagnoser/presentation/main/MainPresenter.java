package com.mambrosi.todddiagnoser.presentation.main;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mambrosi.todddiagnoser.R;
import com.mambrosi.todddiagnoser.presentation.question.QuestionViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by marcosambrosi on 9/18/16.
 */

public class MainPresenter implements MainContract.Presenter {


    @NonNull
    private MainContract.View mainView;

    @NonNull
    List<QuestionViewModel> questionViewModels;


    public MainPresenter(@NonNull MainContract.View mainView) {
        checkNotNull(mainView);
        this.mainView = mainView;
        this.mainView.setPresenter(this);
    }

    @Override
    public void loadQuestions() {
        if (mainView != null && mainView.getContext() != null) {
            Context context = mainView.getContext();
            questionViewModels = new ArrayList<>(4);
            questionViewModels.add(new QuestionViewModel(0, context.getString(R.string.txt_q_has_migraine)));
            questionViewModels.add(new QuestionViewModel(1, context.getString(R.string.txt_q_is_older_15)));
            questionViewModels.add(new QuestionViewModel(2, context.getString(R.string.txt_q_is_man)));
            questionViewModels.add(new QuestionViewModel(3, context.getString(R.string.txt_q_uses_drugs)));
            mainView.setQuestions(questionViewModels);
        }
    }

    @Override
    public void answerQuestion(int index, boolean isPositiveAnswer) {
        if (questionViewModels == null || questionViewModels.isEmpty()) {
            if (mainView != null) {
                mainView.showError(mainView.getContext().getString(R.string.answer_error));
            }

        } else {

            questionViewModels.get(index).setPositiveAnswer(isPositiveAnswer);
            if (index == (questionViewModels.size() - 1)) {//If it is the last question show results
                mainView.showDiagnoseResult(questionViewModels);
            } else {
                //Sets the answer for the current question
                mainView.moveToNextFragment();
            }

        }


    }

    @Override
    public void start() {
        loadQuestions();
    }
}
