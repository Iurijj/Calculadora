package iuri.android.calculadora.unitario;

import junit.framework.TestCase;

import org.junit.Test;

import iuri.android.calculadora.service.CalcularOperacao;
import iuri.android.calculadora.service.MontarOperacao;

public class TestCalcularOperacao extends TestCase {

    public void testEfetuarCalculoOperacaoSoma(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_+_7");

        assertEquals("15.0", expressaoResultante);

    }

    public void testEfetuarCalculoOperacaoSubtracao(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_-_7");

        assertEquals("1.0", expressaoResultante);

    }

    public void testEfetuarCalculoOperacaoSubtracaoResultadoNegativo(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("2_-_7");

        assertEquals("-5.0", expressaoResultante);

    }

    public void testEfetuarCalculoOperacaoMultiplicacao(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_*_7");

        assertEquals("56.0", expressaoResultante);

    }

    public void testEfetuarCalculoOperacaoDivisao(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_/_2");

        assertEquals("4.0", expressaoResultante);

    }

    public void testEfetuarCalculoOperacaoDivisaoPorZero(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_/_0");

        assertEquals("Infinity", expressaoResultante);

    }

    public void testEfetuarCalculoOperacaoExpressaoIncomp(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_/_");

        assertEquals("8_/_",expressaoResultante);

    }

    public void testEfetuarCalculoRespeitandoOrdemDeOperacao(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_+_2_*_6");

        assertEquals("20.0", expressaoResultante);

    }

}