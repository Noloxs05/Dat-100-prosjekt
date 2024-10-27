package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import no.hvl.dat100ptc.TODO;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;
	
	private double minlon, minlat, maxlon, maxlat;

	private double xstep, ystep;
	
	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));
		minlat = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		maxlat = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		
		xstep = scale(MAPXSIZE, minlon, maxlon);
		ystep = scale(MAPYSIZE, minlat, maxlat);
		
		showRouteMap(MARGIN + MAPYSIZE);

		replayRoute(MARGIN + MAPYSIZE);
		
		showStatistics();
	}

	public double scale(int maxsize, double minval, double maxval) {

		double step = maxsize / (Math.abs(maxval - minval));

		return step;
	}

	public void showRouteMap(int ybase) {

		// TODO 
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;
		int y=20;
		setColor(0,0,0);
		setFont("Courier",12);
		
		
		    
		  
		  	drawString("Total Time      : " + GPSUtils.formatTime(gpscomputer.totalTime()), 10, y);
		    y += 20;
		    drawString("Total distance  : " + GPSUtils.formatDouble(gpscomputer.totalDistance() / 1000) + " km", 10, y);
		    y += 20;
		    drawString("Total elevation : " + GPSUtils.formatDouble(gpscomputer.totalElevation()) + " m", 10, y);
		    y += 20;
		    drawString("Max speed       : " + GPSUtils.formatDouble(gpscomputer.maxSpeed() * 3.6) + " km/t", 10, y);
		    y += 20;
		    drawString("Average speed   : " + GPSUtils.formatDouble(gpscomputer.averageSpeed() * 3.6) + " km/t", 10, y);
		    y += 20;
		    drawString("Energy          : " + GPSUtils.formatDouble(gpscomputer.totalKcal(WEIGHT)) + " kcal", 10, y);
		}

	

	public void replayRoute(int ybase) {

	
		    setColor(0, 0, 255); // blå farge
		    int x = (int)((gpspoints[0].getLongitude() - minLongitude) * xstep) + MARGIN;
		    int y = ybase - (int)((gpspoints[0].getLatitude() - minLatitude) * ystep);
		    int circle = fillCircle(x, y, 5);

		    for (int i = 1; i < gpspoints.length; i++) {
		        x = (int)((gpspoints[i].getLongitude() - minLongitude) * xstep) + MARGIN;
		        y = ybase - (int)((gpspoints[i].getLatitude() - minLatitude) * ystep);
		        moveCircle(circle, x, y);
		        pause(100);
		    }
		}

	}

}
