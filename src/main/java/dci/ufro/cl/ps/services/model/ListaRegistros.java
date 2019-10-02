package dci.ufro.cl.ps.services.model;

import java.util.ArrayList;

public class ListaRegistros {

    private static ArrayList<Registro> listaRegistros = new ArrayList<>();

    public static ArrayList<Registro> getListaRegistros() {
        return listaRegistros;
    }

    public static void setListaRegistros(ArrayList<Registro> listaRegistros) {
        ListaRegistros.listaRegistros = listaRegistros;
    }
}
