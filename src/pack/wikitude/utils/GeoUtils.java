package pack.wikitude.utils;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.location.Location;

public class GeoUtils {

	/**
	 * loads poiInformation and returns them as JSONArray. Ensure attributeNames of JSON POIs are well known in JavaScript, so you can parse them easily
	 * @param userLocation the location of the user
	 * @param numberOfPlaces number of places to load (at max)
	 * @return POI information in JSONArray
	 */
	public static JSONArray getPoiInformation(final Location userLocation, final int numberOfPlaces) {
		
		if (userLocation==null) {
			return null;
		}
		
		final JSONArray pois = new JSONArray();
		
		// ensure these attributes are also used in JavaScript when extracting POI data
		final String ATTR_ID = "id";
		final String ATTR_NAME = "name";
		final String ATTR_DESCRIPTION = "description";
		final String ATTR_LATITUDE = "latitude";
		final String ATTR_LONGITUDE = "longitude";
		final String ATTR_ALTITUDE = "altitude";
		
		for (int i=1;i <= numberOfPlaces; i++) {
			HashMap<String, String> poiInformation = new HashMap<String, String>();
			poiInformation.put(ATTR_ID, String.valueOf(i));
			poiInformation.put(ATTR_NAME, "POI#"+i);
			poiInformation.put(ATTR_DESCRIPTION, "This is the description of POI#"+i);
			double[] poiLocationLatLon = getRandomLatLonNearby(userLocation.getLatitude(), userLocation.getLongitude());
			poiInformation.put(ATTR_LATITUDE, String.valueOf(poiLocationLatLon[0]));
			poiInformation.put(ATTR_LONGITUDE, String.valueOf(poiLocationLatLon[1]));
			poiInformation.put(ATTR_ALTITUDE, String.valueOf(100f));
			pois.put(new JSONObject(poiInformation));
		}
		
		return pois;
	}
	
	/**
	 * helper for creation of dummy places.
	 * @param lat center latitude
	 * @param lon center longitude
	 * @return lat/lon values in given position's vicinity
	 */
	private static double[] getRandomLatLonNearby(final double lat, final double lon) {
		return new double[] { lat + Math.random()/5-0.1 , lon + Math.random()/5-0.1};
	}
}
