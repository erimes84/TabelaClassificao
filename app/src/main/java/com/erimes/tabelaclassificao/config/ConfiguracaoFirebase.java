package com.erimes.tabelaclassificao.config;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfiguracaoFirebase {

    private static DatabaseReference database;



    //Retorna instancia do FirebaseDatabase
    public static DatabaseReference getFirebaseDatabase(){

        if(database==null){
            database=FirebaseDatabase.getInstance().getReference();
        }
        return database;

    }

}
