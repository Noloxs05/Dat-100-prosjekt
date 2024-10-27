package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSUtils {

	private static final int R = 6371000; 
	private static final int TEXTWIDTH = 10;

	
	public static double findMax(double[] da) {
		double max = da[0];
		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}
		return max;
	}

	
	public static double findMin(double[] da) {
		double min = da[0];
		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}
		return min;
	}

	
	public static double[] getLatitudes(GPSPoint[] gpspoints) {
		double[] latitudes = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			latitudes[i] = gpspoints[i].getLatitude();
		}
		return latitudes;
	}

	
	public static double[] getLongitudes(GPSPoint[] gpspoints) {
		double[] longitudes = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			longitudes[i] = gpspoints[i].getLongitude();
		}
		return longitudes;
	}

	
	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		double latitude1 = Math.toRadians(gpspoint1.getLatitude());
		double longitude1 = Math.toRadians(gpspoint1.getLongitude());
		double latitude2 = Math.toRadians(gpspoint2.getLatitude());
		double longitude2 = Math.toRadians(gpspoint2.getLongitude());

		double deltaphi = latitude2 - latitude1;
		double deltalambda = longitude2 - longitude1;

		double a = compute_a(latitude1, latitude2, deltaphi, deltalambda);
		double c = compute_c(a);

		return R * c;
	}

	
	private static double compute_a(double phi1, double phi2, double deltaphi, double deltalambda) {
		double sinDeltaPhi = Math.sin(deltaphi / 2);
		double sinDeltaLambda = Math.sin(deltalambda / 2);
		return sinDeltaPhi * sinDeltaPhi + Math.cos(phi1) * Math.cos(phi2) * sinDeltaLambda * sinDeltaLambda;
	}

	
	private static double compute_c(double a) {
		return 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	}

	
	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {
		double distance = distance(gpspoint1, gpspoint2);
		int secs = gpspoint2.getTime() - gpspoint1.getTime(); 
		return distance / secs; 
	}

	
	public static String formatTime(int secs) {
		int hours = secs / 3600;
		int minutes = (secs % 3600) / 60;
		int seconds = secs % 60;

		return String.format("%10s", String.format("%02d:%02d:%02d", hours, minutes, seconds));
	}

	
	public static String formatDouble(double d) {
		return String.format("%10.2f", d);
	}
}
