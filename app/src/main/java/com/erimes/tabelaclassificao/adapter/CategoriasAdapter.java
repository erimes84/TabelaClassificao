package com.erimes.tabelaclassificao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erimes.tabelaclassificao.R;
import com.erimes.tabelaclassificao.model.Categorias;

import java.util.List;

public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.MyViewHolderCategorias> {


    List<Categorias> categorias;
    Context context;

    public CategoriasAdapter(List<Categorias> categorias, Context context) {
        this.categorias = categorias;
        this.context = context;
    }

    @Override
    public MyViewHolderCategorias onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemListaCategorias = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_categorias,parent,false);


        return new MyViewHolderCategorias(itemListaCategorias);
    }

    @Override
    public void onBindViewHolder(MyViewHolderCategorias holder, int position) {

        Categorias categoriasModel = categorias.get(position);

        holder.categoria.setText(categoriasModel.getNomeCategoria().substring(1,categoriasModel.getNomeCategoria().length()-1));
        holder.voltas.setText(categoriasModel.getVoltas());
        holder.numeracaDe.setText(categoriasModel.getDe());
        holder.numeracaoAte.setText(categoriasModel.getAte());
        holder.tituloVoltas.setText("Voltas");
        holder.tituloNumeracao.setText("Numeração");
        holder.tituloAte.setText("até");


    }

    @Override
    public int getItemCount() {
        return categorias.size();
    }


    public class MyViewHolderCategorias extends RecyclerView.ViewHolder{

        TextView categoria,tituloVoltas,tituloNumeracao,tituloAte,voltas,numeracaDe,numeracaoAte;

        public MyViewHolderCategorias(View itemView) {
            super(itemView);

            categoria = itemView.findViewById(R.id.textViewCategoria);
            voltas = itemView.findViewById(R.id.textViewVoltas);
            numeracaDe = itemView.findViewById(R.id.textViewNumeracaoDe);
            numeracaoAte = itemView.findViewById(R.id.textViewNumeracaoAte);
            tituloVoltas = itemView.findViewById(R.id.textViewTituloVoltas);
            tituloNumeracao = itemView.findViewById(R.id.textViewTituloNumeracao);
            tituloAte = itemView.findViewById(R.id.textViewTituloAte);


        }
    }


}



