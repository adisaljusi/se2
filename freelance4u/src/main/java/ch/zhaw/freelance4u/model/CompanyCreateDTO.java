package ch.zhaw.freelance4u.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CompanyCreateDTO {
    private String name;
    private String email;
}