package test.task.lender.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmploymentInformation {
    private Date employmentStartDate;
    private String post;
    private String organizationName;

    public EmploymentInformation(
            Date employmentStartDate,
            String post,
            String organizationName) {
        this.employmentStartDate = employmentStartDate;
        this.post = post;
        this.organizationName = organizationName;
    }
}
