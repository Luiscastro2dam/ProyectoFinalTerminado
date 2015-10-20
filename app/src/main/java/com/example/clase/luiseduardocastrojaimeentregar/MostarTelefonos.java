package com.example.clase.luiseduardocastrojaimeentregar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Clase on 18/10/2015.
 */
public class MostarTelefonos extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telefonos);//layout telefonos;

        Intent i=getIntent();//recoleccion de datos
        Bundle a=i.getExtras();
        int id=a.getInt("id");
        TextView tv = (TextView)findViewById(R.id.tvTelefonos);
        //tv.setText("hola "+ id);

      //  String cadena = ListaContactos.todosTelefonos(id);
      //  tv.setText(cadena);

    }
}
