package com.example.manfredi.prova;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toolbar;


import com.example.manfredi.prova.Dati.DataTavoli;
import com.example.manfredi.prova.Dati.Tavolo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    // Riferimenti alle view
    private ListView vListaTavoli;
    private Switch vSwitchLibero;
    private TextView vTav;
    private Toolbar toolbar;
    private FloatingActionButton vBottone;
    // Adapter e data source
    private TavoliAdapter adapter;
    private DataTavoli dataTavoli;

    // Chiave per il passaggio parametri alla activity di dettaglio
    private final String EXTRA_TAVOLO = "tavolo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vListaTavoli = findViewById(R.id.ListTavoli);
        vSwitchLibero = (Switch) findViewById(R.id.switch1);
        vTav = findViewById(R.id.textTav);
        toolbar = findViewById(R.id.toolbar1);
        vBottone = findViewById(R.id.floatingActionButton);
        vSwitchLibero.setChecked(false);

        dataTavoli = DataTavoli.getInstance();
        dataTavoli.setData();

        // Creo l'adapter

        adapter = new TavoliAdapter(this, dataTavoli.getListaTavoli(false));

        // Associo l'adapter alla listview
        vListaTavoli.setAdapter(adapter);

        vSwitchLibero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vSwitchLibero.isChecked()){
                    adapter.setElencoTavoli(dataTavoli.getListaTavoli(true));
                }
                else {
                    adapter.setElencoTavoli(dataTavoli.getListaTavoli(false));
                }
            }
        });
        // Imposto il listner per il click sulla listview
       vListaTavoli.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // E' stato selezionato una riga della lista: devo visualizzare i dettagli
                // sulla nuova activity

                // Ottengo dall'adapter lo studente da visualizzare
                Tavolo tavolo = (Tavolo) adapter.getItem(i);

                // Creo l'Intent per passare alla activity con il dettaglio
                Intent intent = new Intent(view.getContext(), MenuTavolo.class);

                intent.putExtra(EXTRA_TAVOLO, tavolo);
                startActivity(intent);
            }
        });
    vBottone.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dataTavoli.addTavolo(new Tavolo(Integer.toString(dataTavoli.getSize()), 4, true));
            adapter.setElencoTavoli(dataTavoli.getListaTavoli(true));

        }
    });
    }
}
