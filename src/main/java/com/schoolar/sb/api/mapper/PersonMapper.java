package com.schoolar.sb.api.mapper;

import com.schoolar.sb.api.dto.PersonDto;
import com.schoolar.sb.api.dto.PersonRequestDto;
import com.schoolar.sb.persistent.entity.Person;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper( componentModel = SPRING, uses = DepartmentMapper.class )
public interface PersonMapper {

    Person fromDto( PersonRequestDto requestDto );

    PersonDto toTdo( Person person );
}
