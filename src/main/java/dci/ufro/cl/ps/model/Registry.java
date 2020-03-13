package dci.ufro.cl.ps.model;

/**
 * @author Carlos Radtke
 * @version 1.0
 * @since 1.0
 */
public class Registry {

    private String fecha;
    private String hora;
    private String sector;
    private Double pm10;
    private Double pm25;
    private Double humedad;
    private Double temperatura;

    public Registry(String fecha, String hora, String sector, Double pm10, Double pm25, Double humedad, Double temperatura) {
        this.fecha = fecha;
        this.hora = hora;
        this.sector = sector;
        this.pm10 = pm10;
        this.pm25 = pm25;
        this.humedad = humedad;
        this.temperatura = temperatura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }

    public Double getPm25() {
        return pm25;
    }

    public void setPm25(Double pm25) {
        this.pm25 = pm25;
    }

    public Double getHumedad() {
        return humedad;
    }

    public void setHumedad(Double humedad) {
        this.humedad = humedad;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", sector='" + sector + '\'' +
                ", pm10=" + pm10 +
                ", pm25=" + pm25 +
                ", humedad=" + humedad +
                ", temperatura=" + temperatura +
                '}';
    }
}
