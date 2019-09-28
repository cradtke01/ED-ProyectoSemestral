package dci.ufro.cl.ps.services;

import dci.ufro.cl.ps.model.ListaRegistros;
import dci.ufro.cl.ps.model.Registro;

public class ManejoDato {

    /**
     *
     */
    public static void leerDatos() {
        ListaRegistros.getListaRegistros().clear();
        String datos = FileManager.readFile("src\\main\\resources\\RegistrosAbril.csv");
        String[] registros = datos.split("\n");
        for (int i = 1; i < registros.length; i++) {
            String[] valores = registros[i].split(",");
            String fecha = valores[0];
            String hora = valores[1];
            String sector = valores[2];
            Double pm10 = null;
            if (!valores[3].isBlank()) {
                pm10 = Double.valueOf(valores[3]);
            }
            Double pm25 = null;
            if (!valores[4].isBlank()) {
                pm25 = Double.valueOf(valores[4]);
            }
            Double humedad = null;
            if (!valores[5].isBlank()) {
                humedad = Double.valueOf(valores[5]);
            }
            Double temperatura = null;
            if (!valores[6].isBlank()) {
                temperatura = Double.valueOf(valores[6]);
            }
            Registro registro = new Registro(fecha, hora, sector, pm10, pm25, humedad, temperatura);
            ListaRegistros.getListaRegistros().add(registro);
        }
    }

    public static void selectSector(String sector) {
        for (int i = ListaRegistros.getListaRegistros().size() - 1; i >= 0; i--) {
            if (ListaRegistros.getListaRegistros().get(i).getSector() == null || !ListaRegistros.getListaRegistros().get(i).getSector().equalsIgnoreCase(sector)) {
                ListaRegistros.getListaRegistros().remove(i);
            }
        }
    }

    public static void selectPm10(double pm10Piso, double pm10Techo) {
        for (int i = ListaRegistros.getListaRegistros().size() - 1; i >= 0; i--) {
            if (ListaRegistros.getListaRegistros().get(i).getPm10() == null || ListaRegistros.getListaRegistros().get(i).getPm10() < pm10Piso || ListaRegistros.getListaRegistros().get(i).getPm10() > pm10Techo) {
                ListaRegistros.getListaRegistros().remove(i);
            }
        }
    }

    public static void selectPm25(double pm25Piso, double pm25Techo) {
        for (int i = ListaRegistros.getListaRegistros().size() - 1; i >= 0; i--) {
            if (ListaRegistros.getListaRegistros().get(i).getPm25() == null || ListaRegistros.getListaRegistros().get(i).getPm25() < pm25Piso || ListaRegistros.getListaRegistros().get(i).getPm25() > pm25Techo) {
                ListaRegistros.getListaRegistros().remove(i);
            }
        }
    }

    public static void selectHumedad(double humedadPiso, double humedadTecho) {
        for (int i = ListaRegistros.getListaRegistros().size() - 1; i >= 0; i--) {
            if (ListaRegistros.getListaRegistros().get(i).getHumedad() == null || ListaRegistros.getListaRegistros().get(i).getHumedad() < humedadPiso || ListaRegistros.getListaRegistros().get(i).getHumedad() > humedadTecho) {
                ListaRegistros.getListaRegistros().remove(i);
            }
        }
    }

    public static void selectTemperatura(double temperaturaPiso, double temperaturaTecho) {
        for (int i = ListaRegistros.getListaRegistros().size() - 1; i >= 0; i--) {
            if (ListaRegistros.getListaRegistros().get(i).getTemperatura() == null || ListaRegistros.getListaRegistros().get(i).getTemperatura() < temperaturaPiso || ListaRegistros.getListaRegistros().get(i).getTemperatura() > temperaturaTecho) {
                ListaRegistros.getListaRegistros().remove(i);
            }
        }
    }

}
