package iuri.android.calculadora.unitario;

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

        assertEquals("255", instrucaoInicial);

    }

    public void testTratarInstrucaoTrocaOperacao(){

        MontarOperacao montarOperacao = new MontarOperacao("2_/_2_*_");
        String expressaoResultante = montarOperacao.tratarInstrucao("+");

        assertEquals("2_/_2_+_", expressaoResultante);

    }

    public void testTratarInstrucaoAceitaSinalNegativo(){

        MontarOperacao montarOperacao = new MontarOperacao("23_*_5_/_");
        String expressaoResultante = montarOperacao.tratarInstrucao("-");

        assertEquals("23_*_5_/_-", expressaoResultante);

    }

    public void testTratarInstrucaoLimparTodaExpressoa(){

        MontarOperacao montarOperacao = new MontarOperacao("2_/_5");

        String expressaoResultante = montarOperacao.limparUltOperacao();
        expressaoResultante = montarOperacao.limparUltOperacao();
        expressaoResultante = montarOperacao.limparUltOperacao();

        assertEquals("0", expressaoResultante);

    }

    public void testTratarInstrucaoZeroIsSubstituido(){

        MontarOperacao montarOperacao = new MontarOperacao("0");
        String expressaoResultante = montarOperacao.tratarInstrucao("2");

        assertEquals("2", expressaoResultante);

    }

    public void testTratarLimparExpressaoInfinito(){

        MontarOperacao montarOperacao = new MontarOperacao();
        CalcularOperacao calcularOperacao = new CalcularOperacao();

        String resultadoExpressao = calcularOperacao.efetuarCalculoOperacao("2_/_0");

        montarOperacao.setOperacao(resultadoExpressao);

        String expressaoResultante = montarOperacao.limparUltOperacao();

        assertEquals("0", expressaoResultante);

    }

    public void testTratarTrocarExpressaoInfinito(){

        MontarOperacao montarOperacao = new MontarOperacao();
        CalcularOperacao calcularOperacao = new CalcularOperacao();

        String resultadoExpressao = calcularOperacao.efetuarCalculoOperacao("2_/_0");

        montarOperacao.setOperacao(resultadoExpressao);

        String expressaoResultante = montarOperacao.tratarInstrucao("9");

        assertEquals("9", expressaoResultante);

    }

    public void testTratarApenasUmPonto(){

        MontarOperacao montarOperacao = new MontarOperacao("2_/_5");

        String expressaoResultante = montarOperacao.tratarInstrucao(".");
        expressaoResultante = montarOperacao.tratarInstrucao(".");
        expressaoResultante = montarOperacao.tratarInstrucao(".");

        assertEquals("2_/_5.", expressaoResultante);

    }

    public void testTratarOperacaoAposSinalNegativoNaoAceitaOperadores(){

        MontarOperacao montarOperacao = new MontarOperacao("2_/_-");
        String expressaoResultante = montarOperacao.tratarInstrucao("*");

        assertEquals("2_/_-", expressaoResultante);

    }

    public void testTratarOperacaoAposSinalNegativoAceitaApenasNumeros(){

        MontarOperacao montarOperacao = new MontarOperacao("2_/_-");
        String expressaoResultante = montarOperacao.tratarInstrucao("8");

        assertEquals("2_/_-8", expressaoResultante);

    }

}