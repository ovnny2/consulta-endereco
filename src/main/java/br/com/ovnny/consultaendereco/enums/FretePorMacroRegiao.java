package br.com.ovnny.consultaendereco.enums;

public enum FretePorMacroRegiao {
    NORTE("R$20,83"),
    NORDESTE("R$15,98"),
    SUDESTE("R$7,85"),
    SUL("R$17,30"),
    CENTRO_OESTE("R$12,50");

    private final String frete;

    FretePorMacroRegiao(String frete) {
        this.frete = frete;
    }

    public static String precoDoFrete(String ibge) {

        switch (String.valueOf(ibge.charAt(0))) {
            case "1":
                return NORTE.frete;
            case "2":
                return NORDESTE.frete;
            case "3":
                return SUDESTE.frete;
            case "4":
                return SUL.frete;
            case "5":
                return CENTRO_OESTE.frete;
            default:
                return "Prefixo Inválido";
        }
    }
}