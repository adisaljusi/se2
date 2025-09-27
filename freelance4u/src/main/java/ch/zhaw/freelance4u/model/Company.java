package ch.zhaw.freelance4u.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Document("company")
public class Company {
    @Id
    private String id;
    @NonNull
    private final String name;
    @NonNull
    private final String email;
}
