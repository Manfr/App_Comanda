package com.example.manfredi.prova;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.manfredi.prova.Dati.Tavolo;


public class MenuTavolo extends AppCompatActivity {

    // Riferimenti alle view
    TextView vNumTav;
    TextView vNumPosti;
    TextView vLibero;


    // Chiave per il passaggio parametri alla activity di dettaglio
    private final String EXTRA_TAVOLO = "tavolo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_tavolo);

        // Imposto i riferimenti alle view
        vNumTav = findViewById(R.id.textNumTav);
        vNumPosti = findViewById(R.id.textNumPosti);



        // Ottengo i dati passati ed eventualmente visualizzo lo studente
        Intent intent = getIntent();
        Tavolo tavolo = (Tavolo) intent.getSerializableExtra(EXTRA_TAVOLO);

        if (tavolo != null) {
            vNumTav.setText(tavolo.getNumTav());
            vNumPosti.setText(tavolo.getNumPosti());
            vLibero.setEnabled(tavolo.isStatoTav());

        }
    }
}