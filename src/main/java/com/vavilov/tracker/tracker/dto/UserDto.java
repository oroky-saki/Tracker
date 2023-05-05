package com.vavilov.tracker.tracker.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class UserDto {
    private String email;
    private String password;
    private String role;
}
