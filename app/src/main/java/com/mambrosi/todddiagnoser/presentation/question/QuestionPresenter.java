package com.mambrosi.todddiagnoser.presentation.question;

import android.support.annotation.NonNull;

import com.mambrosi.todddiagnoser.presentation.event.QuestionAnsweredEvent;

import org.greenrobot.eventbus.EventBus;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by marcosambrosi on 9/17/16.
 */

public class QuestionPresenter implements QuestionContract.Presenter {

    @NonNull
    QuestionContract.View questionView;

    public QuestionPresenter(QuestionContract.View questionView) {
        checkNotNull(questionView);
        this.questionView = questionView;
        this.questionView.setPresenter(this);
    }


    @Override
    public void setQuestionAnswered(int questionIndex, boolean isPositiveAnswer) {
        //Notifies that a question was answered
        EventBus.getDefault().post(new QuestionAnsweredEvent(questionIndex, isPositiveAnswer));
    }

    @Override
    public void start() {
        questionView.loadQuestionText();
    }
}
