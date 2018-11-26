package com.erimes.tabelaclassificao.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.erimes.tabelaclassificao.R;
import com.erimes.tabelaclassificao.model.Atleta;
import com.erimes.tabelaclassificao.model.Categorias;
import com.google.firebase.database.DataSnapshot;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


//ContatosAdapter
public class AtletaClassificacaoAdapter extends RecyclerView.Adapter<AtletaClassificacaoAdapter.MyViewHolder>{


    List<Atleta> classificacao;
    Context contextClassificacao;
    int diferenca=0;
    int volta;


    public AtletaClassificacaoAdapter(List<Atleta> classificacao, Context contextClassificacao) {
        this.classificacao = classificacao;
        this.contextClassificacao = contextClassificacao;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemLista = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.adapter_classificacao,viewGroup,false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


        Atleta atletaModel = classificacao.get(i);



        if ((!(atletaModel.getNome() == null))){
            myViewHolder.nomeTabela.setText(atletaModel.getNome().substring(1,atletaModel.getNome().length()-1));
            }

        if ((!(atletaModel.getVoltasDaCategoria() == null))) {
            myViewHolder.progressTabela.setMax(Integer.parseInt(atletaModel.getVoltasDaCategoria()));
        }

        if (((atletaModel.getTempoExibicao() != null))){
            myViewHolder.tempoTabela.setText(atletaModel.getTempoExibicao().substring(1,atletaModel.getTempoExibicao().length()-1));



                if (i==0){
                    myViewHolder.diferencaTabela.setText("");
                    diferenca = Integer.parseInt(atletaModel.getTempoTotal());
                    volta = Integer.parseInt(atletaModel.getVoltas());
                        } else {
                            if(Integer.parseInt(atletaModel.getVoltas())==volta) {
                            myViewHolder.diferencaTabela.setText("Diferença +" + Integer.toString((diferenca - Integer.parseInt(atletaModel.getTempoTotal())) * -1));
                            } else myViewHolder.diferencaTabela.setText("");
                            }
            }

        if (((atletaModel.getVoltas() != null))) {
            myViewHolder.voltasTabela.setText(atletaModel.getVoltas() + "/" + atletaModel.getVoltasDaCategoria());
            myViewHolder.progressTabela.setProgress(Integer.parseInt(atletaModel.getVoltas()));
        }

        myViewHolder.posicaoTabela.setText(Integer.toString(i+1)+"º");

        if (((atletaModel.getFotoUrl() != null))) {
            String foto = atletaModel.getFotoUrl().substring(1, atletaModel.getFotoUrl().length() - 1);
            Uri uri = Uri.parse(foto.replace("\\", ""));
            Glide.with(contextClassificacao).load(uri).into(myViewHolder.fotoTabela);
        }

    }

    @Override
    public int getItemCount() {
        return classificacao.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView posicaoTabela;
        TextView nomeTabela;
        TextView tempoTabela;
        TextView diferencaTabela;
        TextView voltasTabela;
        CircleImageView fotoTabela;
        ProgressBar progressTabela;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            posicaoTabela = itemView.findViewById(R.id.textViewColocacaoTabela);
            nomeTabela = itemView.findViewById(R.id.textViewNomeTabela);
            tempoTabela = itemView.findViewById(R.id.textViewTempoTabela);
            diferencaTabela = itemView.findViewById(R.id.textViewDiferencaTabela);
            voltasTabela = itemView.findViewById(R.id.textViewVoltasTabela);
            fotoTabela = itemView.findViewById(R.id.imageViewFotoTabela);
            progressTabela = itemView.findViewById(R.id.progressBarTabela);

        }
    }
}
