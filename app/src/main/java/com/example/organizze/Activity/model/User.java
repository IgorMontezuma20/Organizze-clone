package com.example.organizze.Activity.model;

import com.example.organizze.Activity.config.FirebaseHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.security.PrivateKey;

public class User {

     private String  nome;
     private String email;
     private String senha;
     private String idUsuario;
     private Double receitaTotal = 0.00;
     private Double despesaTotal = 0.00;

    public User() {
    }

    public void salvar(){
        DatabaseReference reference = FirebaseHelper.getDatabaseReference();
        reference.child("usuarios")
                .child(this.idUsuario)
                .setValue(this);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Exclude
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getReceitaTotal() {
        return receitaTotal;
    }

    public void setReceitaTotal(Double receitaTotal) {
        this.receitaTotal = receitaTotal;
    }

    public Double getDespesaTotal() {
        return despesaTotal;
    }

    public void setDespesaTotal(Double despesaTotal) {
        this.despesaTotal = despesaTotal;
    }
}
