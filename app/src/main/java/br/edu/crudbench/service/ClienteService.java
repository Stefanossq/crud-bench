package br.edu.crudbench.service;

import br.edu.crudbench.dto.ClienteRequest;
import br.edu.crudbench.model.Cliente;
import br.edu.crudbench.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Cliente criar(ClienteRequest req) {
        Cliente c = new Cliente();
        c.setNome(req.getNome());
        c.setEmail(req.getEmail());
        c.setTelefone(req.getTelefone());
        c.setCidade(req.getCidade());
        c.setEstado(req.getEstado());
        return repository.save(c);
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Page<Cliente> listar(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional
    public Cliente atualizar(Long id, ClienteRequest req) {
        return repository.findById(id).map(c -> {
            c.setNome(req.getNome());
            c.setEmail(req.getEmail());
            c.setTelefone(req.getTelefone());
            c.setCidade(req.getCidade());
            c.setEstado(req.getEstado());
            return repository.save(c);
        }).orElse(null);
    }

    @Transactional
    public boolean deletar(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }

    public List<Cliente> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}