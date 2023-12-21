package com.personal.estacionamiento.repository;

import com.personal.estacionamiento.dto.EstacionadoDto;
import com.personal.estacionamiento.dto.EstacionamientoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstacionadoRepository extends JpaRepository<EstacionadoDto, Long> {

    // buscar los estacionamientos por patente y que esten ocupados
    @Query(value = "select * from estacionado e where e.patente = :patente and e.estado = :estado", nativeQuery = true)
    Optional<EstacionadoDto> findByPatenteAndEstado(String patente, int estado);

    @Query(value = "select * from estacionado e where e.estacionamiento_id = :idEstacionamiento and fecha_ingreso between :fechaInicio and :fechaFin order by estado", nativeQuery = true)
    List<EstacionadoDto> findByIdEstacionamiento(long idEstacionamiento, Timestamp fechaInicio, Timestamp fechaFin);

    @Query(value = "select * from estacionado e where e.estacionamiento_id = :idEstacionamiento order by estado", nativeQuery = true)
    List<EstacionadoDto> findAllByIdEstacionamiento(long idEstacionamiento);
}
