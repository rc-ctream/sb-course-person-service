package com.schoolar.sb_basics.api.mapper;

import com.schoolar.sb_basics.api.PersonRequestDto;
import com.schoolar.sb_basics.persistent.Person;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper( componentModel = MappingConstants.ComponentModel.SPRING )
public interface PersonMapper {

    Person fromDto( PersonRequestDto requestDto );

}
