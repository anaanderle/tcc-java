package br.com.cwi.api.security.domain;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.Comentario;
import br.com.cwi.api.domain.Curtida;
import br.com.cwi.api.domain.Post;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String apelido;
    private LocalDate dataNascimento;
    private String imagemUrl;
    private String senha;
    private boolean ativo;

    @OneToMany(mappedBy = "usuario")
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "usuario")
    private List<Amizade> amizadesEnviadas = new ArrayList<>();

    @OneToMany(mappedBy = "amigo")
    private List<Amizade> amizadesRecebidas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permissao> permissoes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Curtida> curtidas = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios = new ArrayList<>();
    public void adicionarPermissao(Permissao permissao) {
        this.permissoes.add(permissao);
        permissao.setUsuario(this);
    }
}
