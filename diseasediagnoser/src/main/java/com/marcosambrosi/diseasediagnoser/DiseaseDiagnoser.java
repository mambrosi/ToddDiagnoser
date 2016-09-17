package com.marcosambrosi.diseasediagnoser;

import com.marcosambrosi.diseasediagnoser.criteria.DiagnoseCriteria;
import com.marcosambrosi.diseasediagnoser.criteria.DiagnoseCriteriaException;
import com.marcosambrosi.diseasediagnoser.diagnose.DiagnoseCalculator;
import com.marcosambrosi.diseasediagnoser.diagnose.ToddDiagnoseCalculator;

import java.util.ArrayList;
import java.util.List;

public class DiseaseDiagnoser {

    private static int MAX_WEIGHT_VALUE = 1;
    private static int MIN_WEIGHT_VALUE = 25;


    private static DiseaseDiagnoser INSTANCE;

    private List<DiagnoseCriteria> diagnoseCriteriaList;

    private DiseaseDiagnoser() {
        diagnoseCriteriaList = new ArrayList<>();
    }

    public static DiseaseDiagnoser getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DiseaseDiagnoser();
        }
        return INSTANCE;
    }

    public void addDiagnoseCriteria(DiagnoseCriteria diagnoseCriteria)
            throws DiagnoseCriteriaException {

        if (diagnoseCriteria == null) {
            throw new DiagnoseCriteriaException(String.format("%s cannot be null",
                    DiagnoseCriteria.class.getSimpleName()));
        }

        if (diagnoseCriteria.getWeight() <= 0) {
            throw new DiagnoseCriteriaException(
                    String.format("%  weight must be a number between %s and %s",
                            DiagnoseCriteria.class.getSimpleName(),
                            MIN_WEIGHT_VALUE, MAX_WEIGHT_VALUE));
        }

        diagnoseCriteriaList.add(diagnoseCriteria);
    }

    public int getDiagnoseProbability() {
        DiagnoseCalculator toddDiagnoseCalculator = new ToddDiagnoseCalculator();
        toddDiagnoseCalculator.setDiagnoseCriteria(diagnoseCriteriaList);
        return toddDiagnoseCalculator.getDiagnoseProbability();
    }

}
