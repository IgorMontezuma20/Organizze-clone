package com.example.organizze.Activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.organizze.Activity.helper.DateCustom;
import com.example.organizze.Activity.model.Movimentacao;
import com.example.organizze.R;
import com.example.organizze.databinding.ActivityCadastroBinding;
import com.example.organizze.databinding.ActivityDespesasBinding;

public class DespesasActivity extends AppCompatActivity {

    private ActivityDespesasBinding binding;
    private Movimentacao movimentacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDespesasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.edtData.setText(DateCustom.dataAtual());
        binding.fabSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDespesa(view);
            }
        });

    }

    public void salvarDespesa(View view){

        movimentacao = new Movimentacao();
        String data = binding.edtData.getText().toString();
        movimentacao.setValor(Double.parseDouble(binding.edtValor.getText().toString()));
        movimentacao.setCategoria(binding.edtCategoria.getText().toString());
        movimentacao.setDescricao(binding.edtDescricao.getText().toString());
        movimentacao.setData(data);
        movimentacao.setTipo("d");
        movimentacao.salvar(data);
        Toast.makeText(this, "Despesa salva com sucesso!", Toast.LENGTH_SHORT).show();

    }
}