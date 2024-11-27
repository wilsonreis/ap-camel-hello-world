package br.com.wilson.camel.router;

import java.io.Serializable;

public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String tipo; // Tipo do pedido (ex: "ELETRONICO", "ROUPA")
    private String descricao;
    private double valor;

    // Construtor padrão
    public Pedido() {
    }

    // Construtor com parâmetros
    public Pedido(String id, String tipo, String descricao, double valor) {
        this.id = id;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
