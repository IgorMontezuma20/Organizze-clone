package com.example.organizze.Activity.config;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguracaoFirebase {

    private  static FirebaseAuth autenticacao;

    //Retorna instância do FirebaseAuth
    public static FirebaseAuth getFirebaseAuth(){
        if(autenticacao== null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

}
