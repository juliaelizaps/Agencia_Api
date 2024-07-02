package com.api.Agencia.Repositories;

import com.api.Agencia.entities.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinoRepository extends JpaRepository<Destino, Long> {
    List<Destino> findByNomeIgnoreCaseContainingOrLocalidadeIgnoreCaseContaining(String nome, String localidade);
}