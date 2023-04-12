package com.training.jmp.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserRequestDto {

    private Long id;
    private String name;
    private String surname;
    private String birthday;

    @JsonCreator
    public UserRequestDto() {
    }

    @JsonCreator
    public UserRequestDto(@JsonProperty("id")Long id, @JsonProperty("name")String name,
                @JsonProperty("surname")String surname,
                @JsonProperty("birthday") String birthday) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }
}
