package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSDataConverter {

	
	private static int TIME_STARTINDEX = 11; 
	public static void main(String[] args) {
        
        String time = "2017-08-13T08:52:26.000Z";
        int seconds = toSeconds(time);
        System.out.println("Total tid i sekunder skal være " + seconds);
    }
	
	public static int toSeconds(String timestr) {
		
		int secs;
		int hr, min, sec;
		
		// TODO
		hr = Integer.parseInt(timestr.substring(11,13));
		min = Integer.parseInt(timestr.substring(14,16));
		sec = Integer.parseInt(timestr.substring(17,19));
		
		secs=(hr * 3600) + (min * 60) + sec;
		
		System.out.println("tid i sekunder" + secs);
		return secs;
		
		
	}

	public static GPSPoint convert(String timeStr, String latitudeStr, String longitudeStr, String elevationStr) {

		GPSPoint gpspoint = convert("2017-08-13T08:52:26.000Z","60.385390","5.217217","61.9") ;
		 int timeInSeconds = toSeconds(timeStr);
		    
		    
		    double latitude = Double.parseDouble(latitudeStr);
		    double longitude = Double.parseDouble(longitudeStr);
		    double elevation = Double.parseDouble(elevationStr);
		    
	
		    return new GPSPoint(timeInSeconds, latitude, longitude, elevation);
		}
		
	}
	
	


