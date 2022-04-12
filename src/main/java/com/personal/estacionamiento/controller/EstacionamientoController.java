package com.personal.estacionamiento.controller;

import com.personal.estacionamiento.dto.*;
import com.personal.estacionamiento.repository.*;
import com.personal.estacionamiento.request.EstacionadoRequest;
import com.personal.estacionamiento.request.EstacionamientoRequest;
import com.personal.estacionamiento.util.EstadoEstacionado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("estacionamiento")
public class EstacionamientoController {

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    EstacionamientoRepository estacionamientoRepository;

    @Autowired
    EstacionadoRepository estacionadoRepository;

    @Autowired
    ConfiguracionRepository configuracionRepository;

    @GetMapping(path = "/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllEstacionamiento() {
        try {
            System.out.println("getAll Estacionamiento");
            List<EstacionamientoDto> estacionamientoDtos = estacionamientoRepository.findAll();
            return new ResponseEntity(estacionamientoDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al buscar todos los estacionamientos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/empresa/{idEmpresa}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getEstacionamientoByEmpresa(@PathVariable long idEmpresa) {
        try {
            List<EstacionamientoDto> estacionamientoDtos = estacionamientoRepository.findByEmpresa(idEmpresa);
            return new ResponseEntity(estacionamientoDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al buscar estacionamiento", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/insert/estacionado", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity insertEstacionado(@RequestBody EstacionadoRequest estacionadoRequest) {
        try {
            EstacionadoDto estacionadoDto = new EstacionadoDto();
            Optional<EstacionamientoDto> estacionamientoDtoOptional = estacionamientoRepository.findById(estacionadoRequest.getEstacionamiento_id());

            Optional<EstacionadoDto> estacionadoDtoOptional = estacionadoRepository.findByPatenteAndEstado(estacionadoRequest.getPatente(), EstadoEstacionado.OCUPADO.ordinal());

            if (estacionadoDtoOptional.isPresent()) {
                return new ResponseEntity("El vehiculo ya se encuentra estacionado", HttpStatus.BAD_REQUEST);
            }

            if (estacionamientoDtoOptional.isPresent()) {
                EstacionamientoDto estacionamientoDto = estacionamientoDtoOptional.get();
                int totalEstacionamiento = estacionamientoDto.getCantidadTotal();
                int ocupados = estacionamientoDto.getCantidadOcupado() + 1;
                int libres = estacionamientoDto.getCantidadLibre() - 1;
                estacionamientoDto.setCantidadOcupado(ocupados);
                estacionamientoDto.setCantidadLibre(libres);
                if (ocupados > totalEstacionamiento) {
                    return new ResponseEntity("No hay mas espacio en el estacionamiento", HttpStatus.BAD_REQUEST);
                }


                estacionamientoRepository.save(estacionamientoDto);

                estacionadoDto.setEstacionamiento(estacionamientoDto);
            }
            estacionadoDto.setPatente(estacionadoRequest.getPatente());

            estacionadoDto.setEstado(EstadoEstacionado.OCUPADO.ordinal());
            estacionadoDto.setEstacionamientoId(estacionadoRequest.getEstacionamiento_id());

            LocalDateTime localDateTime = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            estacionadoDto.setFechaIngreso(timestamp);
            estacionadoRepository.save(estacionadoDto);
            return new ResponseEntity(estacionadoDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error Interno al asignar estacionamiento", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/insert/pago", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity insertPago(@RequestBody EstacionadoRequest estacionadoRequest) {
        try {
            Optional<EstacionamientoDto> estacionamientoDtoOptional = estacionamientoRepository.findById(estacionadoRequest.getEstacionamiento_id());

            if (estacionamientoDtoOptional.isPresent()) {
                EstacionamientoDto estacionamientoDto = estacionamientoDtoOptional.get();

                Optional<ConfiguracionDto> configuracionDtoOptional = configuracionRepository.findByEstacionamientoId(estacionamientoDto.getId());
                if (configuracionDtoOptional.isPresent()) {
                    ConfiguracionDto configuracionDto = configuracionDtoOptional.get();

                    Optional<EstacionadoDto> estacionadoDtoOptional = estacionadoRepository.findByPatenteAndEstado(estacionadoRequest.getPatente(), EstadoEstacionado.OCUPADO.ordinal());

                    if (estacionadoDtoOptional.isPresent()) {


                        //int totalEstacionamiento = estacionamientoDto.getCantidadTotal();
                        int ocupados = estacionamientoDto.getCantidadOcupado() -1;
                        int libres = estacionamientoDto.getCantidadLibre() + 1;
                        estacionamientoDto.setCantidadOcupado(ocupados);
                        estacionamientoDto.setCantidadLibre(libres);

                        estacionamientoRepository.save(estacionamientoDto);

                        long valorMinuto = configuracionDto.getValorMinuto();

                        EstacionadoDto estacionadoDto = estacionadoDtoOptional.get();
                        estacionadoDto.setEstado(EstadoEstacionado.PAGADO.ordinal());
                        //estacionadoDto.setEstacionamientoId(estacionadoRequest.getEstacionamiento_id());

                        LocalDateTime localDateTime = LocalDateTime.now();
                        Timestamp timestamp = Timestamp.valueOf(localDateTime);
                        estacionadoDto.setFechaSalida(timestamp);

                        long diferencia = timestamp.getTime() - estacionadoDto.getFechaIngreso().getTime();
                        long minutos = TimeUnit.MILLISECONDS.toMinutes(diferencia);

                        long total = valorMinuto * minutos;

                        estacionadoDto.setValorTotal(total);

                        estacionadoRepository.save(estacionadoDto);
                        return new ResponseEntity(estacionadoDto, HttpStatus.OK);
                    }else{
                        return new ResponseEntity("No se encuentra vehiculo estacionado", HttpStatus.NOT_FOUND);
                    }

                }else{
                    return new ResponseEntity("No se obtuvo la configuracion", HttpStatus.BAD_REQUEST);
                }
            }else {
                return new ResponseEntity("No se obtuvo estacionamiento", HttpStatus.BAD_REQUEST);
            }


            //if (estacionamientoDtoOptional.isPresent()) {
            //    EstacionamientoDto estacionamientoDto = estacionamientoDtoOptional.get();
            //    estacionadoDto.setEstacionamiento(estacionamientoDto);
            //}

            //estacionadoDto.setPatente(estacionadoRequest.getPatente());


        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Error Interno al asignar estacionamiento", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //return new ResponseEntity("Error Interno al asignar estacionamiento", HttpStatus.INTERNAL_SERVER_ERROR);
    }




    @PostMapping(path = "/insert",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity create(@RequestBody EstacionamientoRequest newEstacionamiento) {

        EstacionamientoDto estacionamientoDto = new EstacionamientoDto();
        Optional<EmpresaDto> empresaDtoOptional = empresaRepository.findById(newEstacionamiento.getEmpresa_id());
        estacionamientoDto.setHabilitado(1);
        estacionamientoDto.setCantidadLibre(newEstacionamiento.getCantidadLibre());
        estacionamientoDto.setCantidadOcupado(newEstacionamiento.getCantidadOcupado());
        estacionamientoDto.setCantidadTotal(newEstacionamiento.getCantidadTotal());

        if(empresaDtoOptional.isPresent()){
            estacionamientoDto.setEmpresa(empresaDtoOptional.get());
        }

        estacionamientoRepository.save(estacionamientoDto);
        return new ResponseEntity(estacionamientoDto, HttpStatus.OK);
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity update(@RequestBody EstacionamientoDto newEstacionamiento) {
        estacionamientoRepository.save(newEstacionamiento);
        return new ResponseEntity(newEstacionamiento, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity delete(@RequestBody EstacionamientoDto newEstacionamiento) {
        Optional<EstacionamientoDto> estacionamientoDtoOptional = estacionamientoRepository.findById(newEstacionamiento.getId());
        if(estacionamientoDtoOptional.isPresent()){

            estacionamientoDtoOptional.get().setHabilitado(0);
            estacionamientoRepository.save(estacionamientoDtoOptional.get());
            return new ResponseEntity(estacionamientoDtoOptional.get(),HttpStatus.OK);
        }
        return new ResponseEntity("No se encontro el estacionamiento",HttpStatus.NOT_FOUND);

    }

}
