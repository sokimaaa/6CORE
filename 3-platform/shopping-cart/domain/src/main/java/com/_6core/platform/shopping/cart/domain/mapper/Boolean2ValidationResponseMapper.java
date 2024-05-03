package com._6core.platform.shopping.cart.domain.mapper;

import com._6core.platform.shopping.cart.domain.dto.internal.ValidationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface Boolean2ValidationResponseMapper {
  Boolean2ValidationResponseMapper INCTANCE =
      Mappers.getMapper(Boolean2ValidationResponseMapper.class);

  @Mapping(target = "isValid", source = "response")
  ValidationResponse map(Boolean response);
}
