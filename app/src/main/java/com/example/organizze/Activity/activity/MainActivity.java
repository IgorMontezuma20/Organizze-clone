package com.example.organizze.Activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.organizze.Activity.activity.CadastroActivity;
import com.example.organizze.Activity.activity.LoginActivity;
import com.example.organizze.Activity.config.FirebaseHelper;
import com.example.organizze.R;
import com.example.organizze.databinding.IntroCadastroBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    private IntroCadastroBinding binding;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = IntroCadastroBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        verificarUsuarioLogado();

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(
                new FragmentSlide.Builder()
                        .background(android.R.color.white)
                        .fragment(R.layout.intro_1)
                        .build()
        );

        addSlide(
                new FragmentSlide.Builder()
                        .background(android.R.color.white)
                        .fragment(R.layout.intro_2)
                        .build()
        );

        addSlide(
                new FragmentSlide.Builder()
                        .background(android.R.color.white)
                        .fragment(R.layout.intro_3)
                        .build()
        );

        addSlide(
                new FragmentSlide.Builder()
                        .background(android.R.color.white)
                        .fragment(R.layout.intro_4)
                        .build()
        );

        addSlide(
                new FragmentSlide.Builder()
                        .background(android.R.color.white)
                        .fragment(R.layout.intro_cadastro)
                        .canGoForward(false)
                        .build()
        );



    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void btnEntrar (View view) {
        startActivity(new Intent(this, LoginActivity.class));

    }

    public void btnCadastrar(View view) {
        startActivity(new Intent(this, CadastroActivity.class));

    }

    public void verificarUsuarioLogado() {
        autenticacao = FirebaseHelper.getAuth();
        //autenticacao.signOut();

        if (autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }

    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));

    }
}