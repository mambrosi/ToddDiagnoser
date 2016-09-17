package com.mambrosi.todddiagnoser.presentation.diagnoseresult;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mambrosi.todddiagnoser.R;
import com.mambrosi.todddiagnoser.presentation.diagnoseresult.adapter.DiagnosesAdapter;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class DiagnoseResultsFragment extends Fragment implements DiagnoseResultsContract.View {

    DiagnoseResultsContract.Presenter presenter;

    @BindView(R.id.rvLastDiagnoses)
    RecyclerView rvLastDiagnoses;

    @BindView(R.id.tvDiagnoseResult)
    TextView tvDiagnoseResult;

    @BindString(R.string.diagnose_is_to_be_positive)
    String diagnoseMessageFormat;

    DiagnosesAdapter diagnosesAdapter;

    public DiagnoseResultsFragment() {
        // Required empty public constructor
    }

    public static DiagnoseResultsFragment newInstance() {
        DiagnoseResultsFragment fragment = new DiagnoseResultsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.diagnose_results_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        rvLastDiagnoses.setLayoutManager(new LinearLayoutManager(getContext()));
        diagnosesAdapter = new DiagnosesAdapter();
        rvLastDiagnoses.setAdapter(diagnosesAdapter);

        presenter.start();
    }

    @Override
    public void setPresenter(@NonNull DiagnoseResultsContract.Presenter presenter) {
        checkNotNull(presenter);
        this.presenter = presenter;
    }

    @Override
    public void showNoResultsError() {

    }

    @Override
    public void showLatestDiagnoses(List<DiagnoseResultsViewModel> diagnoseResultsViewModels) {
        diagnosesAdapter.addAll(diagnoseResultsViewModels);
    }

    @Override
    public void showCurrentDiagnoseResult(int diagnoseResultPercentage) {
        tvDiagnoseResult.setText(String.format(diagnoseMessageFormat,
                diagnoseResultPercentage, "%"));
    }
}
