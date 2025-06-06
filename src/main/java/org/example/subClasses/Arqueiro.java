package org.example.subClasses;

import org.example.Personagem;

public class Arqueiro extends Personagem {

    public Arqueiro(String nome) {
        super(nome, 80, 50, 0);
    }

    @Override
    public void atacar(Personagem personagemAlvo) {
        personagemAlvo.tomarDano(ataque);
    }
}
