package com.project.user.service.payload;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ApiResponse<T> {

    private Boolean success;
    private String message;
    private T entity;
}
