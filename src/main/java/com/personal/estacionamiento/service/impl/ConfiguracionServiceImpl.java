package com.personal.estacionamiento.service.impl;

import com.personal.estacionamiento.dto.ConfiguracionDto;
import com.personal.estacionamiento.dto.EmpresaDto;
import com.personal.estacionamiento.dto.EstacionamientoDto;
import com.personal.estacionamiento.repository.ConfiguracionRepository;
import com.personal.estacionamiento.repository.EmpresaRepository;
import com.personal.estacionamiento.repository.EstacionamientoRepository;
import com.personal.estacionamiento.request.ConfiguracionRequest;
import com.personal.estacionamiento.service.ConfiguracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfiguracionServiceImpl implements ConfiguracionService {

    @Autowired
    ConfiguracionRepository configuracionRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    EstacionamientoRepository estacionamientoRepository;

    public ResponseEntity getAll(){
        System.out.println("getAll");
        try {
            List<ConfiguracionDto> resp = configuracionRepository.findAll();

            return new ResponseEntity(resp, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getConfigurationByCompany(long idempresa){

        Optional<ConfiguracionDto> resp = configuracionRepository.findByEmpresaId(idempresa);

        return new ResponseEntity(resp,HttpStatus.OK);

    }


    public ResponseEntity createConfiguration(ConfiguracionRequest newConfiguracion){

        ConfiguracionDto configuracionDto = new ConfiguracionDto();

        configuracionDto.setHabilitado(1);
        configuracionDto.setValorDia(newConfiguracion.getValorDia());
        configuracionDto.setValorHora(newConfiguracion.getValorHora());
        configuracionDto.setValorMinuto(newConfiguracion.getValorMinuto());
        configuracionDto.setValorMes(newConfiguracion.getValorMes());

        Optional<EstacionamientoDto> estacionamientoDtoOptional = estacionamientoRepository.findById(newConfiguracion.getEstacionamiento_id());

        if(estacionamientoDtoOptional.isPresent()) {

            EstacionamientoDto estacionamientoDto = estacionamientoDtoOptional.get();
            configuracionDto.setEstacionamiento(estacionamientoDto);
            configuracionDto.setEstacionamientoId(estacionamientoDto.getId());
            Optional<EmpresaDto> empresaDtoOptional = empresaRepository.findById(newConfiguracion.getEmpresa_id());

            if (empresaDtoOptional.isPresent()) {
                EmpresaDto empresaDto = empresaDtoOptional.get();
                configuracionDto.setEmpresa(empresaDto);
            } else {
                return new ResponseEntity("No se encontro la empresa", HttpStatus.NOT_FOUND);
            }

            configuracionRepository.save(configuracionDto);
            return new ResponseEntity(newConfiguracion, HttpStatus.CREATED);
        }else{
            return new ResponseEntity("No se encontro el estacionamiento", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity updateConfiguration(ConfiguracionDto newConfiguracion){
        configuracionRepository.save(newConfiguracion);
        return new ResponseEntity(newConfiguracion, HttpStatus.OK);

    }

    public ResponseEntity deleteConfiguration(ConfiguracionDto newConfiguracion) {

        newConfiguracion.setHabilitado(0);
        configuracionRepository.save(newConfiguracion);
        return new ResponseEntity(newConfiguracion, HttpStatus.OK);
    }

    public ResponseEntity existConfiguracionForEmpresa(long id){
        ConfiguracionDto configuracionDto = configuracionRepository.existConfiguracionForEmpresa(id);
        return new ResponseEntity(configuracionDto, HttpStatus.OK);
    }


}
