package iuri.android.calculadora.util;

import java.util.Arrays;
import java.util.List;

public class UtilOperacao {

    public static final String SEPARADOR_OPERACAO = "_";

    public static final String SINAL_SUBTRACAO = "-";
    public static final String SINAL_ADICAO = "+";
    public static final String SINAL_DIVIDIR = "/";
    public static final String SINAL_MULTIPLICAR = "*";

    public static List<String> SINAIS = Arrays.asList(SINAL_SUBTRACAO, SINAL_ADICAO, SINAL_DIVIDIR, SINAL_MULTIPLICAR);
    public static List<String> SINAIS_MULT_OU_DIVIS = Arrays.asList(SINAL_MULTIPLICAR, SINAL_DIVIDIR);

    public static final String CONST_INFINITY = "Infinity";
    public static final String CONST_ZERO = "0";
    public static final String CONST_PONTO= ".";

    public static boolean isSinalMultOuDiv(String operacao){
        return SINAIS_MULT_OU_DIVIS.contains(operacao);
    }

    public static boolean isSinal(String instrucao){
        return (SINAIS.contains(instrucao));
    }

}
