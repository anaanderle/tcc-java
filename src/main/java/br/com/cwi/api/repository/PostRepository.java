package br.com.cwi.api.repository;

import br.com.cwi.api.domain.Post;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.domain.SituacaoPost;
import br.com.cwi.api.security.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByIdAndUsuario(Long id, Usuario usuario);

    List<Post> findByUsuario(Usuario usuario);

    List<Post> findByUsuarioAndSituacao(Usuario usuario, SituacaoPost situacao);

    @Query("select p from Post p where p.usuario = :usuario or p.usuario in " +
            "(select a.usuario from Amizade a where a.amigo = :usuario and a.situacao = 'ATIVA') " +
            "or p.usuario in (select a.amigo from Amizade a where a.usuario = :usuario and a.situacao = 'ATIVA') " +
            "order by p.dataPostagem desc ")
    Page<Post> getMePost(Usuario usuario, Pageable pageable);
}
