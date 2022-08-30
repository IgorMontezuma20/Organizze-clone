package com.example.organizze.Activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.organizze.Activity.helper.DateCustom;
import com.example.organizze.R;
import com.example.organizze.databinding.ActivityCadastroBinding;
import com.example.organizze.databinding.ActivityDespesasBinding;

public class DespesasActivity extends AppCompatActivity {

    private ActivityDespesasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDespesasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.edtData.setText(DateCustom.dataAtual());

    }
}