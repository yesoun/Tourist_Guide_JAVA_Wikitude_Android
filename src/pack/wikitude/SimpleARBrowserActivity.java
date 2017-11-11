package pack.wikitude;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONArray;
import org.json.JSONException;

import pack.tunisiatouristicguide.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.wikitude.architect.ArchitectUrlListener;
import com.wikitude.architect.ArchitectView;

/**
 * 
 * @author Wikitude
 * @date   JAN 2012
 * 
 * @class SimpleARBrowserActivity
 * 
 *	sample application to show how to use the ARchitect SDK
 * 	loads simple pois via javascript into the ARchitect world and displays them accordingly
 *  displays a bubble with information about the selected poi on the screen and displays a detail page when the bubble is clicked
 *  uses Android's LocationManager to get updates on the user's location
 * 
 *  important is that the methods of the activity lifecycle are forwarded to the ArchitectView
 *  Important methods:  	onPostCreate()
 * 							onResume()
 *							onPause()
 * 							onDestroy()
 * 							onLowMemory()	
 * 
 * 	Please also have a look at the application's Manifest and layout xml-file to see the permissions and requirements 
 * 	an activity using the SDK has to possess. (REF: ARchitect Documentation)		  	  
 */
public class SimpleARBrowserActivity extends Activity implements ArchitectUrlListener, LocationListener{
	
	
	/** sample location, you have to set up location-service your own way... this sample beams you to a fixed position
	 * in case you change these values you also need to adjust samplepois.json to run "SHOWCASE_TEST_JSSERVERREQUEST" properly */ 
	private final static float  TEST_LATITUDE =  0;
	private final static float  TEST_LONGITUDE = 0;
	private final static float 	TEST_ALTITUDE = 0;
	
