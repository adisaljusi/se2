package ch.zhaw.freelance4u.model;

import java.util.Collection;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JobStateAggregationDTO {
    private String id;
    private String count;
    private Collection<String> jobIds;
}
