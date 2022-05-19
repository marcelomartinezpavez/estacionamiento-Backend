package com.personal.estacionamiento.repository;

import com.personal.estacionamiento.dto.ConfiguracionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfiguracionRepository extends JpaRepository<ConfiguracionDto, Long> {

    @Override
    Optional<ConfiguracionDto> findById(Long aLong);

    //Obtener configuracion por empresa
    Optional<ConfiguracionDto> findByEmpresaId(Long idEmpresa);

    Optional<ConfiguracionDto> findByEstacionamientoId(Long idEstacionamiento);

    @Query(value = "select * from configuracion e where e.empresa_id = :idEmpresa", nativeQuery = true)
    ConfiguracionDto existConfiguracionForEmpresa(Long idEmpresa);


}
