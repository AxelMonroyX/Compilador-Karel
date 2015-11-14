package com.axelmonroyx.GenerarCodigoObjeto;

/**
 * Created by axelmonroyx on 13/11/15.
 */
public class Lista_polish {

    //Atributos de la lista
    nodo_polish Pri;
    nodo_polish Ult;
    String Nom;

    //Constructor de la lista
    public Lista_polish(String n) {
        Pri = Ult = null;
        Nom = n;
    }

    //Nombre de la lista
    public Lista_polish() {
        this("Lista_polish");
    }

    //Constructor
    public boolean Lista_polishVacia() {//EN CASO DE  QUE LA LISTA ESTE VACIA
        return Pri == null;
    }


    //Metodo para insertar por la parte posterior de la lista
    public void Insertar_Nodo_Final(String lexema, int token, String tipo) {
        if (Lista_polishVacia()) {
            Pri = Ult = new nodo_polish(lexema, token, tipo);
        } else {
            Ult = Ult.sig = new nodo_polish(lexema, token, tipo);
        }
    }

    //Metodo para insertar por la parte posterior de la lista
    public void Insertar_Brinco(String lexema, int token, String tipo, String salto) {
        if (Lista_polishVacia()) {
            Pri = Ult = new nodo_polish(lexema, token, tipo, salto);
        } else {
            Ult = Ult.sig = new nodo_polish(lexema, token, tipo);
        }
    }


    //Metodo para eliminar el frente de la lista
    public void Eliminar_Nodo_Inicio() {
        if (Lista_polishVacia()) {
            Pri = Pri.sig;
        } else if (Pri.equals(Ult)) {
            Pri = Ult = null;
        } else {
            Pri = Pri.sig;
        }
    }

    //Metodo para eliminar el posterior de la lista
    public void Eliminar_Nodo_Final() {
        if (Lista_polishVacia()) {
            Pri = Pri.sig;
        } else if (Pri.equals(null)) {
            Ult = null;
        } else {
            Pri = Pri.sig;
        }
    }


    public void Insertar_Nodo_Final(String lexema, int token, String tipo, boolean valorBolean) {
        String valor;
        if (valorBolean) {
            valor = "true";
        } else {
            valor = "false";
        }
        if (Lista_polishVacia()) {
            Pri = Ult = new nodo_polish(lexema, token, tipo, valor);
        } else {
            Ult = Ult.sig = new nodo_polish(lexema, token, tipo, valor);
        }
    }
}