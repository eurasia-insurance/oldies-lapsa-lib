package test.validationMessages;

import com.lapsa.validation.TemporalFuture;
import com.lapsa.validation.TemporalLeftAfterRight;
import com.lapsa.validation.TemporalLeftBeforeRight;
import com.lapsa.validation.TemporalPast;
import com.lapsa.validation.DaysAfterNow;
import com.lapsa.validation.DaysBeforeNow;
import com.lapsa.validation.NotEmptyString;
import com.lapsa.validation.NotNullValue;
import com.lapsa.validation.NotZeroAmount;
import com.lapsa.validation.ValidDateOfBirth;
import com.lapsa.validation.ValidDateOfIssue;
import com.lapsa.validation.ValidHumanName;
import com.lapsa.validation.ValidURI;

public interface DummyAnnotated {

    @TemporalFuture
    @TemporalLeftAfterRight
    @TemporalLeftBeforeRight
    @TemporalPast
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
    @ValidHumanName
    @ValidURI
    void dummy();
}
