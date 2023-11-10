package com.personal.estacionamiento.controller;

import com.personal.estacionamiento.dto.EstacionadoDto;
import com.personal.estacionamiento.dto.EstacionamientoDto;
import com.personal.estacionamiento.repository.ConfiguracionRepository;
import com.personal.estacionamiento.repository.EmpresaRepository;
import com.personal.estacionamiento.repository.EstacionadoRepository;
import com.personal.estacionamiento.repository.EstacionamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("estacionado")
public class EstacionadoController {


    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    EstacionamientoRepository estacionamientoRepository;

    @Autowired
    EstacionadoRepository estacionadoRepository;

    @Autowired
    ConfiguracionRepository configuracionRepository;

    @GetMapping(path = "/idEstacionamiento/{idEstacionamiento}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllByEmpresa(@PathVariable long idEstacionamiento) {
        try {
            System.out.println("getAll estacionados");
            List<EstacionadoDto> estacionadoDto = estacionadoRepository.findByIdEstacionamiento(idEstacionamiento);
            return new ResponseEntity(estacionadoDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al buscar todos los estacionados", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
