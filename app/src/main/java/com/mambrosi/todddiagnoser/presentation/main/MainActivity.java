package com.mambrosi.todddiagnoser.presentation.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.mambrosi.todddiagnoser.R;
import com.mambrosi.todddiagnoser.data.source.DiagnoseRepository;
import com.mambrosi.todddiagnoser.presentation.diagnoseresult.DiagnoseResultsFragment;
import com.mambrosi.todddiagnoser.presentation.diagnoseresult.DiagnoseResultsPresenter;
import com.mambrosi.todddiagnoser.presentation.event.QuestionAnsweredEvent;
import com.mambrosi.todddiagnoser.presentation.question.QuestionContract;
import com.mambrosi.todddiagnoser.presentation.question.QuestionFragment;
import com.mambrosi.todddiagnoser.presentation.question.QuestionPresenter;
import com.mambrosi.todddiagnoser.presentation.question.QuestionViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;


public class MainActivity extends AppCompatActivity implements MainContract.View {

    @NonNull
    MainContract.Presenter presenter;

    @BindView(R.id.vpQuestions)
    ViewPager viewPagerQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //Disable touch scroll on view pager
        viewPagerQuestions.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        viewPagerQuestions.setOffscreenPageLimit(1);

        new MainPresenter(this); //Initialize main activity presenter

        presenter.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void setQuestions(List<QuestionViewModel> questions) {
        QuestionsFragmentAdapter questionsFragmentAdapter =
                new QuestionsFragmentAdapter(getSupportFragmentManager(),
                        questions);
        viewPagerQuestions.setAdapter(questionsFragmentAdapter);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void moveToNextFragment() {
        int currentIndex = viewPagerQuestions.getCurrentItem();
        int item = ++currentIndex;
        viewPagerQuestions.setCurrentItem(item);
    }

    @Override
    public void showDiagnoseResult(List<QuestionViewModel> questionViewModelList) {
        DiagnoseResultsFragment diagnoseResultsFragment = DiagnoseResultsFragment.newInstance();
        new DiagnoseResultsPresenter(diagnoseResultsFragment, DiagnoseRepository.getInstance(),
                questionViewModelList);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_main, diagnoseResultsFragment).commit();

    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        checkNotNull(presenter);
        this.presenter = presenter;
    }

    @Subscribe
    public void onQuestionAnswered(QuestionAnsweredEvent questionAnsweredEvent) {
        presenter.answerQuestion(questionAnsweredEvent.getQuestionIndex(),
                questionAnsweredEvent.isPositiveAnswer());
    }

    public static class QuestionsFragmentAdapter extends FragmentPagerAdapter {

        private final List<QuestionViewModel> mQuestionList;

        public QuestionsFragmentAdapter(FragmentManager fragmentManager,
                                        List<QuestionViewModel> questionViewModelList) {
            super(fragmentManager);
            this.mQuestionList = questionViewModelList;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return mQuestionList.size();
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {

            Fragment fragment = QuestionFragment.newInstance(mQuestionList.get(position).getPosition(),
                    mQuestionList.get(position).getText());
            //Create presenter for fragment
            new QuestionPresenter((QuestionContract.View) fragment);
            return fragment;
        }

    }
}
