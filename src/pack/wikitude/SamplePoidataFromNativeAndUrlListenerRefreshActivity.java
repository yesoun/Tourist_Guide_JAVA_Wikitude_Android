package pack.wikitude;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.wikitude.architect.ArchitectUrlListener;

public class SamplePoidataFromNativeAndUrlListenerRefreshActivity extends SamplePoidataFromNativeActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("la classe ", "SamplePoidataFromNativeAndUrlListenerRefreshActivity");
		
			this.architectView.registerUrlListener(new ArchitectUrlListener() {
				
			@Override
			public boolean urlWasInvoked(String uri) {
				try {
					final Uri parsedUri = Uri.parse(uri);
					
					/* you may parse host and arguments to differ actions */
					if ("button".equals(parsedUri.getHost()) && "type=refresh".equals(parsedUri.getQuery()) ) {
						loadData();
					}
					
				} catch (final Exception e) {
					e.printStackTrace();
				}
				
				return true;
			}
		});
	}
	
	

}