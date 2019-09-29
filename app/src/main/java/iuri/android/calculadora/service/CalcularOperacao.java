package iuri.android.calculadora.service;

import iuri.android.calculadora.util.UtilOperacao;

public class CalcularOperacao {

    private String operacao;

    public CalcularOperacao(){ }

    public String efetuarCalculoOperacao(String operacao){

        this.operacao = this.calcular(operacao);

        return this.operacao;

    }


    private String calcular(String operacoes){

        String[] vetOperacoes = operacoes.split(UtilOperacao.SEPARADOR_OPERACAO);

        if(!this.podeCalcular(vetOperacoes)){
            return operacoes;
        }

        String[] vetOperacaoSomSub = this.resolveMultDivis(vetOperacoes);

        if(vetOperacaoSomSub == null){
            return UtilOperacao.CONST_INFINITY;
        }

        return this.resolveSomaSubtrac(vetOperacaoSomSub)[0];

    }

    private String[] resolveMultDivis(String[] vetOperacoes){

        String valorOperacao = "";
        String[] vetSomaSub = new String[vetOperacoes.length];

        int itVetSomSubt = 0;
        for(int i=0; i<vetOperacoes.length;){

            if(UtilOperacao.isSinal(vetOperacoes[i])){

                if(UtilOperacao.isSinalMultOuDiv(vetOperacoes[i])){

                    valorOperacao = this.efetuarCalc(Double.valueOf(valorOperacao), Double.valueOf(vetOperacoes[i+1]), vetOperacoes[i]);

                    if(valorOperacao == null){
                        return null;
                    }

                    i++;

                }else{

                    vetSomaSub[itVetSomSubt++] = valorOperacao;
                    valorOperacao = "";
                    vetSomaSub[itVetSomSubt++] = vetOperacoes[i];

                }

            }else{

                valorOperacao = vetOperacoes[i];

            }

            i++;

        }

        vetSomaSub[itVetSomSubt++] = valorOperacao;

        return vetSomaSub;

    }

    private String[] resolveSomaSubtrac(String[] vetOperacoes){

        String valorOperacao = vetOperacoes[0];
        String[] result = new String[1];
        result[0] = valorOperacao;

        for(int i = 1; i<vetOperacoes.length;){

            if(vetOperacoes[i] == null){
                break;
            }

            valorOperacao = this.efetuarCalc(Double.valueOf(valorOperacao), Double.valueOf(vetOperacoes[i+1]), vetOperacoes[i]);
            result[0] = valorOperacao;

            i = i + 2;

        }

        return result;

    }

    private String efetuarCalc(Double vlr1, Double vlr2, String operacao){

        switch (operacao){

            case UtilOperacao.SINAL_ADICAO:{
                vlr1 += vlr2;
                break;
            }

            case UtilOperacao.SINAL_SUBTRACAO:{
                vlr1 -= vlr2;
                break;
            }

            case UtilOperacao.SINAL_MULTIPLICAR:{
                vlr1 *= vlr2;
                break;
            }

            case UtilOperacao.SINAL_DIVIDIR:{

                if(vlr2 == 0){
                    return null;
                }

                vlr1 /= vlr2;
                break;

            }

        }

        return vlr1.toString();

    }

    private boolean podeCalcular(String[] vetOperacao){
        return !UtilOperacao.isSinal(vetOperacao[vetOperacao.length-1]);
    }

}