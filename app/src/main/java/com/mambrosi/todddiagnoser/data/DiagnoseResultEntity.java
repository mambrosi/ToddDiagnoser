package com.mambrosi.todddiagnoser.data;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by marcosambrosi on 9/17/16.
 */

public class DiagnoseResultEntity extends RealmObject {

    private int diagnoseResultPercentage;
    private Date createdDate;

    public int getDiagnoseResultPercentage() {
        return diagnoseResultPercentage;
    }

    public void setDiagnoseResultPercentage(int diagnoseResultPercentage) {
        this.diagnoseResultPercentage = diagnoseResultPercentage;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
