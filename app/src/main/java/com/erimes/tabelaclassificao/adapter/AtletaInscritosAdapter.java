package com.erimes.tabelaclassificao.adapter;


import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erimes.tabelaclassificao.R;
import com.erimes.tabelaclassificao.model.InscritosModel;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


//Adapter
public class AtletaInscritosAdapter extends RecyclerView.Adapter<AtletaInscritosAdapter.MyViewHolderInscritos> {



    List<InscritosModel> inscritos;
    Context context;

    public AtletaInscritosAdapter(List<InscritosModel> inscritos, Context context) {
        this.inscritos = inscritos;
        this.context = context;
    }



    @NonNull
    @Override
    public MyViewHolderInscritos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemListaInscritos = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_inscritos,viewGroup,false);


        return new MyViewHolderInscritos(itemListaInscritos);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderInscritos myViewHolder, int i) {

        InscritosModel inscritosModel = inscritos.get(i);

        myViewHolder.nomeInscritos.setText(inscritosModel.getNome().substring(1,inscritosModel.getNome().length()-1));
        myViewHolder.equipe.setText(inscritosModel.getEquipe().substring(1,inscritosModel.getEquipe().length()-1));
        myViewHolder.dataNascimento.setText(inscritosModel.getIdade());
        myViewHolder.cidade.setText(inscritosModel.getCidade().substring(1,inscritosModel.getCidade().length()-1));
        myViewHolder.numeroInscritos.setText(inscritosModel.getNumero());
        myViewHolder.categoriaInscritos.setText(inscritosModel.getCategoria().substring(1,inscritosModel.getCategoria().length()-1));
        myViewHolder.tituloCidade.setText("Cidade");
        myViewHolder.numeroInscritosTitulo.setText("Número");
        myViewHolder.categoriaInscritosTitulo.setText("Categoria");
        myViewHolder.tituloNome.setText("Nome");
        myViewHolder.tituloDataNascimento.setText("Idade");
        myViewHolder.tituloEquipe.setText("Equipe");

        String foto = inscritosModel.getFotoUrl().substring(3,inscritosModel.getFotoUrl().length()-3);
        Uri uri = Uri.parse(foto.replace("\\",""));
        Glide.with(context).load(uri).into(myViewHolder.fotoInscritos);

    }

    @Override
    public int getItemCount() {

        return inscritos.size();
    }

    public class MyViewHolderInscritos extends RecyclerView.ViewHolder{

        CircleImageView fotoInscritos;
        TextView nomeInscritos,equipe,dataNascimento,cidade,tituloCidade,tituloNome,tituloEquipe,tituloDataNascimento,numeroInscritos,categoriaInscritos,numeroInscritosTitulo,categoriaInscritosTitulo;



        public MyViewHolderInscritos(@NonNull View itemView) {
            super(itemView);

            fotoInscritos = itemView.findViewById(R.id.imageViewFotoInscritos);
            nomeInscritos = itemView.findViewById(R.id.textViewNomeInscritos);
            equipe = itemView.findViewById(R.id.textViewEquipe);
            numeroInscritos = itemView.findViewById(R.id.textViewNumeroInscritos);
            categoriaInscritos = itemView.findViewById(R.id.textViewCategoria);
            categoriaInscritosTitulo = itemView.findViewById(R.id.textViewCategoriaTitulo);
            numeroInscritosTitulo = itemView.findViewById(R.id.textViewNumeroTitulo);
            dataNascimento = itemView.findViewById(R.id.textViewDataNascimento);
            cidade = itemView.findViewById(R.id.textViewCidade);
            tituloCidade = itemView.findViewById(R.id.textViewCidadeTitulo);
            tituloNome = itemView.findViewById(R.id.textViewNomeTitulo);
            tituloEquipe = itemView.findViewById(R.id.textViewEquipeTitulo);
            tituloDataNascimento = itemView.findViewById(R.id.textViewNascimentoTitulo);

        }
    }
}
