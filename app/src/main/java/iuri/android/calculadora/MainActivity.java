package iuri.android.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import iuri.android.calculadora.service.CalcularOperacao;
import iuri.android.calculadora.service.MontarOperacao;
import iuri.android.calculadora.util.UtilOperacao;

public class MainActivity extends AppCompatActivity {

    private TextView txtOperacao;
    private TextView txtHistorico;
    private MontarOperacao montarOperacao;
    private CalcularOperacao calcular;

    private String operacao = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.init();

    }

    private void init(){

        this.txtOperacao = findViewById(R.id.txtOperacao);
        this.txtHistorico = findViewById(R.id.txtHistorico);
        this.montarOperacao = new MontarOperacao(this.operacao);
        this.calcular = new CalcularOperacao();

        this.atualizarTxtOperacao();

    }

    public void limparValores(View v){

        this.operacao = this.montarOperacao.limparUltOperacao();
        this.atualizarTxtOperacao();

    }

    public void pegarInstrucao(View v){

        this.operacao = this.montarOperacao.tratarInstrucao(((TextView)v).getText().toString());
        this.atualizarTxtOperacao();

    }

    public void calcular(View v){

        this.atualizarTxtHistorico();

        this.operacao = this.calcular.efetuarCalculoOperacao(this.operacao);
        this.montarOperacao.setOperacao(this.operacao);

        this.atualizarTxtOperacao();

    }

    private void atualizarTxtOperacao(){
        this.txtOperacao.setText(this.operacao.replace(UtilOperacao.SEPARADOR_OPERACAO," "));
    }

    private void atualizarTxtHistorico(){
        this.txtHistorico.setText(this.operacao.replace(UtilOperacao.SEPARADOR_OPERACAO," "));
    }

}