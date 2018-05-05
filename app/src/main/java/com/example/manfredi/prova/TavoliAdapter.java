package com.example.manfredi.prova;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


import com.example.manfredi.prova.Dati.Tavolo;


import java.util.List;



/**
 * Adapter per l'interfacciamento tra la listview degli studenti e la fonte dati
 */
public class TavoliAdapter extends BaseAdapter {

    private Context context;
    private List<Tavolo> elencoTavoli;
    private CheckBox Occupato;


    public TavoliAdapter(Context context, List<Tavolo> elencoTavoli) {
        this.context = context;
        this.elencoTavoli = elencoTavoli;
    }

    // Invocato per ottenere il numero di elementi nella lista
    @Override
    public int getCount() {
        return elencoTavoli.size();
    }

    // Invocato per ottenere l'iesimo elemento
    @Override
    public Object getItem(int i) {
        return elencoTavoli.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // Invocato per ottenere la view della riga da visualizzare
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // Nel caso il layout non sia stato iniettato, provvedo
        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.elemento_tavolo, null);

        // Ottengo gli ID correnti
        TextView vNumTav = view.findViewById(R.id.textNum);
        TextView vNumPosto = view.findViewById(R.id.textNumPosto);
        CheckBox vOccupato = view.findViewById(R.id.checkOccupato);

        // Imposto i valori da visualizzare
        Tavolo t = elencoTavoli.get(i);
        vNumTav.setText(t.getNumTav());
        vNumPosto.setText(Integer.toString(t.getNumPosti()));
        vOccupato.setChecked(!t.isStatoTav());

        // Restituisco la view alla lista
        return view;
    }


    public void setElencoTavoli(List<Tavolo> elencoTavoli) {
        this.elencoTavoli = elencoTavoli;
        notifyDataSetChanged();
    }

}