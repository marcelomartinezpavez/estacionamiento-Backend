package com.personal.estacionamiento.controller;

import com.personal.estacionamiento.dto.ConfiguracionDto;
import com.personal.estacionamiento.dto.EstacionamientoDto;
import com.personal.estacionamiento.request.ConfiguracionRequest;
import com.personal.estacionamiento.service.ConfiguracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("configuracion")
public class ConfiguracionController {

    @Autowired
    ConfiguracionService configuracionService;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllConfiguracion() {
        return configuracionService.getAll();
    }

    @GetMapping(path = "/exist/{id}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity existConfiguracion(@PathVariable("id") Long id) {
        return configuracionService.existConfiguracionForEmpresa(id);
    }

    @GetMapping(path = "/empresa/{idempresa}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllConfiguracionesPorEmpresa(@PathVariable long idempresa) {


        return configuracionService.getConfigurationByCompany(idempresa);

    }

    @PostMapping(path = "/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity create(@RequestBody ConfiguracionRequest newConfiguracion) {

        return configuracionService.createConfiguration(newConfiguracion);
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<EstacionamientoDto> update(@RequestBody ConfiguracionDto newConfiguracion) {
        return configuracionService.updateConfiguration(newConfiguracion);
    }

    @DeleteMapping(path = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity delete(@RequestBody ConfiguracionDto newConfiguracion) {
        return configuracionService.deleteConfiguration(newConfiguracion);
    }
}
