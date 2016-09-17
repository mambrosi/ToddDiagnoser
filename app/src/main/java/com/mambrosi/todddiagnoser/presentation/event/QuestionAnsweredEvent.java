package com.mambrosi.todddiagnoser.presentation.event;

/**
 * Created by marcosambrosi on 9/18/16.
 */

public class QuestionAnsweredEvent {

    private final int questionIndex;
    private final boolean isPositiveAnswer;

    public QuestionAnsweredEvent(int questionIndex, boolean isPositiveAnswer) {
        this.questionIndex = questionIndex;
        this.isPositiveAnswer = isPositiveAnswer;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public boolean isPositiveAnswer() {
        return isPositiveAnswer;
    }
}
