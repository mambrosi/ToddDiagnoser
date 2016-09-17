package com.marcosambrosi.diseasediagnoser.criteria;

/**
 * Created by marcosambrosi on 9/17/16.
 */

/**
 * Represents a criteria used to determine the accuracy level for a diagnosis
 */
public interface DiagnoseCriteria {

    public static int DEFAULT_WEIGHT = 25;

    /**
     * Weight used to calculate the accuracy level for this criteria
     * @return the weight for this criteria
     */
    int getWeight();

    /**
     * Determine whether this criteria will be taken into account
     * when determining the accuracy level
     * If the result is false, this criteria won't be taken into account
     * If the result is true, this criteria will use the weight value for the total accuracy
     * level value
     * @return
     */
    boolean meetCriteria();
}
