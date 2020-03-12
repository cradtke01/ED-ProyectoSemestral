package dci.ufro.cl.ps.services;

import dci.ufro.cl.ps.model.ListaRegistros;
import dci.ufro.cl.ps.model.Registro;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class RegistroManager {

    /**
     *
     */
    public static void resumirDatos(String fechaSelec, String horaSelec) {
        try {
            fechaSelec = fechaSelec.split("-")[2] + "-" + fechaSelec.split("-")[1] + "-" + fechaSelec.split("-")[0];
        } catch (Exception ignored) {
        }
        ListaRegistros.getListaRegistros().clear();
        HashMap<String, ArrayList<Double[]>> resumenRegistros = new HashMap<>();
        ArrayList<Registro> nuevaListaRegistros = new ArrayList<>();

        String datos = FileManager.readFile("src\\main\\resources\\data\\Registros.csv");
        String[] registros = datos.split("\n");
        for (String registro : registros) {
            String[] valores = registro.split(",");
            if (valores[0].equals(fechaSelec) && valores[1].equals(horaSelec)) {
                String fecha = valores[0];
                String hora = valores[1];
                String sector = valores[2];
                String pm10 = valores[3];
                String pm2_5 = valores[4];
                String humedad = valores[5];
                String temperatura = valores[6];

                Double[] v = new Double[4];
                if (!pm10.isBlank()) {
                    v[0] = Double.valueOf(pm10);
                }
                if (!pm2_5.isBlank()) {
                    v[1] = Double.valueOf(pm2_5);
                }
                if (!humedad.isBlank()) {
                    v[2] = Double.valueOf(humedad);
                }
                if (!temperatura.isBlank()) {
                    v[3] = Double.valueOf(temperatura);
                }
                ArrayList<Double[]> dataToMerge = new ArrayList<>();
                dataToMerge.add(v);
                if (!resumenRegistros.containsKey(fecha + "_" + hora + "_" + sector)) {
                    resumenRegistros.put(fecha + "_" + hora + "_" + sector, dataToMerge);
                } else {
                    resumenRegistros.get(fecha + "_" + hora + "_" + sector).add(v);
                }
            }
        }
        for (String key : resumenRegistros.keySet()) {
            Double[] valoresPromedio = {0.0, 0.0, 0.0, 0.0};
            int[] counters = {0, 0, 0, 0};
            for (int i = 0; i < resumenRegistros.get(key).size(); i++) {
                for (int j = 0; j < resumenRegistros.get(key).get(i).length; j++) {
                    try {
                        valoresPromedio[j] += resumenRegistros.get(key).get(i)[j];
                        counters[j]++;
                    } catch (Exception ignored) {
                    }
                }
            }
            for (int i = 0; i < valoresPromedio.length; i++) {
                try {
                    valoresPromedio[i] /= counters[i];
                    DecimalFormat df2 = new DecimalFormat("#.##");
                    valoresPromedio[i] = Double.valueOf(df2.format(valoresPromedio[i]));
                    if (counters[i] == 0) {
                        valoresPromedio[i] = null;
                    }
                } catch (Exception ignored) {
                }
            }
            nuevaListaRegistros.add(new Registro(key.split("_")[0], key.split("_")[1], key.split("_")[2], valoresPromedio[0], valoresPromedio[1], valoresPromedio[2], valoresPromedio[3]));
        }
        ListaRegistros.setListaRegistros(nuevaListaRegistros);
        csvResumen(nuevaListaRegistros);
    }

    public static void csvResumen(ArrayList<Registro> registros) {
        FileManager.writeFile("src\\main\\resources\\static\\RegistrosR.csv", "fecha,hora,code,pop,a,b,c\n");
        for (Registro r : registros) {
            FileManager.writeFile("src\\main\\resources\\static\\RegistrosR.csv", FileManager.readFile("src\\main\\resources\\static\\RegistrosR.csv")+r.getFecha()+","+r.getHora()+","+r.getSector()+","+r.getPm10()+","+r.getPm25()+","+r.getHumedad()+","+r.getTemperatura()+"\n");
        }
    }
}
