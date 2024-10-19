package com.example.projectingresso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DadosCompraActivity extends AppCompatActivity {

    private TextView lblID;
    private TextView lblValor;
    private TextView lblTaxaConveniencia;
    private TextView lblValorFinal;
    private TextView lblFuncaoDesempenhada;
    private Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_compra);

        lblID = findViewById(R.id.lblID);
        lblValor = findViewById(R.id.lblValor);
        lblTaxaConveniencia = findViewById(R.id.lblTaxaConveniencia);
        lblValorFinal = findViewById(R.id.lblValorFinal);
        lblFuncaoDesempenhada = findViewById(R.id.lblFuncaoDesempenhada);
        btnVoltar = findViewById(R.id.btnVoltar);

        Intent intent = getIntent();

        String codigoIdentificador = intent.getStringExtra("codigoIdentificador");
        float valor = intent.getFloatExtra("valor", 0);
        float taxaConveniencia = intent.getFloatExtra("taxaConveniencia", 0);
        float valorFinal = intent.getFloatExtra("valorFinal", 0);
        String funcaoDesempenhada = intent.getStringExtra("funcaoDesempenhada");

        lblID.setText("Código Identificador: " + codigoIdentificador);
        lblValor.setText("Valor: " + valor);
        lblTaxaConveniencia.setText("Taxa de Conveniência: " + taxaConveniencia);
        lblValorFinal.setText("Valor Final: " + valorFinal);

        if (funcaoDesempenhada != null) {
            lblFuncaoDesempenhada.setText("Função Desempenhada: " + funcaoDesempenhada);
            lblFuncaoDesempenhada.setVisibility(View.VISIBLE);
        } else {
            lblFuncaoDesempenhada.setVisibility(View.GONE);
        }

        btnVoltar.setOnClickListener(view -> {
            Intent intentVoltar = new Intent(DadosCompraActivity.this, MainActivity.class);
            startActivity(intentVoltar);
            finish();
        });
    }
}