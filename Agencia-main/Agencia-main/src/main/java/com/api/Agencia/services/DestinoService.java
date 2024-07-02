package com.api.Agencia.services;

import com.api.Agencia.entities.Destino;
import com.api.Agencia.Repositories.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {

    @Autowired
    private DestinoRepository destinoRepository;

    public void salvar(Destino destino) {
        destinoRepository.save(destino);
    }

    public List<Destino> listar() {
        return destinoRepository.findAll();
    }

    public List<Destino> buscarPorNomeOuLocalidade(String query) {
        return destinoRepository.findByNomeIgnoreCaseContainingOrLocalidadeIgnoreCaseContaining(query, query);
    }

    public Destino buscarPorId(Long id) {
        Optional<Destino> destino = destinoRepository.findById(id);
        return destino.orElse(null);
    }

    public boolean avaliarDestino(Long id, double nota) {
        Destino destino = buscarPorId(id);
        if (destino != null) {
            double totalNota = destino.getNota() * destino.getContaNota();
            destino.setContaNota(destino.getContaNota() + 1);
            destino.setNota((totalNota + nota) / destino.getContaNota());
            destinoRepository.save(destino);
            return true;
        } else {
            return false;
        }
    }

    public boolean excluir(Long id) {
        if (destinoRepository.existsById(id)) {
            destinoRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
