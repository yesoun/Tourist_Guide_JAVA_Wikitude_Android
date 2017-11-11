package pack.wikitude;

import java.io.File;

import pack.tunisiatouristicguide.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
public class ActivityChargeAllPOI extends Activity {
	
	
	public static final String EXTRAS_KEY_ACTIVITY_TITLE_STRING = "activityTitle";
	public static final String EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL = "activityArchitectWorldUrl";
	
	public static final String EXTRAS_KEY_ACTIVITIES_ARCHITECT_WORLD_URLS_ARRAY = "activitiesArchitectWorldUrls";
	public static final String EXTRAS_KEY_ACTIVITIES_TILES_ARRAY = "activitiesTitles";
	public static final String EXTRAS_KEY_ACTIVITIES_CLASSNAMES_ARRAY = "activitiesClassnames";
	final Intent intent = new Intent( this, BasicArchitectActivity.class );
	
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		Log.i("LA CLASSE ", "ActivityChargeAllPOI");
		
//		this.setContentView( this.getContentViewId() );
		
	//	this.setTitle(this.getActivityTitle());

		/* extract names of samples from res/arrays */
	
		/* use default list-ArrayAdapter */
	
		final Intent intent = new Intent( this, SamplePoidataFromNativeActivity.class );
		
		//intent.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING, this.getListLabels()[position]);
		intent.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING, "All Points of interest in Tunisia :");
		
		
		intent.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator + "1_All$Poi$in$Tunisia_" + File.separator + "index.html");
		//samples/1_POI$Poi$Data_1_From$WebService/index.html
		//Log.i("samples" + File.separator + this.getArchitectWorldUrls()[position] + File.separator + "index.html"
			//	, "samples + File.separator + this.getArchitectWorldUrls()[position] + ");
		Log.i(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "extras key activity ACTIVITY_ARCHITECT WORLD URL dans sampleslistactivity");
		
	    this.startActivity( intent );
	
	}
}
