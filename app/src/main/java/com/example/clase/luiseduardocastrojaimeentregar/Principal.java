package com.example.clase.luiseduardocastrojaimeentregar;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.clase.luiseduardocastrojaimeentregar.adaptadores.Adaptador;

import java.util.List;

public class Principal extends AppCompatActivity {
      ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        iniciar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*-------------------------------------------------*/
    private List<Contacto> personas;
    private static Adaptador cl;

    /*metodo inciar*/
    int marca;
    public void iniciar(){
        ListView lv= (ListView) findViewById(R.id.lvMostrar);
        ListaContactos listaContactos=new ListaContactos(this);
        personas=listaContactos.getContactos();

        for (Contacto aux:personas)
            aux.setNumeros(listaContactos.getNumeros(this, aux.getId()));

        cl = new Adaptador(this, R.layout.informacion,personas);
        lv.setAdapter(cl);
        registerForContextMenu(lv);
         ListaContactos.ordenar();
       /*eventos list*/
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override //Cuando pulsas con el boton aparece el texto de abajo
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // se queda fijo
                marca = position;
                Toast.makeText(getApplicationContext(), "Manten pulsado para Opciones", Toast.LENGTH_LONG).show();
            }
        });


    }
    /*metodo para mostrar el menu de opciones cuando dejamos pulsado*/
    public void  onCreateContextMenu(ContextMenu menu, View v,
                                     ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("Opciones");
        menu.add(0, v.getId(), 0, "Editar");//opciones del menu
        menu.add(0, v.getId(), 0, "Borrar");
        menu.add(0, v.getId(), 0, "Agregar");
        menu.add(0, v.getId(), 0, "LLamar");
    }
    /*metodo para cojer los datos seleccionados en el menu contextual */
    public  boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                                                    item.getMenuInfo();
        List<String> number = personas.get(info.position).getNumeros();
        if(item.getTitle()=="LLamar"){
            String tel = ListaContactos.llamar(info.position);
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +tel)));
            return true;
        }else if(item.getTitle()=="Borrar"){
            personas.remove(info.position);//borramos la persona selecionada
            cl.notifyDataSetChanged(); //reseteamos la lista
        }else if(item.getTitle()=="Editar"){
            Intent i = new Intent(this, Editar.class);//metemos la clase
            Bundle a=new Bundle();//para pasar informacion usamos bundle
            a.putInt("id", info.position);//primer valor nombre de referencia para la
            //siguiete ventana segundo valor parametro que se pasa
            i.putExtras(a);
            startActivity(i); //entramos
        }else {
            if (item.getTitle() == "Agregar") {
                Intent a = new Intent(this,Agregar.class);
                startActivity(a);
            }
        }
        return true;
    }
    /*resetear la lista para los cambios*/
    public static void resetear(){
         ListaContactos.ordenar();
        cl.notifyDataSetChanged();
    }
    /*metodo de la imagen cuando presionamos*/
    public void foto(View v) {
        cl.imgTelefono(v);
//        if (marca == 0) {
//            Toast.makeText(getApplicationContext(), "seleciona antes el Contacto", Toast.LENGTH_LONG).show();
//        } else {
//            marca =0;
//            Intent i = new Intent(this, MostarTelefonos.class);
//            Bundle a = new Bundle();
//             a.putInt("id", marca);
//            i.putExtras(a);
//            startActivity(i); //
//        }
    }


}
