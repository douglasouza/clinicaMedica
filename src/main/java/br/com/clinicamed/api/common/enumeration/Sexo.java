package br.com.clinicamed.api.common.enumeration;

public enum Sexo {
    FEMININO("Feminino"),
    MASCULINO("Masculino");

    private String sexo;

    Sexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return sexo;
    }
}
