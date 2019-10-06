package iuri.android.calculadora;

import junit.framework.TestCase;

import org.junit.Test;

import iuri.android.calculadora.service.CalcularOperacao;
import iuri.android.calculadora.service.MontarOperacao;

public class TestCalcularOperacao extends TestCase {

    public void testEfetuarCalculoOperacaoSoma(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_+_7");

        assertEquals(expressaoResultante,"15.0");

    }

    public void testEfetuarCalculoOperacaoSubtracao(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_-_7");

        assertEquals(expressaoResultante,"1.0");

    }

    public void testEfetuarCalculoOperacaoSubtracaoResultadoNegativo(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("2_-_7");

        assertEquals(expressaoResultante,"-5.0");

    }

    public void testEfetuarCalculoOperacaoMultiplicacao(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_*_7");

        assertEquals(expressaoResultante,"56.0");

    }

    public void testEfetuarCalculoOperacaoDivisao(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_/_2");

        assertEquals(expressaoResultante,"4.0");

    }

    public void testEfetuarCalculoOperacaoDivisaoPorZero(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_/_0");

        assertEquals(expressaoResultante,"Infinity");

    }

    public void testEfetuarCalculoOperacaoExpressaoIncomp(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_/_");

        assertEquals(expressaoResultante,"8_/_");


    }

}