package ch.zhaw.freelance4u.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Document("job")
public class Job {
    @Id
    private String id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private JobType jobType;
    @NonNull
    private JobState jobState = JobState.NEW;
    @NonNull
    private Double earnings;
    @NonNull
    private String companyId;

    private String freelancerId = "";
}
