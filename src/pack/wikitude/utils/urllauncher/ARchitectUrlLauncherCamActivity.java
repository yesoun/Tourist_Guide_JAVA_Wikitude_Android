package pack.wikitude.utils.urllauncher;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import pack.wikitude.utils.BasicSampleActivity;

import android.widget.Toast;


public class ARchitectUrlLauncherCamActivity extends BasicSampleActivity {

	public static final String ARCHITECT_ACTIVITY_EXTRA_KEY_URL = "url2launch";

	@Override
	public String getARchitectWorldPath() {
		try {
			final String decodedUrl = URLDecoder.decode(getIntent().getExtras().getString(ARCHITECT_ACTIVITY_EXTRA_KEY_URL), "UTF-8");
			return decodedUrl;
		} catch (UnsupportedEncodingException e) {
			Toast.makeText(this, "Unexpected Exception: " + e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
			return null;
		}
	}
	

}