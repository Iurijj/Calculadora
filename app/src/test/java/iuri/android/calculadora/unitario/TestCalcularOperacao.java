package iuri.android.calculadora.unitario;

import junit.framework.TestCase;

import org.junit.Test;

import iuri.android.calculadora.service.CalcularOperacao;
import iuri.android.calculadora.service.MontarOperacao;

public class TestCalcularOperacao extends TestCase {

    public void testeSomarDoisValores(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_+_8");

        assertEquals("16.0", expressaoResultante);

    }

    public void testeSubtrairDoisValores(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_-_8");

        assertEquals("0.0", expressaoResultante);

    }

    public void testeSubtrairDoisValoresResultandoValorNegativo(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_-_9");

        assertEquals("-1.0", expressaoResultante);

    }

    public void testeMultiplicarDoisValores(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_*_8");

        assertEquals("64.0", expressaoResultante);

    }

    public void testeDividirDoisValores(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_/_8");

        assertEquals("1.0", expressaoResultante);

    }

    public void testeDividirPorZero(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_/_0");

        assertEquals("Infinity", expressaoResultante);

    }

    public void testeCalcularComOperacaoIncompleta(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("5_+_");

        assertEquals("5_+_",expressaoResultante);

    }

    public void testeCalcularRespeitandoOrdemMatematica(){

        CalcularOperacao calcularOperacao = new CalcularOperacao();
        String expressaoResultante = calcularOperacao.efetuarCalculoOperacao("8_+_2_*_6");

        assertEquals("20.0", expressaoResultante);

    }

}