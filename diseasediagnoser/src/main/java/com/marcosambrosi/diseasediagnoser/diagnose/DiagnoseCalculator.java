package com.marcosambrosi.diseasediagnoser.diagnose;

import com.marcosambrosi.diseasediagnoser.criteria.DiagnoseCriteria;

import java.util.List;

/**
 * Created by marcosambrosi on 9/17/16.
 */

public interface DiagnoseCalculator {

    void setDiagnoseCriteria(List<DiagnoseCriteria> diagnoseCriteriaList);
    int getDiagnoseProbability();
}
