package module3;

//Java utilities libraries
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Processing library
import processing.core.PApplet;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

//Parsing library
import parsing.ParseFeed;

/**
 * EarthquakeCityMap An application with an interactive map displaying
 * earthquake data. Author: UC San Diego Intermediate Software Development MOOC
 * team
 * 
 * @author Petar Stoyanov Date: July 17, 2015
 */
public class EarthquakeCityMap extends PApplet {

	// You can ignore this. It's to keep eclipse from generating a warning.
	private static final long serialVersionUID = 1L;

	// IF YOU ARE WORKING OFFLINE, change the value of this variable to true
	private static final boolean offline = false;

	// Less than this threshold is a light earthquake
	public static final float THRESHOLD_MODERATE = 5;
	// Less than this threshold is a minor earthquake
	public static final float THRESHOLD_LIGHT = 4;

	/**
	 * This is where to find the local tiles, for working without an Internet
	 * connection
	 */
	public static String mbTilesString = "blankLight-1-3.mbtiles";

	// The map
	private UnfoldingMap map;

	// feed with magnitude 2.5+ Earthquakes
	private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

	public void setup() {
		size(950, 600, OPENGL);

		if (offline) {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new MBTilesMapProvider(mbTilesString));
			earthquakesURL = "2.5_week.atom"; // Same feed, saved Aug 7, 2015,
												// for working offline
		} else {
			map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
			// IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
			// earthquakesURL = "2.5_week.atom";
		}

		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);

		// The List you will populate with new SimplePointMarkers
		List<Marker> markers = new ArrayList<Marker>();

		// Use provided parser to collect properties for each earthquake
		// PointFeatures have a getLocation method
		List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);

		if (earthquakes.size() > 0) {
			for (PointFeature f : earthquakes) {
				markers.add(createMarker(f));
			}

		}

		map.addMarkers(markers);
	}

	// A suggested helper method that takes in an earthquake feature and
	// returns a SimplePointMarker for that earthquake
	// TODO: Implement this method and call it from setUp, if it helps
	private SimplePointMarker createMarker(PointFeature feature) {
		
		SimplePointMarker newEarthquake = new SimplePointMarker(feature.getLocation());
		
		Object magObj = feature.getProperty("magnitude");
		float magnitude = Float.parseFloat(magObj.toString());
		
		if(magnitude <= THRESHOLD_LIGHT){
			newEarthquake.setRadius((float)4.0);
			newEarthquake.setColor(color(0,0,255));
		}else if(magnitude > THRESHOLD_LIGHT && magnitude < THRESHOLD_MODERATE){
			newEarthquake.setRadius((float)7.0);
			newEarthquake.setColor(color(255, 255, 0));
		}else if(magnitude >= THRESHOLD_MODERATE){
			newEarthquake.setRadius((float)10.0);
			newEarthquake.setColor(color(255, 0, 0));
		}
		
		
		
		return newEarthquake;
	}

	public void draw() {
		background(10);
		map.draw();
		addKey();
	}

	// helper method to draw key in GUI
	private void addKey() {
		fill(255,255,255);
		
		float x = (float)20.0;
		float y = (float)50.0;
		float w = (float)160.0;
		float h = (float)200.0;
		float r = (float)7.0;
		
		rect(x,y,w,h,r);
		
		fill(50,50,50);
		
		String title = "Earthquake Key";
		x = (float)35.0;
		y = (float)65.0;

		text(title, x, y);
		
		title = "5+ Magnitude";
		float x1 = (float)80;
		float y1 = (float)100;
		
		text(title, x1, y1);
		
		title = "4+ Magnitude";
		float x2 = (float)80;
		float y2 = (float)140;
		
		text(title, x2, y2);
		
		title = "Below 4";
		float x3 = (float)80;
		float y3 = (float)180;
		
		text(title, x3, y3);
		
		fill(255,0,0);
		ellipse(60, 95, 10, 10);
		fill(255,255,0);
		ellipse(60, 135, 7, 7);
		fill(0,0,255);
		ellipse(60, 175, 4, 4);
		
	}

}
