package br.com.cwi.api.factories;

import br.com.cwi.api.domain.Amizade;
import br.com.cwi.api.domain.SituacaoAmizade;
import br.com.cwi.api.security.domain.Usuario;

import static br.com.cwi.api.domain.SituacaoAmizade.*;

public class AmizadeFactory {

    public static Amizade getAtiva(){
        Usuario usuario = new Usuario();
        Usuario amigo = new Usuario();

        return Amizade.builder()
                .id(SimpleFactory.getRandomLong())
                .amigo(amigo)
                .usuario(usuario)
                .situacao(ATIVA)
                .build();
    }

    public static Amizade getSolicitada(){
        Usuario usuario = new Usuario();
        Usuario amigo = new Usuario();

        return Amizade.builder()
                .id(SimpleFactory.getRandomLong())
                .amigo(amigo)
                .usuario(usuario)
                .situacao(SOLICITADA)
                .build();
    }

    public static Amizade getAmigo(Usuario amigo){
        Usuario usuario = new Usuario();

        return Amizade.builder()
                .id(SimpleFactory.getRandomLong())
                .amigo(amigo)
                .usuario(usuario)
                .build();
    }

    public static Amizade get(Usuario usuario, Usuario amigo){

        return Amizade.builder()
                .id(SimpleFactory.getRandomLong())
                .amigo(amigo)
                .usuario(usuario)
                .situacao(ATIVA)
                .build();
    }
}
