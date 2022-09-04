package com.example.organizze.Activity.model;

import android.widget.Toast;

import com.example.organizze.Activity.config.FirebaseHelper;
import com.example.organizze.Activity.helper.Base64Custom;
import com.example.organizze.Activity.helper.DateCustom;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class Movimentacao {

    private String data;
    private String categoria;
    private String descricao;
    private String tipo;
    private Double valor;
    private String key;

    public Movimentacao() {
    }

    public void salvar(String dataEscolhida){

        FirebaseAuth auth = FirebaseHelper.getAuth();

        //Recuperando email do user
        String idUsuario = Base64Custom.codificarBase64(auth.getCurrentUser().getEmail());

        //Recuperando a data da movimentação
        String mesAno = DateCustom.mesAnoDataEscolhida(dataEscolhida);

        DatabaseReference reference = FirebaseHelper.getDatabaseReference();
        reference.child("movimentacao")
                .child(idUsuario)
                .child(mesAno)
                .push()
                .setValue(this);

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
