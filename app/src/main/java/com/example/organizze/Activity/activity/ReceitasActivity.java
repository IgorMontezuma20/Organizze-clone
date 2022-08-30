package com.example.organizze.Activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.organizze.Activity.config.FirebaseHelper;
import com.example.organizze.Activity.helper.Base64Custom;
import com.example.organizze.Activity.helper.DateCustom;
import com.example.organizze.Activity.model.Movimentacao;
import com.example.organizze.Activity.model.User;
import com.example.organizze.R;
import com.example.organizze.databinding.ActivityDespesasBinding;
import com.example.organizze.databinding.ActivityReceitasBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ReceitasActivity extends AppCompatActivity {

    private ActivityReceitasBinding binding;
    private Movimentacao movimentacao;
    private DatabaseReference reference = FirebaseHelper.getDatabaseReference();
    private FirebaseAuth auth = FirebaseHelper.getAuth();
    private Double receitaTotal;
    private Double receitaGerada;
    private Double receitaAtualizada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReceitasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.edtData.setText(DateCustom.dataAtual());
        recuperarReceitaTotal();

        binding.fabSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarReceita(view);
            }
        });

    }

    public void salvarReceita(View view){

        if(validarCamposReceita()){
            movimentacao = new Movimentacao();
            String data = binding.edtData.getText().toString();
            Double valorRecuperado = Double.parseDouble(binding.edtValor.getText().toString());

            movimentacao.setValor(valorRecuperado);
            movimentacao.setCategoria(binding.edtCategoria.getText().toString());
            movimentacao.setDescricao(binding.edtDescricao.getText().toString());
            movimentacao.setData(data);
            movimentacao.setTipo("r");

            receitaGerada = valorRecuperado;
            receitaAtualizada = receitaTotal + receitaGerada;
            atualizarReceita( receitaAtualizada );

            movimentacao.salvar(data);

            Toast.makeText(this, "Receita adicionada!", Toast.LENGTH_SHORT).show();
        }


    }

    public boolean validarCamposReceita(){

        String valor = binding.edtValor.getText().toString();
        String data = binding.edtData.getText().toString();
        String categoria = binding.edtCategoria.getText().toString();
        String descricao = binding.edtDescricao.getText().toString();

        if(!valor.isEmpty()){
            if(!data.isEmpty()){
                if(!categoria.isEmpty()){
                    if(!descricao.isEmpty()){
                        return true;
                    }else{
                        Toast.makeText(this, "Preencha a descrição.",
                                Toast.LENGTH_SHORT).show();

                        return false;
                    }
                }else{
                    Toast.makeText(this, "Preencha a categoria.",
                            Toast.LENGTH_SHORT).show();

                    return false;
                }
            }else{
                Toast.makeText(this, "Preencha a data.",
                        Toast.LENGTH_SHORT).show();

                return false;
            }
        }else{
            Toast.makeText(this, "Preencha o valor.",
                    Toast.LENGTH_SHORT).show();

            return false;
        }

    }

    public void recuperarReceitaTotal(){

        String emailUsuario = auth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference userRef = reference.child("usuarios").child(idUsuario);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                User user = snapshot.getValue(User.class);
                receitaTotal = user.getReceitaTotal();

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    public void atualizarReceita(Double receita){
        String emailUsuario = auth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(emailUsuario);
        DatabaseReference userRef = reference.child("usuarios").child(idUsuario);

        userRef.child("receitaTotal").setValue(receita);
    }
}