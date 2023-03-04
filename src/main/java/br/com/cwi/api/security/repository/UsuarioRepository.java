package br.com.cwi.api.security.repository;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
    boolean existsByEmail(String email);
    List<Usuario> findByNomeContainingOrEmailContainingAllIgnoreCase(String nome, String email);
}
