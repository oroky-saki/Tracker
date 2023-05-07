package com.vavilov.tracker.tracker.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class UserDto {

    private String email;

    private String role;
}
