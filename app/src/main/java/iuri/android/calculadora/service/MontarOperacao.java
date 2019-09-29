package iuri.android.calculadora.service;

import iuri.android.calculadora.util.UtilOperacao;

public class MontarOperacao {

    private String operacao;

    public MontarOperacao(){ }

    public MontarOperacao(String operacao){
        this.operacao = operacao;
    }

    public String limparUltOperacao(){

        String ultOperacao = this.getUltimaInstrucaoOperacao();

        if(this.operacao.length() == 1 && this.operacao.equals(UtilOperacao.SINAL_SUBTRACAO)){

            this.operacao = UtilOperacao.CONST_ZERO;
            return this.operacao;

        }

        if(this.isSinalNegativo() || !UtilOperacao.isSinal(ultOperacao)){

            if(this.getUltimaInstrucaoOperacao().equals(UtilOperacao.CONST_INFINITY)){
                this.operacao = UtilOperacao.CONST_ZERO;
            }else {
                this.operacao = this.operacao.substring(0, this.operacao.length() - 1);
            }

        }else{

            this.operacao = this.operacao.substring(0,this.operacao.length() - 3);

        }

        if(this.operacao.length() == 0){
            this.operacao = UtilOperacao.CONST_ZERO;
        }

        return this.operacao;

    }

    private boolean isSinalNegativo(){

        String[] vetInstrucao = this.operacao.split(UtilOperacao.SEPARADOR_OPERACAO);

        if(vetInstrucao.length < 1){
            return false;
        }

        if(!vetInstrucao[vetInstrucao.length - 1].equals(UtilOperacao.SINAL_SUBTRACAO)){
            return false;
        }

        if(vetInstrucao.length == 1 && vetInstrucao[0].equals(UtilOperacao.SINAL_SUBTRACAO)){
            return true;
        }

        return (UtilOperacao.isSinalMultOuDiv(vetInstrucao[vetInstrucao.length - 2]));

    }

    public String tratarInstrucao(String instrucao){

        if (UtilOperacao.isSinal(instrucao)){
            this.tratarInstrucaoSinal(instrucao);
        }else{
            this.tratarInstrucaoNumeral(instrucao);
        }

        return this.operacao;

    }

    private void tratarInstrucaoSinal(String instrucao){

        String ultInstrucao = this.getUltimaInstrucaoOperacao();

        if(ultInstrucao.equals(UtilOperacao.CONST_INFINITY)){
            return;
        }

        if(UtilOperacao.SINAIS.contains(ultInstrucao)){

            if(this.isSinalNegativo() || ultInstrucao.equals(UtilOperacao.CONST_INFINITY)){
                return;
            }

            if((UtilOperacao.isSinalMultOuDiv(ultInstrucao) && instrucao.equals(UtilOperacao.SINAL_SUBTRACAO))) {

                this.operacao += instrucao;

            }else{
                this.operacao = (this.operacao.substring(0, this.operacao.length() - 3) + this.getInstrucaoComSeparador(instrucao));
            }

        }else {
            this.operacao += this.getInstrucaoComSeparador(instrucao);
        }

    }

    private void tratarInstrucaoNumeral(String instrucao){

        if(this.isPonto(instrucao) &&
            (UtilOperacao.SINAIS.contains(this.getUltimaInstrucaoOperacao()) || this.getUltimaInstrucaoOperacao().contains(UtilOperacao.CONST_PONTO))){

            return;

        }else if(this.isZeroUltimoValor(this.removerSinalValor(this.getUltimaInstrucaoOperacao())) && !this.isPonto(instrucao)){

            this.operacao = (this.operacao.substring(0, this.operacao.length() - 1) + instrucao);

        }else if(this.getUltimaInstrucaoOperacao().equals(UtilOperacao.CONST_INFINITY)){

            this.operacao = instrucao;

        }else{

            this.operacao += instrucao;

        }

    }

    private String getUltimaInstrucaoOperacao(){

        String[] vetOperacoes = this.operacao.split(UtilOperacao.SEPARADOR_OPERACAO);

        return vetOperacoes[vetOperacoes.length-1];

    }

    private boolean isPonto(String instrucao){
        return instrucao.equals(UtilOperacao.CONST_PONTO);
    }

    private boolean isZeroUltimoValor(String ultValor){
        return ultValor.replace(" ","").equals("0");
    }

    private String removerSinalValor(String valor){
        return valor.replace(UtilOperacao.SINAL_SUBTRACAO, "");
    }

    private String getInstrucaoComSeparador(String instrucao){
        return UtilOperacao.SEPARADOR_OPERACAO + instrucao + UtilOperacao.SEPARADOR_OPERACAO;
    }

    private String getInstrucaoValorNegatico(String instrucao){
        return instrucao;
    }

    public void setOperacao(String operacao){
        this.operacao = operacao;
    }

}