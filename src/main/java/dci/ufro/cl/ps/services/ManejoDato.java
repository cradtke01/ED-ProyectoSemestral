package dci.ufro.cl.ps.services;

import dci.ufro.cl.ps.model.ListaRegistros;
import dci.ufro.cl.ps.model.Registro;

import java.util.ArrayList;
import java.util.HashMap;

public class ManejoDato {

    /**
     *
     */
    public static void resumirDatos(String sectorSelec, String fechaSelec, String horaSelec) {
        HashMap<String, ArrayList<Double[]>> resumenRegistros = new HashMap<>();
        ArrayList<Registro> nuevaListaRegistros = new ArrayList<>();
        ListaRegistros.getListaRegistros().clear();
        String datos = FileManager.readFile("src\\main\\resources\\Registros.csv");
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
                    if (counters[i] == 0) {
                        valoresPromedio[i] = null;
                    }
                } catch (Exception ignored) {
                }
            }
            nuevaListaRegistros.add(new Registro(key.split("_")[0], key.split("_")[1], key.split("_")[2], valoresPromedio[0], valoresPromedio[1], valoresPromedio[2], valoresPromedio[3]));
        }
        ListaRegistros.setListaRegistros(nuevaListaRegistros);
    }

    public static void selectSector(String sector) {
        for (int i = ListaRegistros.getListaRegistros().size() - 1; i >= 0; i--) {
            if (ListaRegistros.getListaRegistros().get(i).getSector() != null && ListaRegistros.getListaRegistros().get(i).getSector().equalsIgnoreCase(sector)) {
                continue;
            }
            ListaRegistros.getListaRegistros().remove(i);
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
