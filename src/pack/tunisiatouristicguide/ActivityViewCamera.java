package pack.tunisiatouristicguide;

import org.json.JSONArray;

import pack.wikitude.BasicArchitectActivity;
import pack.wikitude.location.LocationProvider;
import pack.wikitude.utils.GeoUtils;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;



public class ActivityViewCamera extends BasicArchitectActivity {
	
	protected JSONArray poiData;


	/** Called when the activity is first created. */
	@Override
	public void onCreate( final Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );


		this.locationListener = new LocationListener() {

			@Override
			public void onStatusChanged( String provider, int status, Bundle extras ) {
			}

			@Override
			public void onProviderEnabled( String provider ) {
			}

			@Override
			public void onProviderDisabled( String provider ) {
			}

			@Override
			public void onLocationChanged( final Location location ) {
				if (location!=null) {
					ActivityViewCamera.this.lastKnownLocaton = location;
				if ( ActivityViewCamera.this.architectView != null ) {
					if ( location.hasAltitude() ) {
						ActivityViewCamera.this.architectView.setLocation( location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getAccuracy() );
					} else {
						ActivityViewCamera.this.architectView.setLocation( location.getLatitude(), location.getLongitude(), location.getAccuracy() );
					}
				}
				}
			}
		};
		
		this.architectView.registerSensorAccuracyChangeListener( this.sensorAccuracyListener );
		this.locationProvider = new LocationProvider( this, this.locationListener );
	}


	@Override
	protected void onPostCreate( final Bundle savedInstanceState ) {
		super.onPostCreate( savedInstanceState );
		this.loadData();
		
	}
	
	boolean isLoading = false;
	
	final Runnable loadData = new Runnable() {
		
		
		
		@Override
		public void run() {
			
			isLoading = true;
			
			final int WAIT_FOR_LOCATION_STEP_MS = 2000;
			
			while (ActivityViewCamera.this.lastKnownLocaton==null && !ActivityViewCamera.this.isFinishing()) {
			
				ActivityViewCamera.this.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						//Toast.makeText(Activity_Navigation.this, R.string.location_fetching, Toast.LENGTH_SHORT).show();	
						Toast.makeText( ActivityViewCamera.this, "hhhhh non", Toast.LENGTH_LONG ).show();
						
					}
				});

				try {
					Thread.sleep(WAIT_FOR_LOCATION_STEP_MS);
				} catch (InterruptedException e) {
					break;
				}
			}
			
			if (ActivityViewCamera.this.lastKnownLocaton!=null && !ActivityViewCamera.this.isFinishing()) {
				// TODO: you may replace this dummy implementation and instead load POI information e.g. from your database
				poiData = GeoUtils.getPoiInformation(ActivityViewCamera.this.lastKnownLocaton, 20);
				ActivityViewCamera.this.callJavaScript("World.loadPoisFromJsonData", new String[] { poiData.toString() });
			}
			
			isLoading = false;
		}
	};
	
	
	protected void loadData() {
		if (!isLoading) {
			final Thread t = new Thread(loadData);
			t.start();
		}
	}
	
	/**
	 * call JacaScript in architectView
	 * @param methodName
	 * @param arguments
	 */
	private void callJavaScript(final String methodName, final String[] arguments) {
		final StringBuilder argumentsString = new StringBuilder("");
		for (int i= 0; i<arguments.length; i++) {
			argumentsString.append(arguments[i]);
			if (i<arguments.length-1) {
				argumentsString.append(", ");
			}
		}
		
		if (this.architectView!=null) {
			final String js = ( methodName + "( " + argumentsString.toString() + " );" );
			this.architectView.callJavascript(js);
		}
	}
	
	

}