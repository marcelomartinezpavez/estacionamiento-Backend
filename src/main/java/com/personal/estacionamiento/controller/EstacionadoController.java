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

import java.sql.Date;
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
            String fechaInicio = dateTime +" 00:00:00";
            String fechaFin = dateTime +" 23:59:59";
            Timestamp ini = Timestamp.valueOf(fechaInicio);
            Timestamp fin = Timestamp.valueOf(fechaFin);
            List<EstacionadoDto> estacionadoDto = estacionadoRepository.findByIdEstacionamiento(idEstacionamiento, ini, fin);
            List<EstacionadoDto> estacionadoDtoList = new ArrayList<>();
            for (EstacionadoDto estacionadoDto1 : estacionadoDto){
                EstacionadoDto estacionadoDto2 = new EstacionadoDto();
                estacionadoDto2.setId(estacionadoDto1.getId());

                if(estacionadoDto1.getFechaIngreso() !=null){

                    ZonedDateTime zonedUTCIngreso = estacionadoDto1.getFechaIngreso().toLocalDateTime().atZone(ZoneId.of("UTC"));

                    Timestamp timestampIngreso = Timestamp.valueOf(zonedUTCIngreso.toLocalDateTime());

                    estacionadoDto2.setFechaIngreso(timestampIngreso);

                }

                if(estacionadoDto1.getFechaSalida() != null) {
                    ZonedDateTime zonedUTCSalida = estacionadoDto1.getFechaSalida().toLocalDateTime().atZone(ZoneId.of("UTC"));

                    Timestamp timestampSalida = Timestamp.valueOf(zonedUTCSalida.toLocalDateTime());

                    estacionadoDto2.setFechaSalida(timestampSalida);
                }

                LOGGER.info("TIPO PAGO: {}", estacionadoDto1.getTipoPago());
                estacionadoDto2.setTipoPago(estacionadoDto1.getTipoPago());
                estacionadoDto2.setValorTotal(estacionadoDto1.getValorTotal());
                estacionadoDto2.setMinutosEstacionado(estacionadoDto1.getMinutosEstacionado());
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

                LOGGER.info("TIPO PAGO: {}", estacionadoDto1.getTipoPago());
                estacionadoDto2.setTipoPago(estacionadoDto1.getTipoPago());
                estacionadoDto2.setValorTotal(estacionadoDto1.getValorTotal());
                estacionadoDto2.setEstado(estacionadoDto1.getEstado());
                estacionadoDto2.setMinutosEstacionado(estacionadoDto1.getMinutosEstacionado());
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

    @GetMapping(path = "/idEstacionamiento/{idEstacionamiento}/desde/{desde}/hasta/{hasta}", produces = "application/json")
    @CrossOrigin(origins = "*")
    public @ResponseBody
    ResponseEntity getAllByEmpresaAndDate(@PathVariable long idEstacionamiento, @PathVariable String desde, @PathVariable String hasta) {
        try {
            System.out.println("getAll estacionados");
            //Aquí colocas tu objeto tipo Date
            //String dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            //        .format(LocalDateTime.now());
            //String fechaInicio = dateTime +" 00:00:00";
            //String fechaFin = dateTime +" 23:59:59";
            Timestamp ini = Timestamp.valueOf(desde);
            Timestamp fin = Timestamp.valueOf(hasta);
            List<EstacionadoDto> estacionadoDto = estacionadoRepository.findByIdEstacionamiento(idEstacionamiento, ini, fin);
            List<EstacionadoDto> estacionadoDtoList = new ArrayList<>();
            for (EstacionadoDto estacionadoDto1 : estacionadoDto){
                EstacionadoDto estacionadoDto2 = new EstacionadoDto();
                estacionadoDto2.setId(estacionadoDto1.getId());

                if(estacionadoDto1.getFechaIngreso() !=null){

                    ZonedDateTime zonedUTCIngreso = estacionadoDto1.getFechaIngreso().toLocalDateTime().atZone(ZoneId.of("UTC"));

                    Timestamp timestampIngreso = Timestamp.valueOf(zonedUTCIngreso.toLocalDateTime());

                    estacionadoDto2.setFechaIngreso(timestampIngreso);

                }

                if(estacionadoDto1.getFechaSalida() != null) {
                    ZonedDateTime zonedUTCSalida = estacionadoDto1.getFechaSalida().toLocalDateTime().atZone(ZoneId.of("UTC"));

                    Timestamp timestampSalida = Timestamp.valueOf(zonedUTCSalida.toLocalDateTime());

                    estacionadoDto2.setFechaSalida(timestampSalida);
                }

                LOGGER.info("TIPO PAGO: {}", estacionadoDto1.getTipoPago());
                estacionadoDto2.setTipoPago(estacionadoDto1.getTipoPago());
                estacionadoDto2.setValorTotal(estacionadoDto1.getValorTotal());
                estacionadoDto2.setMinutosEstacionado(estacionadoDto1.getMinutosEstacionado());
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