	private String apiKey = "yessine";
	
	
	private ArchitectView architectView;
	private LocationManager locManager;
	private Location loc;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //let the application be fullscreen
        this.getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );
        
        //check if the device fulfills the SDK'S minimum requirements
        if(!ArchitectView.isDeviceSupported(this))
        {
        	Toast.makeText(this, "deppendance non satisfaites !!", Toast.LENGTH_LONG).show();
        	this.finish();
        	return;
        }
        setContentView(R.layout.main);
        Toast.makeText(this, " Veuillez Patienter ", Toast.LENGTH_LONG).show();
        
        Toast.makeText(this, " Localisation .... ", Toast.LENGTH_LONG).show();
        
        Toast.makeText(this, " Chargement ... ", Toast.LENGTH_LONG).show();
    	
        //set the devices' volume control to music to be able to change the volume of possible soundfiles to play
        this.setVolumeControlStream( AudioManager.STREAM_MUSIC );
        this.architectView = (ArchitectView) this.findViewById(R.id.architectView);
        //onCreate method for setting the license key for the SDK
        architectView.onCreate(apiKey);
        
        //in order to inform the ARchitect framework about the user's location Androids LocationManager is used in this case
        //NOT USED IN THIS EXAMPLE
        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
       locManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, this);

    
    
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
    	super.onPostCreate(savedInstanceState);
    	
    	//IMPORTANT: creates ARchitect core modules
    	if(this.architectView != null)
    		this.architectView.onPostCreate();
    	
    	//register this activity as handler of "architectsdk://" urls
    	this.architectView.registerUrlListener(this);
    	
    	try {
			loadSampleWorld();
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    
	@Override
	protected void onResume() {
		super.onResume();

		this.architectView.onResume();
		this.architectView.setLocation(TEST_LATITUDE, TEST_LONGITUDE, TEST_ALTITUDE,1f);

	}
    @Override
    protected void onPause() {
    	super.onPause();
    	if(this.architectView != null)
    		this.architectView.onPause();
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	
    	if(this.architectView != null)
    		this.architectView.onDestroy();
    }
    
    @Override
    public void onLowMemory() {
    	super.onLowMemory();
    	
    	if(this.architectView != null)
    		this.architectView.onLowMemory();
    }

    /**
     * <p>
     * interface method of {@link ArchitectUrlListener} class
     * called when an url with host "architectsdk://" is discovered
     * 
     * can be parsed and allows to react to events triggered in the ARchitect World
     * </p>
     */
	@Override
	public boolean urlWasInvoked(String url) {
		//parsing the retrieved url string
		List<NameValuePair> queryParams = URLEncodedUtils.parse(URI.create(url), "UTF-8");
		
		String id = "";
		String description = "";
		String name = "";
		// getting the values of the contained GET-parameters
		for(NameValuePair pair : queryParams)
		{
			if(pair.getName().equals("id"))
			{
				id = pair.getValue();
			}
			if(pair.getName().equals("description"))
			{
				description = pair.getValue();
			}
			if(pair.getName().equals("name"))
			{
				name = pair.getValue();
			}
		}
		
		//get the corresponding poi bean for the given id
//		PoiBean bean = poiBeanList.get(Integer.parseInt(id));
		//start a new intent for displaying the content of the bean
		Intent intent = new Intent(this, PoiDetailActivity.class);
		intent.putExtra("POI_NAME", name);
		intent.putExtra("POI_DESC", description);
		this.startActivity(intent);
		return true;
	}
	
	/**
	 * method for creating random locations in the vicinity of the user
	 * @return array with lat and lon values as doubles
	 */
	private double[] createRandLocation() {
		 
		return new double[]{ TEST_LATITUDE + ((Math.random() - 0.5) / 500), TEST_LONGITUDE + ((Math.random() - 0.5) / 500),  TEST_ALTITUDE + ((Math.random() - 0.5) * 10)};
	}
	
	private double[] createRandLocationModel() {
		 
		return new double[]{ TEST_LATITUDE + 0.005, TEST_LONGITUDE + + 0.005,  TEST_ALTITUDE + (- 0.5) * 10};
	}

	/**
	 * loads a sample architect world and
	 * creates a definable amount of pois in beancontainers 
	 * and converts them into a jsonstring that can be sent to the framework
	 * @throws IOException exception thrown while loading an Architect world
	 */
	private void loadSampleWorld() throws IOException {
		this.architectView.load("tutorial1.html");

		final int NR_OF_TESTPOIS = 3;
		JSONArray array = new JSONArray();
		List<PoiBean> poiBeanList = new ArrayList<PoiBean>();
		try 
		{
			/*for (int i = 0; i < NR_OF_TESTPOIS; i++) {
				double[] location = createRandLocation();
				PoiBean bean = new PoiBean(
						""+i,
						"POI :" + i,
						"hotel , lieu est (#"+i+") a uneeeeeee" , (int) (Math.random() * 3), location[0], location[1], location[2]);
				array.put(bean.toJSONObject());
				poiBeanList.add(bean);
			}*/	
		//create a POI represented as a Model (type 4)
		double[] location = createRandLocationModel();
		PoiBean bean = new PoiBean(	""+NR_OF_TESTPOIS,"MODEL 3d", 
				"1 ", 4, location[0], location[1], location[2]);
		array.put(bean.toJSONObject());
		poiBeanList.add(bean);
		//
		//Ajout yessine
		double[] location1 = createRandLocationModel();
		JSONArray array1 = new JSONArray();
		
		PoiBean bean1 = new PoiBean(	""+NR_OF_TESTPOIS,"MODEL 3d", 
				"2", 6, location1[0], location1[1], location1[2]);
		array.put(bean1.toJSONObject());
		poiBeanList.add(bean1);
		
		// fin ajout
		
		
		this.architectView.callJavascript("newData(" + array.toString() + ");");
		  }
		
		catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

	
	@Override
	public void onLocationChanged(Location loc) {
		// IMPORTANT: 
		// use this method for informing the SDK about a location change by the user
		// for simplicity not used in this example
		
		//inform ArchitectView about location changes
		if(this.architectView != null) {
			this.architectView.setLocation((float)(loc.getLatitude()), (float)(loc.getLongitude()), loc.getAccuracy());
		
			
			
			System.out.print("////////xxxxxxxxxxxxxxxxxxxxxxxxx//////////");
			System.out.print(loc.getLatitude());
			System.out.print(loc.getAccuracy());
			
			//Log.println( 4 , String(loc.getLatitude()), String(loc.getAccuracy()) );
		
		}
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	
}