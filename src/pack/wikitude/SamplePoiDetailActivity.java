package pack.wikitude;

import org.json.JSONException;
import org.json.JSONObject;

import com.androidhive.imagefromurl.ImageLoader;

import pack.tunisiatouristicguide.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.wikitude.sdksamples.R;

public class SamplePoiDetailActivity extends Activity {
	// Classe utilisée pour afficher le nom , la descriptino ainsi que les données d'un poi aprés avoir le sélectionner
	
	public static final String EXTRAS_KEY_POIDATA = "poiData";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("LA CLASSE ", "SamplePoiDetailActivity");
	
		setContentView(R.layout.sample_5_1_poidetail);
		// fill layout with JSON object data
		 
		
		int loader = R.drawable.loader;
	        
	     
		try {
			JSONObject poiData = new JSONObject(getIntent().getExtras().getString(EXTRAS_KEY_POIDATA) );
			// name , description , latitude , longitude sont des variables
			// Cette classe est utilisé dans Presenting PoifDetails
			((TextView)findViewById(R.id.poi_title)).setText(poiData.getString("name"));
			((TextView)findViewById(R.id.poi_description)).setText(poiData.getString("description"));
			((TextView)findViewById(R.id.poi_latitude)).setText(poiData.getString("latitude"));
			((TextView)findViewById(R.id.poi_longitude)).setText(poiData.getString("longitude"));
			((TextView)findViewById(R.id.poi_latitude)).setText(poiData.getString("altitude"));
			
		    // Imageview to show
	        ImageView image = (ImageView) findViewById(R.id.image);
	        
	        // Image url
	        String image_url = "http://api.androidhive.info/images/sample.jpg";
	        
	        // ImageLoader class instance
	        ImageLoader imgLoader = new ImageLoader(getApplicationContext());
	        
	        // whenever you want to load an image from url
	        // call DisplayImage function
	        // url - image url to load
	        // loader - loader image, will be displayed before getting image
	        // image - ImageView 
	        imgLoader.DisplayImage(image_url, loader, image);
	
		} catch (JSONException e) {
			Toast.makeText(this, "Unexpected error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
			this.finish();
			return;
		}
	}

}
