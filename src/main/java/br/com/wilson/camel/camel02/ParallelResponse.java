package br.com.wilson.camel.camel02;

import br.com.wilson.camel.camel01.Registro;

import java.util.ArrayList;
import java.util.List;

public class ParallelResponse {
    private String nome;

    private List<Registro> registros;

    public ParallelResponse() {
        registros = new ArrayList<>();
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "PayloadRequest{" +
                "nome='" + nome + '\'' +
                ", registros=" + registros +
                '}';
    }
}
