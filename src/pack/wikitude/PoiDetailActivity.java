package pack.wikitude;

import pack.tunisiatouristicguide.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * simple Activity for displaying information about a specific poi
 * gets the information about a poi from an intent
 */
public class PoiDetailActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poi_detail);
			
		String name = this.getIntent().getExtras().getString("POI_NAME");
		String desc = this.getIntent().getExtras().getString("POI_DESC");
		
			((TextView) this.findViewById(R.id.tvPoiName)).setText(name);
			((TextView) this.findViewById(R.id.tvPoiDesc)).setText(desc);
	
		
	}
}
