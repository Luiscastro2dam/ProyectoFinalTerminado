package com.example.clase.luiseduardocastrojaimeentregar;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ListaContactos {


  private static List<Contacto> personas;

  public ListaContactos(Context c) {
    personas=getListaContactos(c);
  }
  public List<Contacto> getContactos(){
    return personas;
  }
  public List<String> getNumeros(Context c,long id){
    return getListaTelefonos(c,id);
  }
   /*metodo telefono para llamar*/
  public static String llamar(int pos){
    String a = personas.get(pos).untelefono(0);
    return a;
  }

   /*para borrar*/
  public static void borrar(int posicion){
    personas.remove(posicion);
  }
  /*para agregar nuevo contactos*/
  public static void agregarContactos(Contacto a){
    personas.add(a);
  }
  /*apra agregar*/
  public void setContactoNuevo(Contacto uno){
    personas.add(uno);
  }
  /*cojer el id para nuevo Contactos */
  public static long devolverid(){
    long a =personas.size();
    return a;
  }
  /*devolver un contacto*/
  public static Contacto devolverContacto(int pos){
    return personas.get(pos);
  }
  /*ordenar*/
  public static void ordenar(){
    Collections.sort(personas);
  }
   /*devolver todos los numeros*/
  public static String todosTelefonos(int pos){
    String cadena="";
    for(int i=0;i<personas.size();i++){
      cadena=personas.get(i).untelefono(i)+'\n'+cadena;
    }
    return cadena;
  }

  public static List<Contacto> getListaContactos(Context contexto){
    Uri uri =  ContactsContract.Contacts.CONTENT_URI;
    String proyeccion[] = null;
    String seleccion = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = ? and " +
            ContactsContract.Contacts.HAS_PHONE_NUMBER + "= ?";
    String argumentos[] = new String[]{"1","1"};
    String orden = ContactsContract.Contacts.DISPLAY_NAME + " collate localized asc";
    Cursor cursor = contexto.getContentResolver().query(uri, proyeccion, seleccion, argumentos, orden);
    int indiceId = cursor.getColumnIndex(ContactsContract.Contacts._ID);
    int indiceNombre = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
    List<Contacto> lista = new ArrayList<>();
    Contacto contacto;
    while(cursor.moveToNext()){
      contacto = new Contacto();
      contacto.setId(cursor.getLong(indiceId));
      contacto.setNombre(cursor.getString(indiceNombre));
      lista.add(contacto);
    }
    return lista;
  }
  /*Teléfonos de un contacto*/
  public static List<String> getListaTelefonos(Context contexto, long id){
    Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    String proyeccion[] = null;
    String seleccion = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";
    String argumentos[] = new String[]{id+""};
    String orden = ContactsContract.CommonDataKinds.Phone.NUMBER;
    Cursor cursor = contexto.getContentResolver().query(uri, proyeccion, seleccion, argumentos, orden);
    int indiceNumero = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
    List<String> lista = new ArrayList<>();
    String numero;
    while(cursor.moveToNext()){
      numero = cursor.getString(indiceNumero);
      lista.add(numero);
    }
    return lista;
  }


}
