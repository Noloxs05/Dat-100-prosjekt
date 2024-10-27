package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

public class GPSComputer {
	
	private GPSPoint[] gpspoints;
	public static final double MS = 2.23; 
	private static double WEIGHT = 80.0;  
	
	public GPSComputer(String filename) {
		this(GPSDataFileReader.readGPSFile(filename).getGPSPoints());
	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}
	
	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	public double totalDistance() {
		double distance = 0;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			distance += GPSUtils.distance(gpspoints[i], gpspoints[i + 1]);
		}
		return distance;
	}

	public double totalElevation() {
		double elevation = 0;
		for (int i = 0; i < gpspoints.length - 1; i++) {
			double deltaElevation = gpspoints[i + 1].getElevation() - gpspoints[i].getElevation();
			if (deltaElevation > 0) {
				elevation += deltaElevation;
			}
		}
		return elevation;
	}

	public int totalTime() {
		return gpspoints[gpspoints.length - 1].getTime() - gpspoints[0].getTime();
	}

	public double[] speeds() {
		double[] speeds = new double[gpspoints.length - 1];
		for (int i = 0; i < gpspoints.length - 1; i++) {
			speeds[i] = GPSUtils.speed(gpspoints[i], gpspoints[i + 1]);
		}
		return speeds;
	}
	
	public double maxSpeed() {
		double maxspeed = 0;
		for (double speed : speeds()) {
			if (speed > maxspeed) {
				maxspeed = speed;
			}
		}
		return maxspeed;
	}

	public double averageSpeed() {
		double totalDistanceKm = totalDistance() / 1000; // Distanse i km
		double totalTimeHours = totalTime() / 3600.0; // Tid i timer
		return totalDistanceKm / totalTimeHours;
	}

	public double kcal(double weight, int secs, double speed) {
		double speedmph = speed * MS;
		double met;

		if (speedmph < 10) met = 4.0;
		else if (speedmph < 12) met = 6.0;
		else if (speedmph < 14) met = 8.0;
		else if (speedmph < 16) met = 10.0;
		else if (speedmph < 20) met = 12.0;
		else met = 16.0;

		double hours = secs / 3600.0;
		return met * weight * hours;
	}

	public double totalKcal(double weight) {
		double totalKcal = 0;
		double[] speeds = speeds();
		for (int i = 0; i < speeds.length; i++) {
			int secs = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
			totalKcal += kcal(weight, secs, speeds[i]);
		}
		return totalKcal;
	}

	public void displayStatistics() {
		System.out.println("==============================================");
		System.out.println("Total Time     : " + GPSUtils.formatTime(totalTime()));
		System.out.printf("Total distance : %10.2f km%n", totalDistance() / 1000);
		System.out.printf("Total elevation: %10.2f m%n", totalElevation());
		System.out.printf("Max speed      : %10.2f km/t%n", maxSpeed() * 3.6);
		System.out.printf("Average speed  : %10.2f km/t%n", averageSpeed());
		System.out.printf("Energy         : %10.2f kcal%n", totalKcal(WEIGHT));
		System.out.println("==============================================");
	}
}
