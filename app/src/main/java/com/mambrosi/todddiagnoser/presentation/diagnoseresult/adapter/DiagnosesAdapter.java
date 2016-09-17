package com.mambrosi.todddiagnoser.presentation.diagnoseresult.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mambrosi.todddiagnoser.R;
import com.mambrosi.todddiagnoser.presentation.diagnoseresult.DiagnoseResultsViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcosambrosi on 9/19/16.
 */

public class DiagnosesAdapter extends RecyclerView.Adapter<DiagnosesAdapter.DiagnoseViewHolder> {

    List<DiagnoseResultsViewModel> resultsViewModels;

    public DiagnosesAdapter() {
        this.resultsViewModels = new ArrayList<>();
    }

    @Override
    public DiagnoseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.row_diagnose_result, parent, false);
        DiagnoseViewHolder holder = new DiagnoseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DiagnoseViewHolder holder, int position) {
        DiagnoseResultsViewModel resultsViewModel = resultsViewModels.get(position);
        holder.tvDiagnosePercentage.setText(resultsViewModel.getDiagnoseResultPercentage() + "%");
        holder.tvCreatedAt.setText(resultsViewModel.getCreatedDate());
    }

    @Override
    public int getItemCount() {
        return resultsViewModels.size();
    }

    public void addAll(List<DiagnoseResultsViewModel> diagnoseResultsViewModels) {
        resultsViewModels.addAll(diagnoseResultsViewModels);
        notifyDataSetChanged();
    }

    public class DiagnoseViewHolder extends RecyclerView.ViewHolder {

        TextView tvDiagnosePercentage;
        TextView tvCreatedAt;

        public DiagnoseViewHolder(View itemView) {
            super(itemView);
            tvDiagnosePercentage = (TextView) itemView.findViewById(R.id.tvDiagnosePercentage);
            tvCreatedAt = (TextView) itemView.findViewById(R.id.tvDiagnoseCreatedDate);
        }
    }
}
