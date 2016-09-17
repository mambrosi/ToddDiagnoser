package com.mambrosi.todddiagnoser.presentation.question;

/**
 * Created by marcosambrosi on 9/17/16.
 */

public class QuestionViewModel{
    private int position;
    private String text;
    private boolean positiveAnswer;

    public QuestionViewModel(int position, String text) {
        this.position = position;
        this.text = text;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isPositiveAnswer() {
        return positiveAnswer;
    }

    public void setPositiveAnswer(boolean positiveAnswer) {
        this.positiveAnswer = positiveAnswer;
    }

}
