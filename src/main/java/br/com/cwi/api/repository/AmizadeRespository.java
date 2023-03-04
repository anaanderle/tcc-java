package br.com.cwi.api.repository;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AmizadeRespository extends JpaRepository<Amizade, Long> {

    boolean existsByUsuarioAndAmigoAndSituacao(Usuario usuario, Usuario amigo, SituacaoAmizade situacao);

    boolean existsByUsuarioAndAmigo(Usuario usuario, Usuario amigo);
    Optional<Amizade> findByIdAndSituacao(Long id, SituacaoAmizade situacao);

    @Query("select a from Amizade a where (a.usuario = :usuario or a.amigo = :usuario) and a.situacao = :situacao")
    List<Amizade> findAmizadesUsuarioPorSituacao(Usuario usuario, SituacaoAmizade situacao);
}
