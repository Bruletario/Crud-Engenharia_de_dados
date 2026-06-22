package org.example.Repository;

import org.example.entity.Vinculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VinculoRepository extends JpaRepository<Vinculo, Long> {

    List<Vinculo> findByStatus(String status);


    List<Vinculo> findByEstudanteMatEstudante(String matEstudante);
}