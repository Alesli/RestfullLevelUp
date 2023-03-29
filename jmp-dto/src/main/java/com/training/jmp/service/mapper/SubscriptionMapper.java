package com.training.jmp.service.mapper;

import com.training.jmp.service.dto.SubscriptionRequestDto;
import com.training.jmp.service.dto.SubscriptionResponseDto;
import com.training.jmp.service.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ReferenceMapper.class)
public interface SubscriptionMapper {

    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "user", source = "userId")
    Subscription toEntity(SubscriptionRequestDto subscriptionRequestDto);

    @Mapping(target = "startDate", source = "startDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "userId", source = "user")
    SubscriptionResponseDto toDto(Subscription subscription);

    List<SubscriptionResponseDto> toDtoList(List<Subscription> entityList);
}