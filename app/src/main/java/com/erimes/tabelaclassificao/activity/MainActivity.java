package com.erimes.tabelaclassificao.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.erimes.tabelaclassificao.Fragment.ClassificacaoFragment;
import com.erimes.tabelaclassificao.Fragment.InscritosFragment;
import com.erimes.tabelaclassificao.Fragment.CategoriasFragment;
import com.erimes.tabelaclassificao.R;
import com.erimes.tabelaclassificao.config.ConfiguracaoFirebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SmartTabLayout smartTabLayout;
    private DatabaseReference categoriasRef;
    private ArrayList<String> listaCategorias = new ArrayList<>();
    public MaterialSpinner spinnerCategorias;
    private TextView textView;
    public String categoriaEscolhida;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        categoriasRef = ConfiguracaoFirebase.getFirebaseDatabase()
                .child("NovoCronometro").child("Categorias");


            //Parte nova para recuperar categorias
            //valueEventListenerCategorias = categoriasRef.addListenerForSingleValueEvent
            categoriasRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listaCategorias.clear();
                    Log.i("teste", String.valueOf(dataSnapshot.getChildrenCount()));
                    for (DataSnapshot dados: dataSnapshot.getChildren()){


                        listaCategorias.add(dados.getKey());
                        Log.i("teste",dados.getKey());
                        Log.i("mengo", listaCategorias.toString());
                    }
                    if (listaCategorias.size()==dataSnapshot.getChildrenCount()){
                        preencheSpinner();

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




            //categoriasRef.removeEventListener(valueEventListenerCategorias);


        viewPager = findViewById(R.id.viewpager);
        smartTabLayout = findViewById(R.id.viewPagerTab);



        //Aplica configurações Action Bar
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("Tabela Classificação");

        //Configura as abas
        FragmentPagerAdapter adapter = new FragmentPagerItemAdapter(
        getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("Classificação",ClassificacaoFragment.class)
                        .add("Inscritos",InscritosFragment.class)
                        .add("Categorias",CategoriasFragment.class)
                        .create()
        );
        viewPager.setAdapter(adapter);
        smartTabLayout.setViewPager(viewPager);







        //preenche o spinner
        ArrayAdapter<String> adapterSpenner = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, listaCategorias);
/*
        adapterSpenner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorias = findViewById(R.id.spinner2);
        spinnerCategorias.setAdapter(adapterSpenner);
        Log.i("testeSpinner","Spinner Preenchido");*/










  }




  public void preencheSpinner(){

      ArrayAdapter<String> adapterSpenner = new ArrayAdapter<String>(this,
              android.R.layout.simple_spinner_item, listaCategorias);

      adapterSpenner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinnerCategorias = findViewById(R.id.spinner2);
      spinnerCategorias.setAdapter(adapterSpenner);
      Log.i("testeSpinner","Spinner Preenchido");
      spinnerCategorias.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
          @Override
          public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {




              categoriaEscolhida = item.toString();
              Log.i("selecao", categoriaEscolhida);
              Bundle bundle = new Bundle();
              bundle.putString("SELECAO_CHAVE",categoriaEscolhida);

              ClassificacaoFragment classificacaoFragment = new ClassificacaoFragment();
              classificacaoFragment.setArguments(bundle);

              getSupportFragmentManager().beginTransaction().replace(R.id.container,classificacaoFragment).commit();




          }
      });
  }
}