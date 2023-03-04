package br.com.cwi.api.repository;

import br.com.cwi.api.domain.Curtida;
import br.com.cwi.api.domain.Post;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurtidaRepository extends JpaRepository<Curtida, Long> {

    boolean existsByUsuarioAndPost(Usuario usuario, Post post);
    Optional<Curtida> findByUsuarioAndPost(Usuario usuario, Post post);
}
