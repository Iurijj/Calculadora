package iuri.android.calculadora.unitario;

import junit.framework.TestCase;

import org.junit.Test;

import iuri.android.calculadora.service.CalcularOperacao;
import iuri.android.calculadora.service.MontarOperacao;

public class TesteMontarOperacao extends TestCase {

    public void testeLimparUltimoValor(){

        MontarOperacao montarOperacao = new MontarOperacao("3_+_5");
        String expressaoResutante = montarOperacao.limparUltOperacao();

        assertEquals(expressaoResutante, "3_+_");

    }

    public void testeConcatenarValores(){

        String instrucaoInicial = "0";

        MontarOperacao montarOperacao = new MontarOperacao(instrucaoInicial);

        instrucaoInicial = montarOperacao.tratarInstrucao("8");
        instrucaoInicial = montarOperacao.tratarInstrucao("8");
        instrucaoInicial = montarOperacao.tratarInstrucao("0");

        assertEquals("880", instrucaoInicial);

    }

    public void testeTrocarOperador(){

        MontarOperacao montarOperacao = new MontarOperacao("8_/_");
        String expressaoResultante = montarOperacao.tratarInstrucao("+");

        assertEquals("8_+_", expressaoResultante);

    }

    public void testeAceitaSinalNegativo(){

        MontarOperacao montarOperacao = new MontarOperacao("8_/_");
        String expressaoResultante = montarOperacao.tratarInstrucao("-");

        assertEquals("8_/_-", expressaoResultante);

    }

    public void testeLimparTodaExpressao(){

        MontarOperacao montarOperacao = new MontarOperacao("5_+_8");

        String expressaoResultante = montarOperacao.limparUltOperacao();
        expressaoResultante = montarOperacao.limparUltOperacao();
        expressaoResultante = montarOperacao.limparUltOperacao();

        assertEquals("0", expressaoResultante);

    }

    public void testeSubstituirZero(){

        MontarOperacao montarOperacao = new MontarOperacao("5_+_0");
        String expressaoResultante = montarOperacao.tratarInstrucao("8");

        assertEquals("5_+_8", expressaoResultante);

    }

    public void testeLimparExpressaoInfinito(){

        MontarOperacao montarOperacao = new MontarOperacao();
        CalcularOperacao calcularOperacao = new CalcularOperacao();

        String resultadoExpressao = calcularOperacao.efetuarCalculoOperacao("2_/_0");

        montarOperacao.setOperacao(resultadoExpressao);

        String expressaoResultante = montarOperacao.limparUltOperacao();

        assertEquals("0", expressaoResultante);

    }

    public void testeSubstituirExpressaoInfinito(){

        MontarOperacao montarOperacao = new MontarOperacao();
        CalcularOperacao calcularOperacao = new CalcularOperacao();

        String resultadoExpressao = calcularOperacao.efetuarCalculoOperacao("2_/_0");

        montarOperacao.setOperacao(resultadoExpressao);

        String expressaoResultante = montarOperacao.tratarInstrucao("8");

        assertEquals("8", expressaoResultante);

    }

    public void testeAceitarApenasUmPonto(){

        MontarOperacao montarOperacao = new MontarOperacao("8");

        String expressaoResultante = montarOperacao.tratarInstrucao(".");
        expressaoResultante = montarOperacao.tratarInstrucao(".");

        assertEquals("8.", expressaoResultante);

    }

    public void testeNaoAceitarOperadorAposSinalNegativo(){

        MontarOperacao montarOperacao = new MontarOperacao("8_/_-");
        String expressaoResultante = montarOperacao.tratarInstrucao("*");

        assertEquals("8_/_-", expressaoResultante);

    }

    public void testeAceitarValorAposSinalNegativo(){

        MontarOperacao montarOperacao = new MontarOperacao("8_/_-");
        String expressaoResultante = montarOperacao.tratarInstrucao("8");

        assertEquals("8_/_-8", expressaoResultante);

    }

}