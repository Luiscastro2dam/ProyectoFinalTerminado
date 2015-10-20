package com.example.clase.luiseduardocastrojaimeentregar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Clase on 18/10/2015.
 */
public class Editar extends AppCompatActivity {
    private TextView tv1;
    private TextView tv2;
    private EditText et1;
    private EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones);
        iniciar();
    }

    public void iniciar(){
        tv1 = (TextView) findViewById(R.id.tv1);//conexiones
        tv2 = (TextView) findViewById(R.id.tv2);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
         /*obtener los datos*/
        Intent i=getIntent();
        Bundle a=i.getExtras();
        int id=a.getInt("id");//lo recojemos y metemos en id
        /*buscamos el contacto en nuestro array*/
        Contacto nuevo;
        nuevo = ListaContactos.devolverContacto(id);
        et1.setText(nuevo.getNombre().toString());//mostar datos
        et2.setText(nuevo.getNumeros().toString());//mostar datos
    }
    public void agregar(View v){
        String nombre;
        String telefono;
        ArrayList<String> numeros = new ArrayList<>();
        /*introducimos los datos del texto*/
        nombre = et1.getText().toString();
        telefono = et2.getText().toString();
         /*obtener los datos*/
        Intent i=getIntent();
        Bundle a=i.getExtras();
        int id=a.getInt("id");//lo recojemos y metemos en id

        numeros.add(telefono);//metemos el telefono en el arrayList
        int id2=id;
        ListaContactos.borrar(id);
        ListaContactos.agregarContactos(new Contacto((long) id2, nombre, numeros));

        Principal.resetear();//para que lo muestre despuedes de añadirlo
        finish();//cerramos
    }
    /*evento del boton salir*/
    public void  salir(View v){
        finish(); //cierra la ventana
    }
}


