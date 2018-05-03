package com.example.manfredi.prova.Dati;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * Classe che simula la sorgente dei dati
 */
public class DataTavoli {

    private static final String TAG = "Tavolo" ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    // Lista locale per simulare una ipotetica sorgente dati
    private Hashtable<String, Tavolo> elencoTavoli;

    // Unica instanza
    private static DataTavoli instance = null;

    // Costruttore privato
    private DataTavoli() {
        elencoTavoli = new Hashtable<>();
        Tavoliesempio();
    }

    /**
     * Ottiene il riferimento alla sorgente dati
     *
     * @return restituisce l'instanza corrente
     */
    public static DataTavoli getInstance() {
        if (instance == null)
            instance = new DataTavoli();
        return instance;
    }


    public void addTavolo(Tavolo tavolo) {
        elencoTavoli.put(tavolo.getNumTav(), tavolo);
    }

    public void deleteTavolo(String numTav) {
        elencoTavoli.remove(numTav);
    }

    public List<Tavolo> getListaTavoli(boolean Bottone) {

        ArrayList<Tavolo> risultato = new ArrayList<Tavolo>();
        if(Bottone)
            for (Map.Entry<String, Tavolo> elemento: elencoTavoli.entrySet()) {
                if(elemento.getValue().isStatoTav())
                    risultato.add(elemento.getValue());
            }
        else {
            for (Map.Entry<String, Tavolo> elemento : elencoTavoli.entrySet()) {
                risultato.add(elemento.getValue());
            }
        }

        return risultato;
    }


    private void Tavoliesempio() {

        myRef.child("Tavolo").child("1").child("Tavolo n°").setValue("1");
        myRef.child("Tavolo").child("1").child("Numero posti").setValue(4);
        myRef.child("Tavolo").child("1").child("Libero").setValue("si");
        myRef.child("Tavolo").child("2").child("Tavolo n°").setValue("2");
        myRef.child("Tavolo").child("2").child("Numero posti").setValue(4);
        myRef.child("Tavolo").child("2").child("Libero").setValue("si");
        myRef.child("Tavolo").child("3").child("Tavolo n°").setValue("3");
        myRef.child("Tavolo").child("3").child("Numero posti").setValue(2);
        myRef.child("Tavolo").child("3").child("Libero").setValue("si");
        myRef.child("Tavolo").child("4").child("Tavolo n°").setValue("4");
        myRef.child("Tavolo").child("4").child("Numero posti").setValue(2);
        myRef.child("Tavolo").child("4").child("Libero").setValue("no");
        myRef.child("Tavolo").child("5").child("Tavolo n°").setValue("5");
        myRef.child("Tavolo").child("5").child("Numero posti").setValue(6);
        myRef.child("Tavolo").child("5").child("Libero").setValue("no");
        myRef.child("Tavolo").child("6").child("Tavolo n°").setValue("6");
        myRef.child("Tavolo").child("6").child("Numero posti").setValue(4);
        myRef.child("Tavolo").child("6").child("Libero").setValue("si");
        myRef.child("Tavolo").child("7").child("Tavolo n°").setValue("7");
        myRef.child("Tavolo").child("7").child("Numero posti").setValue(8);
        myRef.child("Tavolo").child("7").child("Libero").setValue("si");
        myRef.child("Tavolo").child("8").child("Tavolo n°").setValue("8");
        myRef.child("Tavolo").child("8").child("Numero posti").setValue(2);
        myRef.child("Tavolo").child("8").child("Libero").setValue("no");
        elencoTavoli.put("1", new Tavolo("1", 4, true));
        elencoTavoli.put("2", new Tavolo("2", 4, true));
        elencoTavoli.put("3", new Tavolo("3", 2, true));
        elencoTavoli.put("4", new Tavolo("4", 2, false));
        elencoTavoli.put("5", new Tavolo("5", 6, false));
        elencoTavoli.put("6", new Tavolo("6", 4, true));
        elencoTavoli.put("7", new Tavolo("7", 8, true));
        elencoTavoli.put("8", new Tavolo("8", 2, false));
    }


}