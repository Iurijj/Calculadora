package iuri.android.calculadora;

import junit.framework.TestCase;

import org.junit.Test;

import iuri.android.calculadora.service.CalcularOperacao;
import iuri.android.calculadora.service.MontarOperacao;

public class TesteMontarOperacao extends TestCase {

    public void testTratarInstrucaoLimpar(){

        MontarOperacao montarOperacao = new MontarOperacao("2_+_5");
        String expressaoResutante = montarOperacao.limparUltOperacao();

        assertEquals(expressaoResutante, "2_+_");

    }

    public void testTratarInstrucaoConcatenacao(){

        String instrucaoInicial = "0";

        MontarOperacao montarOperacao = new MontarOperacao(instrucaoInicial);

        instrucaoInicial = montarOperacao.tratarInstrucao("2");
        instrucaoInicial = montarOperacao.tratarInstrucao("5");
        instrucaoInicial = montarOperacao.tratarInstrucao("5");

        assertEquals(instrucaoInicial, "255");

    }

    public void testTratarInstrucaoTrocaOperacao(){

        MontarOperacao montarOperacao = new MontarOperacao("2_/_2_*_");
        String expressaoResultante = montarOperacao.tratarInstrucao("+");

        assertEquals(expressaoResultante, "2_/_2_+_");

    }

    public void testTratarInstrucaoAceitaSinalNegativo(){

        MontarOperacao montarOperacao = new MontarOperacao("23_*_5_/_");
        String expressaoResultante = montarOperacao.tratarInstrucao("-");

        assertEquals(expressaoResultante, "23_*_5_/_-");

    }

    public void testTratarInstrucaoLimparTodaExpressoa(){

        MontarOperacao montarOperacao = new MontarOperacao("2_/_5");

        String expressaoResultante = montarOperacao.limparUltOperacao();
        expressaoResultante = montarOperacao.limparUltOperacao();
        expressaoResultante = montarOperacao.limparUltOperacao();

        assertEquals(expressaoResultante, "0");

    }

    public void testTratarInstrucaoZeroIsSubstituido(){

        MontarOperacao montarOperacao = new MontarOperacao("0");
        String expressaoResultante = montarOperacao.tratarInstrucao("2");

        assertEquals(expressaoResultante, "2");

    }

    public void testTratarLimparExpressaoInfinito(){

        MontarOperacao montarOperacao = new MontarOperacao();
        CalcularOperacao calcularOperacao = new CalcularOperacao();

        String resultadoExpressao = calcularOperacao.efetuarCalculoOperacao("2_/_0");

        montarOperacao.setOperacao(resultadoExpressao);

        String expressaoResultante = montarOperacao.limparUltOperacao();

        assertEquals(expressaoResultante, "0");

    }

    public void testTratarTrocarExpressaoInfinito(){

        MontarOperacao montarOperacao = new MontarOperacao();
        CalcularOperacao calcularOperacao = new CalcularOperacao();

        String resultadoExpressao = calcularOperacao.efetuarCalculoOperacao("2_/_0");

        montarOperacao.setOperacao(resultadoExpressao);

        String expressaoResultante = montarOperacao.tratarInstrucao("9");

        assertEquals(expressaoResultante, "9");

    }

    public void testTratarApenasUmPonto(){

        MontarOperacao montarOperacao = new MontarOperacao("2_/_5");

        String expressaoResultante = montarOperacao.tratarInstrucao(".");
        expressaoResultante = montarOperacao.tratarInstrucao(".");
        expressaoResultante = montarOperacao.tratarInstrucao(".");

        assertEquals(expressaoResultante, "2_/_5.");

    }

    public void testTratarOperacaoAposSinalNegativoNaoAceitaOperadores(){

        MontarOperacao montarOperacao = new MontarOperacao("2_/_-");
        String expressaoResultante = montarOperacao.tratarInstrucao("*");

        assertEquals(expressaoResultante, "2_/_-");

    }

    public void testTratarOperacaoAposSinalNegativoAceitaApenasNumeros(){

        MontarOperacao montarOperacao = new MontarOperacao("2_/_-");
        String expressaoResultante = montarOperacao.tratarInstrucao("8");

        assertEquals(expressaoResultante, "2_/_-8");

    }

}