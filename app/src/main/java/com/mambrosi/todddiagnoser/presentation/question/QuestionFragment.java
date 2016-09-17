package com.mambrosi.todddiagnoser.presentation.question;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mambrosi.todddiagnoser.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by marcosambrosi on 9/17/16.
 */

public class QuestionFragment extends Fragment implements QuestionContract.View {


    public static final String KEY_INDEX = "index";
    public static final String KEY_QUESTION_TEXT = "questionText";

    @NonNull
    QuestionContract.Presenter presenter;

    private int questionIndex;
    private String questionText;

    @BindView(R.id.btnYes)
    Button btnYes;

    @BindView(R.id.btnNo)
    Button btnNo;

    @BindView(R.id.tvQuestionText)
    TextView tvQuestionText;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        questionIndex = getArguments().getInt(KEY_INDEX, 0);
        questionText = getArguments().getString(KEY_QUESTION_TEXT, "");

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.start();

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPositiveButtonClicked();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNegativeButtonClicked();
            }
        });
    }


    public static Fragment newInstance(int index, String questionText) {
        QuestionFragment questionFragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_INDEX, index);
        args.putString(KEY_QUESTION_TEXT, questionText);
        questionFragment.setArguments(args);
        return questionFragment;
    }

    @Override
    public void onPositiveButtonClicked() {
        presenter.setQuestionAnswered(questionIndex, true);
    }

    @Override
    public void onNegativeButtonClicked() {
        presenter.setQuestionAnswered(questionIndex, false);
    }

    @Override
    public void loadQuestionText() {
        tvQuestionText.setText(questionText);
    }

    @Override
    public void setPresenter(QuestionContract.Presenter presenter) {
        checkNotNull(presenter);
        this.presenter = presenter;
    }
}
