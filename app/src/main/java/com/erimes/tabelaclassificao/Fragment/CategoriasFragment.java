package com.erimes.tabelaclassificao.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erimes.tabelaclassificao.R;
import com.erimes.tabelaclassificao.adapter.CategoriasAdapter;
import com.erimes.tabelaclassificao.config.ConfiguracaoFirebase;
import com.erimes.tabelaclassificao.model.Categorias;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriasFragment extends Fragment {


    private RecyclerView recyclerViewCategorias;
    private CategoriasAdapter categoriasAdapter;
    private ArrayList<Categorias> listaCategorias = new ArrayList<>();
    private Query categoriasRef;
    private ValueEventListener valueEventListenerCategorias;


    public CategoriasFragment() {
        // Required empty public constructor
    }

    public void eventListenerCategorias(){

        categoriasRef = ConfiguracaoFirebase.getFirebaseDatabase().child("NovoCronometro").child("Categorias").orderByChild("de");
        valueEventListenerCategorias = categoriasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaCategorias.clear();

                for (DataSnapshot dados: dataSnapshot.getChildren()){

                    Categorias categoriasModel = dados.getValue(Categorias.class);
                    listaCategorias.add(categoriasModel);
                    categoriasAdapter.notifyDataSetChanged();

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
        View view = inflater.inflate(R.layout.fragment_tempos, container, false);
        eventListenerCategorias();

        recyclerViewCategorias = view.findViewById(R.id.recyclerViewCategorias);

        categoriasAdapter = new CategoriasAdapter(listaCategorias, getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewCategorias.setLayoutManager(layoutManager);
        recyclerViewCategorias.setHasFixedSize(true);
        recyclerViewCategorias.setAdapter(categoriasAdapter);


        return view;
    }

}
