package com.erimes.tabelaclassificao.Fragment;


import android.content.Context;
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
import com.erimes.tabelaclassificao.R;
import com.erimes.tabelaclassificao.adapter.AtletaInscritosAdapter;
import com.erimes.tabelaclassificao.config.ConfiguracaoFirebase;
import com.erimes.tabelaclassificao.model.InscritosModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InscritosFragment extends Fragment {

    private RecyclerView recyclerViewInscritos;
    private AtletaInscritosAdapter atletaInscritosAdapter;
    private ArrayList<InscritosModel> listaInscritos = new ArrayList<>();
    private DatabaseReference inscritosRef;



    public InscritosFragment() {
        // Required empty public constructor
    }




    public void eventListenerInscritos()

    {
        inscritosRef = ConfiguracaoFirebase.getFirebaseDatabase().child("NovoCronometro").child("Atletas").child("teste");
        inscritosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listaInscritos.clear();

                for (DataSnapshot dados : dataSnapshot.getChildren()) {

                    InscritosModel inscritosModel = dados.getValue(InscritosModel.class);
                    listaInscritos.add(inscritosModel);

                }

                atletaInscritosAdapter.notifyDataSetChanged();
                Log.i("Teste", "Funcionou");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        eventListenerInscritos();
    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_inscritos, container, false);

            eventListenerInscritos();


            //Configurações iniciais
            recyclerViewInscritos = view.findViewById(R.id.recyclerViewInscritos);
            //categoriasRef = ConfiguracaoFirebase.getFirebaseDatabase().child("NovoCronometro").child("Categorias");

            //Configurar Adapter
            atletaInscritosAdapter = new AtletaInscritosAdapter(listaInscritos, getContext());


            //Configurar RecyclerView
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerViewInscritos.setLayoutManager(layoutManager);
            recyclerViewInscritos.setHasFixedSize(true);
            recyclerViewInscritos.setAdapter(atletaInscritosAdapter);

            return view;


        }


    @Override
    public void onPause() {
        super.onPause();
        Log.i("Teste","Parar");
    }


}
