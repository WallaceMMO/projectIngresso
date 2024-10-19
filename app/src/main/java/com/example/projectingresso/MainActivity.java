/*
 *@author:<Wallace Moura Machado de Oliveira;1110482413004>
 */

package com.example.projectingresso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText txtCodigoIdentificador;
    private EditText txtValor;
    private EditText txtTaxaConveniencia;
    private EditText txtFuncaoDesempenhada;
    private Button btnComprar;
    private boolean ingressoVip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCodigoIdentificador = findViewById(R.id.txtID);
        txtValor = findViewById(R.id.txtValor);
        txtTaxaConveniencia = findViewById(R.id.txtTaxaConveniencia);
        txtFuncaoDesempenhada = findViewById(R.id.txtuncaoDesempenhada);
        btnComprar = findViewById(R.id.btnComprar);

        Switch swtVip = findViewById(R.id.swtVip);
        swtVip.setOnCheckedChangeListener((view, isChecked) -> {
            ingressoVip = isChecked;
            if (isChecked) {
                txtFuncaoDesempenhada.setVisibility(View.VISIBLE);
            } else {
                txtFuncaoDesempenhada.setVisibility(View.GONE);
            }
        });

        btnComprar.setOnClickListener(view -> {
            String codigoIdentificador = txtCodigoIdentificador.getText().toString().trim();
            String valorStr = txtValor.getText().toString().trim();
            String taxaConvenienciaStr = txtTaxaConveniencia.getText().toString().trim();

            if (codigoIdentificador.isEmpty() || valorStr.isEmpty() || taxaConvenienciaStr.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show();
                return;
            }

            float valor = Float.parseFloat(txtValor.getText().toString());
            float taxaConveniencia = Float.parseFloat(txtTaxaConveniencia.getText().toString());

            Ingresso ingresso;

            if (ingressoVip) {
                String funcaoDesempenhada = txtFuncaoDesempenhada.getText().toString();

                if (funcaoDesempenhada.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, preencha a Função Desempenhada.", Toast.LENGTH_SHORT).show();
                    return;
                }

                ingresso = new IngressoVIP(codigoIdentificador, valor, funcaoDesempenhada);
            } else {
                ingresso = new Ingresso(codigoIdentificador, valor);
            }

            float valorFinal = ingresso.valorFinal(taxaConveniencia);

            Intent intent = new Intent(MainActivity.this, DadosCompraActivity.class);
            intent.putExtra("codigoIdentificador", ingresso.getCodigoIdentificador());
            intent.putExtra("valor", ingresso.getValor());
            intent.putExtra("taxaConveniencia", taxaConveniencia);
            intent.putExtra("valorFinal", valorFinal);

            if (ingressoVip) {
                intent.putExtra("funcaoDesempenhada", ((IngressoVIP) ingresso).getFuncaoDesempenhada());
            }

            startActivity(intent);
        });
    }
}