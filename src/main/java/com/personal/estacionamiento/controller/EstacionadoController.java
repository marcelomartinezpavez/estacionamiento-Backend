package com.personal.estacionamiento.controller;

import com.personal.estacionamiento.dto.EmpresaDto;
import com.personal.estacionamiento.dto.EstacionadoDto;
import com.personal.estacionamiento.dto.EstacionamientoDto;
import com.personal.estacionamiento.repository.ConfiguracionRepository;
import com.personal.estacionamiento.repository.EmpresaRepository;
import com.personal.estacionamiento.repository.EstacionadoRepository;
import com.personal.estacionamiento.repository.EstacionamientoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("estacionado")
public class EstacionadoController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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
    ResponseEntity getAllByEmpresaDay(@PathVariable long idEstacionamiento) {
        try {
            System.out.println("getAll estacionados");
            //Aquí colocas tu objeto tipo Date
            String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    .format(LocalDateTime.now());
            LOGGER.info("dateTime: {}",dateTime);
            String fechaInicio = dateTime +" 00:00:00";
            String fechaFin = dateTime +" 23:59:59";
            Timestamp ini = Timestamp.valueOf(fechaInicio);
            Timestamp fin = Timestamp.valueOf(fechaFin);
            List<EstacionadoDto> estacionadoDto = estacionadoRepository.findByIdEstacionamiento(idEstacionamiento, ini, fin);
            List<EstacionadoDto> estacionadoDtoList = new ArrayList<>();
            for (EstacionadoDto estacionadoDto1 : estacionadoDto){
                EstacionadoDto estacionadoDto2 = new EstacionadoDto();
                estacionadoDto2.setId(estacionadoDto1.getId());

                LOGGER.info("fecha Ingreso: {}",estacionadoDto1.getFechaIngreso());
                LOGGER.info("fecha Salida: {}",estacionadoDto1.getFechaSalida());
                if(estacionadoDto1.getFechaIngreso() !=null){

                    ZonedDateTime zonedUTCIngreso = estacionadoDto1.getFechaIngreso().toLocalDateTime().atZone(ZoneId.of("UTC"));
                    LOGGER.info("zonedUTCIngreso: {}",zonedUTCIngreso);

                    Timestamp timestampIngreso = Timestamp.valueOf(zonedUTCIngreso.toLocalDateTime());
                    LOGGER.info("timestampIngreso: {}",timestampIngreso);
                    estacionadoDto2.setFechaIngreso(timestampIngreso);

                }

                if(estacionadoDto1.getFechaSalida() != null) {
                    ZonedDateTime zonedUTCSalida = estacionadoDto1.getFechaSalida().toLocalDateTime().atZone(ZoneId.of("UTC"));
                    LOGGER.info("zonedUTCSalida: {}", zonedUTCSalida);
                    Timestamp timestampSalida = Timestamp.valueOf(zonedUTCSalida.toLocalDateTime());
                    LOGGER.info("timestampSalida: {}",timestampSalida);
                    estacionadoDto2.setFechaSalida(timestampSalida);
                }
                estacionadoDto2.setTipoPago(estacionadoDto1.getTipoPago());
                estacionadoDto2.setValorTotal(estacionadoDto1.getValorTotal());
                estacionadoDto2.setEstado(estacionadoDto1.getEstado());
                estacionadoDto2.setPatente(estacionadoDto1.getPatente());
                estacionadoDto2.setEstacionamientoId(estacionadoDto1.getEstacionamientoId());
                
                EstacionamientoDto estacionamientoDto = new EstacionamientoDto();
                estacionamientoDto.setId(estacionadoDto1.getEstacionamiento().getId());
                estacionamientoDto.setCantidadTotal(estacionadoDto1.getEstacionamiento().getCantidadTotal());
                estacionamientoDto.setCantidadLibre(estacionadoDto1.getEstacionamiento().getCantidadLibre());
                estacionamientoDto.setCantidadOcupado(estacionadoDto1.getEstacionamiento().getCantidadOcupado());
                estacionamientoDto.setHabilitado(estacionadoDto1.getEstacionamiento().getHabilitado());
                EmpresaDto empresaDto = new EmpresaDto();
                empresaDto.setId(estacionadoDto1.getEstacionamiento().getEmpresa().getId());
                empresaDto.setDireccion(estacionadoDto1.getEstacionamiento().getEmpresa().getDireccion());
                empresaDto.setNombre(estacionadoDto1.getEstacionamiento().getEmpresa().getNombre());
                empresaDto.setRut(estacionadoDto1.getEstacionamiento().getEmpresa().getRut());
                estacionamientoDto.setEmpresa(empresaDto);
                estacionadoDto2.setEstacionamiento(estacionamientoDto);
                estacionadoDtoList.add(estacionadoDto2);

            }
            return new ResponseEntity(estacionadoDtoList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Error Interno al buscar todos los estacionados del dia", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/idEstacionamiento/{idEstacionamiento}/all", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllByEmpresa(@PathVariable long idEstacionamiento) {
        try {
            System.out.println("getAll estacionados");
            //Aquí colocas tu objeto tipo Date
            
            List<EstacionadoDto> estacionadoDto = estacionadoRepository.findAllByIdEstacionamiento(idEstacionamiento);
            List<EstacionadoDto> estacionadoDtoList = new ArrayList<>();
            for (EstacionadoDto estacionadoDto1 : estacionadoDto){
                EstacionadoDto estacionadoDto2 = new EstacionadoDto();
                estacionadoDto2.setId(estacionadoDto1.getId());
                estacionadoDto2.setFechaIngreso(estacionadoDto1.getFechaIngreso());
                estacionadoDto2.setFechaSalida(estacionadoDto1.getFechaSalida());
                estacionadoDto2.setTipoPago(estacionadoDto1.getTipoPago());
                estacionadoDto2.setValorTotal(estacionadoDto1.getValorTotal());
                estacionadoDto2.setEstado(estacionadoDto1.getEstado());
                estacionadoDto2.setPatente(estacionadoDto1.getPatente());
                estacionadoDto2.setEstacionamientoId(estacionadoDto1.getEstacionamientoId());
                
                EstacionamientoDto estacionamientoDto = new EstacionamientoDto();
                estacionamientoDto.setId(estacionadoDto1.getEstacionamiento().getId());
                estacionamientoDto.setCantidadTotal(estacionadoDto1.getEstacionamiento().getCantidadTotal());
                estacionamientoDto.setCantidadLibre(estacionadoDto1.getEstacionamiento().getCantidadLibre());
                estacionamientoDto.setCantidadOcupado(estacionadoDto1.getEstacionamiento().getCantidadOcupado());
                estacionamientoDto.setHabilitado(estacionadoDto1.getEstacionamiento().getHabilitado());
                EmpresaDto empresaDto = new EmpresaDto();
                empresaDto.setId(estacionadoDto1.getEstacionamiento().getEmpresa().getId());
                empresaDto.setDireccion(estacionadoDto1.getEstacionamiento().getEmpresa().getDireccion());
                empresaDto.setNombre(estacionadoDto1.getEstacionamiento().getEmpresa().getNombre());
                empresaDto.setRut(estacionadoDto1.getEstacionamiento().getEmpresa().getRut());

                estacionamientoDto.setEmpresa(empresaDto);
                estacionadoDto2.setEstacionamiento(estacionamientoDto);
                estacionadoDtoList.add(estacionadoDto2);

            }
            return new ResponseEntity(estacionadoDtoList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Error Interno al buscar todos los estacionados del dia", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
