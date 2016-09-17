package com.mambrosi.todddiagnoser.presentation.diagnoseresult;

/**
 * Created by marcosambrosi on 9/18/16.
 */

public class DiagnoseResultsViewModel {

    private int id;
    private int diagnoseResultPercentage;
    private String createdDate;


    public int getDiagnoseResultPercentage() {
        return diagnoseResultPercentage;
    }

    public void setDiagnoseResultPercentage(int diagnoseResultPercentage) {
        this.diagnoseResultPercentage = diagnoseResultPercentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
