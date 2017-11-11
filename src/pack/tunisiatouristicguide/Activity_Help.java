package pack.tunisiatouristicguide;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class Activity_Help extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
	   call();
	
	
	}

	
	
	private void call() {
	    try {
	        Intent callIntent = new Intent(Intent.ACTION_CALL);
	        callIntent.setData(Uri.parse("tel:21196229"));
	        startActivity(callIntent);
	    } catch (ActivityNotFoundException activityException) {
	         Log.i("Appel Urgence failed", "appel erreur");
	    }
	}
	 
}
