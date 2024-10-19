package com.example.projectingresso;

public class IngressoVIP extends Ingresso {
    private String funcaoDesempenhada;

    public IngressoVIP(String codigoIdentificador, float valor, String funcaoDesempenhada) {
        super(codigoIdentificador, valor);
        this.funcaoDesempenhada = funcaoDesempenhada;
    }

    @Override
    public float valorFinal(float taxaConveniencia) {
        float valorBase = super.valorFinal(taxaConveniencia);
        valorBase *= 1.18f;
        return valorBase;
    }

    public String getFuncaoDesempenhada() {
        return funcaoDesempenhada;
    }

    public void setFuncaoDesempenhada(String funcaoDesempenhada) {
        this.funcaoDesempenhada = funcaoDesempenhada;
    }
}
