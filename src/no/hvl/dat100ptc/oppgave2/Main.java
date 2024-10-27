package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {
    public static void main(String[] args) {
        
    	GPSPoint gpsPoint1 = new GPSPoint(100, 60.385390, 5.217217, 61.9);
        GPSPoint gpsPoint2 = new GPSPoint(200, 60.385490, 5.217317, 62.1);

        GPSData gpsData = new GPSData(2);

        gpsData.insertGPS(gpsPoint1);
        gpsData.insertGPS(gpsPoint2);

        gpsData.print();
    }


		
	}

