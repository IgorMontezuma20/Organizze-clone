package com.example.organizze.Activity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.organizze.Activity.config.ConfiguracaoFirebase;
import com.example.organizze.Activity.config.FirebaseHelper;
import com.example.organizze.Activity.model.User;
import com.example.organizze.databinding.ActivityCadastroBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {

    private ActivityCadastroBinding binding;
    private FirebaseAuth autenticacao;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



       binding.butnCadastrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String nome = binding.edtNome.getText().toString();
               String email = binding.edtEmail.getText().toString();
               String senha = binding.edtSenha.getText().toString();

               //Validação dos campos
               if(!nome.isEmpty()){
                   if(!email.isEmpty()){
                       if(!senha.isEmpty()){
                            user = new User();
                            user.setNome(nome);
                            user.setEmail(email);
                            user.setSenha(senha);
                            cadastrarUser();
                       }else{
                           Toast.makeText(CadastroActivity.this,
                                   "Campo de SENHA obrigatório.", Toast.LENGTH_SHORT).show();
                       }
                   }else{
                       Toast.makeText(CadastroActivity.this,
                               "Campo de EMAIL obrigatório.", Toast.LENGTH_SHORT).show();
                   }
               }
               else{
                   Toast.makeText(CadastroActivity.this,
                           "Campo de NOME obrigatório.", Toast.LENGTH_SHORT).show();
               }

           }
       });


    }

    public void cadastrarUser(){

       autenticacao = FirebaseHelper.getAuth();
       autenticacao.createUserWithEmailAndPassword(
            user.getEmail(), user.getSenha()
       ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){

                   finish();
               }else{

                   String excecao = "";
                   try {
                       throw task.getException();
                   }catch (FirebaseAuthWeakPasswordException e){
                       excecao = "Digite uma senha mais forte!";
                   }catch (FirebaseAuthInvalidCredentialsException e){
                       excecao = "Por favor, informe um e-mail válido.";
                   }catch (FirebaseAuthUserCollisionException e){
                       excecao = "Esta conta já foi registrada.";
                   }catch (Exception e){
                       excecao = "Erro ao cadastrar o usuário: "+ e.getMessage();
                       e.printStackTrace();
                   }
                   Toast.makeText(CadastroActivity.this,
                           excecao, Toast.LENGTH_SHORT).show();
               }
           }
       });

    }

}