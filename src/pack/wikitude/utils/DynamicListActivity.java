package pack.wikitude.utils;

//import pack.tunisiatouristicguide.R;
import pack.tunisiatouristicguide.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;





public abstract class DynamicListActivity extends ListActivity {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		this.setContentView( this.getContentViewId() );

		/* extract names of samples from res/arrays */
		final String[] values = this.getListValues();

		/* use default list-ArrayAdapter */
		this.setListAdapter( new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, android.R.id.text1, values ) );
	}
	
	/**
	 * array containing all values to display, each value is shown in a new row
	 * @return
	 */
	protected int getContentViewId() {
		return R.layout.list_main;
	}
	
	/**
	 * array containing all values to display, each value is shown in a new row
	 * @return
	 */
	protected abstract String[] getListValues();
	
	/**
	 * class names of the Activities to call. Must be same order and length as in list-values
	 * @return
	 */
	protected abstract String[] getListActivities();
	

	@Override
	protected void onListItemClick( ListView l, View v, int position, long id ) {
		super.onListItemClick( l, v, position, id );

		/* get className of activity to call when clicking item at position x */
		final String className = getListActivities()[position];

		try {
			/* launch activity */
			this.startActivity( new Intent( this, Class.forName( className ) ) );
		} catch ( Exception e ) {
			/* may never occur, as long as all SampleActivities exist and are listed in manifest */
			Toast.makeText( this, className + "\nnot defined/accessible", Toast.LENGTH_SHORT ).show();
		}

	}

}
