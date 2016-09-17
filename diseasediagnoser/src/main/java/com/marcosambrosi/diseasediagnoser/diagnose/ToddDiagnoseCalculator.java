package com.marcosambrosi.diseasediagnoser.diagnose;

import com.marcosambrosi.diseasediagnoser.criteria.DiagnoseCriteria;

import java.util.List;

/**
 * Created by marcosambrosi on 9/17/16.
 */

public class ToddDiagnoseCalculator implements DiagnoseCalculator {

    List<DiagnoseCriteria> diagnoseCriteriaList;

    @Override
    public void setDiagnoseCriteria(List<DiagnoseCriteria> diagnoseCriteriaList) {
        this.diagnoseCriteriaList = diagnoseCriteriaList;
    }

    @Override
    public int getDiagnoseProbability() {
        int totalWeight = 0;
        for (DiagnoseCriteria diagnoseCriteria : diagnoseCriteriaList) {
            if (diagnoseCriteria.meetCriteria()) totalWeight += diagnoseCriteria.getWeight();
        }
        return totalWeight;
    }
}
