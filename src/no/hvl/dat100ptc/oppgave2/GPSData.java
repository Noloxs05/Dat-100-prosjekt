package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int n) {

		gpspoints=new GPSpoint[n];
		antall=0;
		
		
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {
		if(antall< gpspoints.length) {
			gpspoints[antall]=gpspoint;
			
			antall++;
			return true;
		}else {
			return false;
		}
		
	
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {

		int timeInSeconds = GPSDataConverter.toSeconds(time);
		double lat = Double.parseDouble(latitude);
		double lon = Double.parseDouble(longitude);
		double elev = Double.parseDouble(elevation);
		
		GPSPoint gpspoint = new GPSPoint(timeInSeconds,lat,lon,elev);

		return insertGPS(gpspoint);
	}

	public void print() {
		System.out.println("===== GPS Data - START =====");
		
		for(int i =0; i< antall; i++) {
			System.out.println(gpspoints[i].toString());
		}
		System.out.println("===== GPS Data - SLUTT =====");
	}
}
