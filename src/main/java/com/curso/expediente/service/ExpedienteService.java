package com.curso.expediente.service;

import com.curso.expediente.model.dto.ExpedienteDTO;
import com.curso.expediente.model.entity.ExpedienteEntity;
import com.curso.expediente.model.mapper.ExpedienteMapper;
import com.curso.expediente.repository.ExpedienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpedienteService {

    @Autowired
    private ExpedienteRepository expedienteRepository;

    @Autowired
    private ExpedienteMapper expedienteMapper;

    public List<ExpedienteDTO> getAllExpedientes() {
        return expedienteMapper.entityListToDtoList(expedienteRepository.findAll());
    }

    public List<ExpedienteDTO> getExpedientesByDNI(String dni) {
        return expedienteMapper.entityListToDtoList(expedienteRepository.getByDni(dni));
    }

    public List<ExpedienteDTO> getExpedientesByTipoPrestacion(Integer tipoPrestacion) {
        return expedienteMapper.entityListToDtoList(expedienteRepository.getByTipoPrestacion(tipoPrestacion));
    }

    public Optional<ExpedienteDTO> getExpedienteById(Long id) {
        Optional<ExpedienteEntity> expedienteEntityOptional =  expedienteRepository.findById(id);
        return expedienteEntityOptional.isPresent()
                ?Optional.of(expedienteMapper.toDTO(expedienteEntityOptional.get()))
                :Optional.empty();
    }

    public ExpedienteDTO createExpediente(ExpedienteDTO expediente) {
        ExpedienteEntity expedienteEntity = expedienteMapper.toEntity(expediente);
        expedienteEntity.setId(null);
        return expedienteMapper.toDTO(expedienteRepository.save(expedienteEntity));
    }

    public ExpedienteDTO updateExpediente(Long id, ExpedienteEntity expediente) {
        if (expedienteRepository.existsById(id)) {
            expediente.setId(id);
            return expedienteMapper.toDTO(expedienteRepository.save(expediente));
        }
        return null; // O manejar con una excepción personalizada
    }

    public boolean deleteExpediente(Long id) {
        if (expedienteRepository.existsById(id)) {
            expedienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
