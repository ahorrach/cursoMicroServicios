package com.curso.expediente.model.mapper;

import com.curso.expediente.model.dto.ExpedienteDTO;
import com.curso.expediente.model.entity.ExpedienteEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ExpedienteMapper extends GenericMapper<ExpedienteEntity, ExpedienteDTO> {

}
