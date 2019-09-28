package dci.ufro.cl.ps.model;

import java.util.Date;

public class Registro {

    private Date fecha;
    private double pm10;
    private double pm25;
    private double humedad;
    private double temperatura;

    public Registro(Date fecha, double pm10, double pm25, double humedad, double temperatura) {
        this.fecha = fecha;
        this.pm10 = pm10;
        this.pm25 = pm25;
        this.humedad = humedad;
        this.temperatura = temperatura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getPm10() {
        return pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public double getPm25() {
        return pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getHumedad() {
        return humedad;
    }

    public void setHumedad(double humedad) {
        this.humedad = humedad;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "fecha=" + fecha +
                ", pm10=" + pm10 +
                ", pm25=" + pm25 +
                ", humedad=" + humedad +
                ", temperatura=" + temperatura +
                '}';
    }
}
