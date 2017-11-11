package pack.tunisiatouristicguide;

import java.io.IOException;

import pack.tunisiatouristicguide.R;
import pack.wikitude.location.ILocationProvider;
import pack.wikitude.location.LocationProvider;
import pack.wikitude.utils.Constants;

import android.app.Activity;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.ArchitectView.ArchitectConfig;
import com.wikitude.architect.SensorAccuracyChangeListener;

public class BasicArchitectActivity extends Activity {
	
	
	public static final String EXTRAS_KEY_ACTIVITY_TITLE_STRING = "activityTitle";
	public static final String EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL = "activityArchitectWorldUrl";

	/**
	 * holds the AR-view
	 */
	protected ArchitectView					architectView;
	
	
	/**
	 * sensor accuracy listener in case you want to display calibration hints
	 */
	protected SensorAccuracyChangeListener	sensorAccuracyListener;
	
	/**
	 * last known location of the user, used internally for content-loading after user location was fetched
	 */
	protected Location lastKnownLocaton;

	/**
	 * sample location strategy
	 */
	protected ILocationProvider				locationProvider;
	
	/* 
	static {
		System.loadLibrary("architect");
	}
	*/ 
	
	
	/**
	 * location listener receives location updates and must forward them to the architectView
	 */
	protected LocationListener locationListener;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate( final Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );

		
		
		Log.i("LA CLASSE ", "BasiArchitectACtivity");
		
		
		/* pressing volume up/down should cause music volume changes */
		this.setVolumeControlStream( AudioManager.STREAM_MUSIC );

		/* set samples content view */
		this.setContentView( R.layout.sample_cam );
		
		this.setTitle(this.getActivityTitle());

		/* set AR-view for life-cycle notifications etc. */
		this.architectView = (ArchitectView)this.findViewById( R.id.architectView );

		/* pass SDK key if you have one, this one is only valid for this package identifier and must not be used somewhere else */
		final ArchitectConfig config = new ArchitectConfig( Constants.WIKITUDE_SDK_KEY );

		/* first mandatory life-cycle notification */
		this.architectView.onCreate( config );

		this.sensorAccuracyListener = new SensorAccuracyChangeListener() {
			@Override
			public void onCompassAccuracyChanged( int accuracy ) {
				/* UNRELIABLE = 0, LOW = 1, MEDIUM = 2, Height = 3 */
				if ( accuracy < SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM && BasicArchitectActivity.this != null && !BasicArchitectActivity.this.isFinishing() ) {
					Toast.makeText( BasicArchitectActivity.this, R.string.compass_accuracy_low, Toast.LENGTH_LONG ).show();
				}
			}
		};

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
					BasicArchitectActivity.this.lastKnownLocaton = location;
				if ( BasicArchitectActivity.this.architectView != null ) {
					if ( location.hasAltitude() ) {
						BasicArchitectActivity.this.architectView.setLocation( location.getLatitude(), location.getLongitude(), location.getAltitude(), location.hasAccuracy() ? location.getAccuracy() : 1000 );
					} else {
						BasicArchitectActivity.this.architectView.setLocation( location.getLatitude(), location.getLongitude(), location.hasAccuracy() ? location.getAccuracy() : 1000 );
					}
				}
				}
			}
		};
		
		this.architectView.registerSensorAccuracyChangeListener( this.sensorAccuracyListener );
		this.locationProvider = new LocationProvider( this, this.locationListener );
	}

	@Override
	protected void onResume() {
		super.onResume();
		if ( this.architectView != null ) {
			this.architectView.onResume();
		}

		if ( this.locationProvider != null ) {
			this.locationProvider.onResume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if ( this.architectView != null ) {
			this.architectView.onPause();
		}
		if ( this.locationProvider != null ) {
			this.locationProvider.onPause();
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if ( this.architectView != null ) {
			if ( this.sensorAccuracyListener != null ) {
				this.architectView.unregisterSensorAccuracyChangeListener( this.sensorAccuracyListener );
			}
			this.architectView.onDestroy();
		}
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		if ( this.architectView != null ) {
			this.architectView.onLowMemory();
		}
	}


	@Override
	protected void onPostCreate( final Bundle savedInstanceState ) {
		super.onPostCreate( savedInstanceState );
		if ( this.architectView != null ) {
			this.architectView.onPostCreate();
		}

		try {
			this.architectView.load( getARchitectWorldPath() );
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	

	/**
	 * path to the architect-file (AR-Experience HTML) to launch
	 * @return
	 */
	public String getARchitectWorldPath() {
		return getIntent().getExtras().getString(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL);
	}
	
	public String getActivityTitle() {
		return (getIntent().getExtras()!=null && getIntent().getExtras().get(EXTRAS_KEY_ACTIVITY_TITLE_STRING)!=null) ? getIntent().getExtras().getString(EXTRAS_KEY_ACTIVITY_TITLE_STRING) : "Test-World";
	}

	

}