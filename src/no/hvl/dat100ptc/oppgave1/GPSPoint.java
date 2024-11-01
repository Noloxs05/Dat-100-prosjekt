package no.hvl.dat100ptc.oppgave1;

public class GPSPoint {

    private int time;
    private double latitude;
    private double longitude;
    private double elevation;


    public GPSPoint(int time, double latitude, double longitude, double elevation) {
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
    }

    
    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

  
    public String toString() {
        String str = time + " (" + latitude + "," + longitude + ") " + elevation + "\n";
        return str;
    }

    
    public static void main(String[] args) {
        
        GPSPoint point = new GPSPoint(1, 2.0, 3.0, 5.0);
        System.out.println(point.toString());
        point.setTime(2);
        System.out.println("tid="+ point.getTime());
    }
}
