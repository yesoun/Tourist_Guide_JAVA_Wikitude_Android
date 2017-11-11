package pack.wikitude;

import org.json.JSONException;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.wikitude.architect.ArchitectUrlListener;

public class SamplePoidataFromNativeAndUrlListenerActivity extends SamplePoidataFromNativeActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("LA CLASSE ", "SamplePoidataFromNativeAndUrlListenerActivity");

		this.architectView.registerUrlListener(new ArchitectUrlListener() {
			
			@Override
			// fetch e.g. document.location = "architectsdk://markerselected?id=1";
			public boolean urlWasInvoked(String uriString) {
				Uri invokedUri = Uri.parse(uriString);
				if ("markerselected".equalsIgnoreCase(invokedUri.getHost()) && invokedUri.getQueryParameter("id")!=null) {
					final int poiDataIndex = Integer.valueOf(invokedUri.getQueryParameter("id"));
					try {
						final Intent poiDetailIntent = new Intent(SamplePoidataFromNativeAndUrlListenerActivity.this, SamplePoiDetailActivity.class);
						poiDetailIntent.putExtra(SamplePoiDetailActivity.EXTRAS_KEY_POIDATA, poiData.getJSONObject(poiDataIndex-1).toString());
						SamplePoidataFromNativeAndUrlListenerActivity.this.startActivity(poiDetailIntent);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
				}
				return false;
			}
		});
	}
	
	

}