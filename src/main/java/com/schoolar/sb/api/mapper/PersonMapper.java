package com.schoolar.sb.api.mapper;

import com.schoolar.sb.api.dto.PersonRequestDto;
import com.schoolar.sb.api.dto.PersonDto;
import com.schoolar.sb.persistent.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper( componentModel = MappingConstants.ComponentModel.SPRING )
public interface PersonMapper {

    Person fromDto( PersonRequestDto requestDto );

    PersonDto toTdo( Person person );
}
