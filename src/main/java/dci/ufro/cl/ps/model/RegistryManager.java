package dci.ufro.cl.ps.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Carlos Radtke
 * @version 1.0
 * @since 1.0
 */
public class RegistryManager {

    /**
     * This method filters (according to the selected date and time) and works the registries to get them ready for display.
     *
     * @param selectedDate date selected by the user.
     * @param selectedTime time selected by the user.
     */
    public static void filterRegistries(String selectedDate, String selectedTime) {

        // Reformated date to match format from source csv.
        selectedDate = reformatDate(selectedDate);

        // Fetches the registries data from a csv file and then separates the individual entries.
        String rawData = PlainFileManager.readFile("src\\main\\resources\\data\\registries.csv");
        String[] registries = rawData.split("\n");

        // registriesByDateAndTime contains as keys all date-time-area combinations that are present in the csv entries.
        // The value associated with each keys is an ArrayList containing the values (as Double[4]'s) of pm10,
        // pm2_5, humidity and temperature of each entry that matches the key's date-time-area combination.
        HashMap<String, ArrayList<Double[]>> registriesByDateAndTime = new HashMap<>();
        for (String registry : registries) {

            String[] registryValues = registry.split(",");
            String date = registryValues[0];
            String time = registryValues[1];
            String area = registryValues[2];

            if (date.equals(selectedDate) && time.equals(selectedTime)) {

                String pm10 = registryValues[3];
                String pm2_5 = registryValues[4];
                String humidity = registryValues[5];
                String temperature = registryValues[6];

                //registryConditionValues contains the values of pm10, pm2_5, humidity and temperature of the current registry.
                Double[] registryConditionValues = new Double[4];
                if (!pm10.isBlank()) {
                    registryConditionValues[0] = Double.valueOf(pm10);
                }
                if (!pm2_5.isBlank()) {
                    registryConditionValues[1] = Double.valueOf(pm2_5);
                }
                if (!humidity.isBlank()) {
                    registryConditionValues[2] = Double.valueOf(humidity);
                }
                if (!temperature.isBlank()) {
                    registryConditionValues[3] = Double.valueOf(temperature);
                }

                if (!registriesByDateAndTime.containsKey(date + "_" + time + "_" + area)) {
                    //arraysToAverage is the ArrayList that contains all registryConditionValues arrays for the same date-time-area key.
                    ArrayList<Double[]> arraysToAverage = new ArrayList<>();
                    arraysToAverage.add(registryConditionValues);
                    registriesByDateAndTime.put(date + "_" + time + "_" + area, arraysToAverage);
                } else {
                    registriesByDateAndTime.get(date + "_" + time + "_" + area).add(registryConditionValues);
                }
            }
        }
        ArrayList<Registry> averagedRegistryList = new ArrayList<>();

        // Finally, the values of pm10, pm2_5, humidity and temperature of the same date-time-area get averaged to get a
        // mean value.
        calculateAverage(registriesByDateAndTime, averagedRegistryList);

        //Sets the registryList for the class "RegistryController" to use.
        RegistryList.setRegistryList(averagedRegistryList);

        // Writes the csv used to color the map.
        writeCSV(averagedRegistryList);
    }

    private static void calculateAverage(HashMap<String, ArrayList<Double[]>> registriesByDateAndTime, ArrayList<Registry> newRegistryList) {
        for (String key : registriesByDateAndTime.keySet()) {
            Double[] averageValues = {0.0, 0.0, 0.0, 0.0};
            int[] counters = {0, 0, 0, 0};
            for (int i = 0; i < registriesByDateAndTime.get(key).size(); i++) {
                for (int j = 0; j < registriesByDateAndTime.get(key).get(i).length; j++) {
                    try {
                        averageValues[j] += registriesByDateAndTime.get(key).get(i)[j];
                        counters[j]++;
                    } catch (Exception ignored) {
                    }
                }
            }
            for (int i = 0; i < averageValues.length; i++) {
                try {
                    averageValues[i] /= counters[i];
                    DecimalFormat df2 = new DecimalFormat("#.##");
                    averageValues[i] = Double.valueOf(df2.format(averageValues[i]));
                    if (counters[i] == 0) {
                        averageValues[i] = null;
                    }
                } catch (Exception ignored) {
                }
            }
            newRegistryList.add(new Registry(key.split("_")[0], key.split("_")[1], key.split("_")[2], averageValues[0], averageValues[1], averageValues[2], averageValues[3]));
        }
    }

    // Reformated date to match format from source csv.
    private static String reformatDate(String selectedDate) {
        try {
            selectedDate = selectedDate.split("-")[2] + "-" + selectedDate.split("-")[1] + "-" + selectedDate.split("-")[0];
        } catch (Exception ignored) {
        }
        return selectedDate;
    }

    // Writes the csv used to color the map.
    public static void writeCSV(ArrayList<Registry> registries) {
        PlainFileManager.writeFile("src\\main\\resources\\static\\averagedRegistries.csv", "fecha,hora,sector,pm10,a,b,c\n");
        for (Registry r : registries) {
            PlainFileManager.writeFile("src\\main\\resources\\static\\averagedRegistries.csv", PlainFileManager.readFile("src\\main\\resources\\static\\averagedRegistries.csv") + r.getFecha() + "," + r.getHora() + "," + r.getSector() + "," + r.getPm10() + "," + r.getPm25() + "," + r.getHumedad() + "," + r.getTemperatura() + "\n");
        }
    }
}
