package com.anthony.helpdesk.domain.enuns;

public enum Status {

    ADMIN(0, "ABERTO"),
    CLIENTE(1, "ANDAMENTO"),
    TECNICO(2, "ENCERRADO");

    private Integer codigo;
    private String descricao;


    Status(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Status toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (Status status : Status.values()) {

            if (codigo.equals(status.getCodigo()))
                return status;
        }
        throw new IllegalArgumentException("Status inválido");

    }



}
