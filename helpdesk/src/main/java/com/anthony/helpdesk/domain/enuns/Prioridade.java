
package com.anthony.helpdesk.domain.enuns;

public enum Prioridade {

    ADMIN(0, "BAIXA"),
    CLIENTE(1, "MEDIA"),
    TECNICO(2, "ALTA");

    private Integer codigo;
    private String descricao;


    Prioridade(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Prioridade toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (Prioridade prioridade : Prioridade.values()) {

            if (codigo.equals(prioridade.getCodigo()))
                return prioridade;
        }
        throw new IllegalArgumentException("Prioridade inválida");

    }



}
