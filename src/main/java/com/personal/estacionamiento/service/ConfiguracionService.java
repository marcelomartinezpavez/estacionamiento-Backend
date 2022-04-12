package com.personal.estacionamiento.service;

import com.personal.estacionamiento.dto.ConfiguracionDto;
import com.personal.estacionamiento.request.ConfiguracionRequest;
import org.springframework.http.ResponseEntity;

public interface ConfiguracionService {

    ResponseEntity getAll();

    ResponseEntity getConfigurationByCompany(long idempresa);

    ResponseEntity createConfiguration(ConfiguracionRequest newConfiguracion);

    ResponseEntity updateConfiguration(ConfiguracionDto newConfiguracion);

    ResponseEntity deleteConfiguration(ConfiguracionDto newConfiguracion);

}
