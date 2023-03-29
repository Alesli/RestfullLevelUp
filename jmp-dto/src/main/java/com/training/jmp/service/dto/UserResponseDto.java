package com.training.jmp.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
@EqualsAndHashCode(callSuper=true)
public class UserResponseDto extends RepresentationModel<UserResponseDto> {

    private Long id;
    private String name;
    private String surname;
    private String birthday;
}
