package com.example.javi.blogreader_javiermartinezlizama;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EntradaAdapter extends BaseAdapter{

    ArrayList<EntradaBlog> entradas;
    Context context;

    public EntradaAdapter(Context con, ArrayList<EntradaBlog> ent){
        this.entradas = ent;
        this.context = con;
    }

    @Override
    public int getCount() {
        return entradas.size();
    }

    @Override
    public Object getItem(int position) {
        return entradas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder;
        if(convertView == null){
            vHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_entrada, null);
            vHolder.tvEntradaTitulo = (TextView)convertView.findViewById(R.id.tv_entrada_titulo);
            vHolder.tvEntradaAutor= (TextView)convertView.findViewById(R.id.tv_entrada_autor);
            convertView.setTag(vHolder);

        } else {
            vHolder = (ViewHolder)convertView.getTag();
        }


        vHolder.tvEntradaTitulo.setText(entradas.get(position).getTitulo());
        vHolder.tvEntradaAutor.setText(entradas.get(position).getAutor());
        return convertView;
    }

    public class ViewHolder{
        TextView tvEntradaTitulo, tvEntradaAutor;
    }
}
