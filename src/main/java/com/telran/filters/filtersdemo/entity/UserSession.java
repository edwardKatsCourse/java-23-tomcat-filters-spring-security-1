package com.telran.filters.filtersdemo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class UserSession {

    @Id
    private String id;

    @Indexed(unique = true)
    private String sessionId;

    @DBRef
    private User user;

    private boolean isValid;
}
