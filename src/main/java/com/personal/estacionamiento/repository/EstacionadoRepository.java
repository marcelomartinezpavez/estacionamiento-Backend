package com.personal.estacionamiento.repository;

import com.personal.estacionamiento.dto.EstacionadoDto;
import com.personal.estacionamiento.dto.EstacionamientoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstacionadoRepository extends JpaRepository<EstacionadoDto, Long> {

    // buscar los estacionamientos por patente y que esten ocupados
    @Query(value = "select * from estacionado e where e.patente = :patente and e.estado = :estado", nativeQuery = true)
    Optional<EstacionadoDto> findByPatenteAndEstado(String patente, int estado);

    @Query(value = "select * from estacionado e where e.estacionamiento_id = :idEstacionamiento", nativeQuery = true)
    List<EstacionadoDto> findByIdEstacionamiento(long idEstacionamiento);

}
