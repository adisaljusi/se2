package ch.zhaw.freelance4u.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JobCreateDTO {
    private String title;
    private String description;
    private JobType jobType;
    private double earnings;
    private String companyId;
}
