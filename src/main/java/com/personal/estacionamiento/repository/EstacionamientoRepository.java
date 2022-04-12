package com.personal.estacionamiento.repository;

import com.personal.estacionamiento.dto.EstacionamientoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstacionamientoRepository extends JpaRepository<EstacionamientoDto, Long> {


    //Obtener estacionamiento por empresa
    @Query(value = "select * from estacionamiento e where e.empresa.id = :idEmpresa", nativeQuery = true)
    List<EstacionamientoDto> findByEmpresa(Long idEmpresa);

}
