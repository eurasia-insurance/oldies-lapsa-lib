package test.validationMessages;

import com.lapsa.validation.DateFuture;
import com.lapsa.validation.DateLeftAfterRight;
import com.lapsa.validation.DateLeftBeforeRight;
import com.lapsa.validation.DatePast;
import com.lapsa.validation.DaysAfterNow;
import com.lapsa.validation.DaysBeforeNow;
import com.lapsa.validation.NotEmptyString;
import com.lapsa.validation.NotNullValue;
import com.lapsa.validation.NotZeroAmount;
import com.lapsa.validation.ValidDateOfBirth;
import com.lapsa.validation.ValidDateOfIssue;
import com.lapsa.validation.ValidEnumerationValue;
import com.lapsa.validation.ValidHumanName;

public interface DummyAnnotated {

    @DateFuture
    @DateLeftAfterRight
    @DateLeftBeforeRight
    @DatePast
    @DaysAfterNow(5)
    @DaysBeforeNow(5)
    @NotEmptyString
    @NotNullValue
    // becasuse needs to know how to pass annotation values
    // to message interpolator
    // @NotTooOldYearOfIssue
    // @NotTooYoungYearOfIssue
    @NotZeroAmount
    @ValidDateOfBirth
    @ValidDateOfIssue
    @ValidEnumerationValue
    @ValidHumanName
    void dummy();
}
