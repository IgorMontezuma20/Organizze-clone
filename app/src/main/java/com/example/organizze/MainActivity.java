package com.example.organizze;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.organizze.Activity.CadastroActivity;
import com.example.organizze.Activity.LoginActivity;
import com.example.organizze.databinding.ActivityMainBinding;
import com.example.organizze.databinding.IntroCadastroBinding;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {

    private IntroCadastroBinding binding;
    private Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = IntroCadastroBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

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


        binding.btnCadastrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(MainActivity.this, CadastroActivity.class));
               Log.d("TAG", "onClick: Foi");
           }
        });
    }

    public void btnEntrar(View view){

    }

    public void btnCadastrar(View view){

    }
}