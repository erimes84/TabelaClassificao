package com.erimes.tabelaclassificao.Fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erimes.tabelaclassificao.R;
import com.erimes.tabelaclassificao.activity.MainActivity;
import com.erimes.tabelaclassificao.adapter.AtletaClassificacaoAdapter;
import com.erimes.tabelaclassificao.config.ConfiguracaoFirebase;
import com.erimes.tabelaclassificao.model.Atleta;
import com.erimes.tabelaclassificao.model.Categorias;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassificacaoFragment extends Fragment {

                         //recyclerViewListaContatos
    private RecyclerView recyclerViewClassificacao;
    private AtletaClassificacaoAdapter adapter;
    private ArrayList<Atleta> listaAtletas = new ArrayList<>();
    private Query classificacaoRef;
    private Query corredoresRef;
    private ValueEventListener valueEventListenerClassificacao;
    private List<String> voltas = new ArrayList<String>();
    private ArrayList<String> corredores = new ArrayList<String>();
    private ValueEventListener eventListener;
    private ChildEventListener childEventListener;
    private String selecaoSpinner;
    private String selecaoSpinnerTemp;



    @Override
    public void onResume() {
        super.onResume();



    }





    public ClassificacaoFragment() {
        // Required empty public constructor
    }


    public void listenerTabela(){

        int x=0;
        for (String volta:voltas){

            Log.i("infuX", "for 2o listener");
            //Log.i("infuXY", String.valueOf(selecaoSpinner.length()));

                final Query corredoresRef = ConfiguracaoFirebase.getFirebaseDatabase().child("NovoCronometro").child("Tempos").child(selecaoSpinner).child(voltas.get(x)).orderByChild("TempoTotal");

            Log.i("infuXYZ", String.valueOf(corredoresRef.getRef()));
            x++;
            eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {


                    Log.i("infu","DataSnapshot 2o listener"+dataSnapshot2);
                    for (DataSnapshot dados3 : dataSnapshot2.getChildren()) {
                        Log.i("infuVoltas", "for dentro 2o listener"+dados3.getValue());
                        Atleta atleta = dados3.getValue(Atleta.class);
                        listaAtletas.add(atleta);
                        adapter.notifyDataSetChanged();
                        Log.i("infu333","adapterok");

                    }
                    corredoresRef.removeEventListener(eventListener);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError2) {

                }
            };
            corredoresRef.addListenerForSingleValueEvent(eventListener);

        }


    }





    public void eventListenerClassificacao() {


            classificacaoRef = ConfiguracaoFirebase.getFirebaseDatabase().child("NovoCronometro").child("Tempos").child(selecaoSpinner).orderByValue();



        valueEventListenerClassificacao = classificacaoRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                Log.i("infu", "DataSnapshot 1o listener" + dataSnapshot);
                listaAtletas.clear();
                voltas.clear();
                for (DataSnapshot dadosVoltas2 : dataSnapshot.getChildren()) {
                    voltas.add(dadosVoltas2.getKey());
                    Log.i("infuVoltas", "for dentro 1o listener");


                    /*
                    for (DataSnapshot dados:dadosVoltas2.getChildren()) {

                        Atleta atleta = dados.getValue(Atleta.class);
                        listaAtletas.add(atleta);
                        adapter.notifyDataSetChanged();
                    }


                    Query corredoresRef = ConfiguracaoFirebase.getFirebaseDatabase().child("NovoCronometro").child("Tempos").child("TempoTotalteste").child(dadosVoltas2.getKey()).orderByChild("TempoTotal");

                    eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot2) {


                            for (DataSnapshot dados3 : dataSnapshot2.getChildren()) {

                                Atleta atleta = dados3.getValue(Atleta.class);
                                listaAtletas.add(atleta);
                                adapter.notifyDataSetChanged();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError2) {

                        }
                    };
                    corredoresRef.addListenerForSingleValueEvent(eventListener);
                    */
                }
                if (voltas.size() == dataSnapshot.getChildrenCount()) {
                    Log.i("infuVoltas", "condicao pra chamar 2o listener");
                    listenerTabela();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classificacao, container, false);

        selecaoSpinnerTemp=this.getArguments().getString("SELECAO_CHAVE");
        if (selecaoSpinnerTemp!=null) {

            selecaoSpinner="TempoTotal"+selecaoSpinnerTemp;
            Log.i("selecao2",selecaoSpinner);
            Log.i("selecao2Temp",selecaoSpinnerTemp);
            eventListenerClassificacao();
        }else if (selecaoSpinnerTemp==null)Log.i("selecao2vazio","Vazio");






        //Configurações iniciais
        recyclerViewClassificacao = view.findViewById(R.id.recyclerViewClassificacao);


        //Configurar Adapter
        adapter = new AtletaClassificacaoAdapter(listaAtletas, getContext());

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManagerClassificacao = new LinearLayoutManager(getContext());
        recyclerViewClassificacao.setLayoutManager(layoutManagerClassificacao);
        recyclerViewClassificacao.setHasFixedSize(true);
        recyclerViewClassificacao.setAdapter(adapter);

        return view;

    }

}
