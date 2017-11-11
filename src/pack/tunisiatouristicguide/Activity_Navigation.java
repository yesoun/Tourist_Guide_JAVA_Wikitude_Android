package pack.tunisiatouristicguide;






import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pack.events.ItemListFragment;
import pack.tunisiatouristicguide.Activity_Navigation.FragmentCreation;
import pack.tunisiatouristicguide.ExpandableListAdapter;
import pack.wikitude.ActivityChargeAllPOI;
import pack.wikitude.MainActivity2;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

import com.google.android.gms.maps.MapFragment;

public class Activity_Navigation extends FragmentActivity {
	//private static GoogleMap googleMap;
	private SupportMapFragment mMapFragment;
	private boolean isJustAfterResume = false;
    private static final String[] CONTENT = new String[] { "Calendar", "Maps" , "Camera"  };
    private static final String[] CONTENT1 = new String[] { "Popular events ", "Location with google maps" , "Augmented Reality videos"  };
    
    private static final int[] ICONS = new int[] {
            R.drawable.perm_group_calendar,
            R.drawable.perm_group_location,
            R.drawable.perm_group_camera,
            R.drawable.perm_group_device_alarms,
            
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        // la view à  afficher simple_tabs qui contient le ciew pager qui contient les 4 onglets
        // Camera , Agenda , Maps , Notes
        setContentView(R.layout.simple_tabs);
        
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        final FragmentPagerAdapter adapter = new GoogleMusicAdapter(getSupportFragmentManager());

     // Set up the ViewPager with the sections adapter.
        ViewPager pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

       // pager.setCurrentItem(2); //sets initial page to "Facebook"
        
        TabPageIndicator indicator = (TabPageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        
        
        
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(Activity_Navigation.this, CONTENT1[position % CONTENT.length].toUpperCase() , Toast.LENGTH_SHORT).show();
               
             // Notify the current page whether it is just after the activity resumed or not.
                
             // Store the position of current page
            
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        
    }

   ////////////////////////
    class GoogleMusicAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
        public GoogleMusicAdapter(FragmentManager fm) {
            super(fm);
        }

        
    
// getItem is called to instantiate the fragment for the given page.
// Return a DummySectionFragment (defined as a static inner class
// below) with the page number as its lone argument.      
         @Override
         // c'est la partie ou en écrit dans chaque vue
          public Fragment getItem(int position) {
        	 Fragment fragment = new FragmentCreation();
        	 Bundle args = new Bundle();
             args.putInt(FragmentCreation.ARG_SECTION_NUMBER, position );
             fragment.setArguments(args);
             return fragment;
        	 
                }
        
         
         @Override
        public boolean isViewFromObject(View view, Object object) {
        	// TODO Auto-generated method stub
        	return super.isViewFromObject(view, object);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override public int getIconResId(int index) {
          return ICONS[index];
        }

      @Override
        public int getCount() {
          return CONTENT.length;
        }
    }
    
    public static class FragmentCreation extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
    	
    	Button button1;
        
        public static final String ARG_SECTION_NUMBER = "section_number";

        public FragmentCreation() {
        }


        
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            // Create a new TextView and set its text to the fragment's section
            // number argument value.
            
        	
        	
        	/////////////////////////////////////////////////////////////////////////////////////////
        	// pour le fragment numéro 0 , càd l'onglet Calendar qui contient touis les évennements//
        	////////////////////////////////////////////////////////////////////////////////////////
        	if(getArguments().getInt(ARG_SECTION_NUMBER)==0)
        	{
        		
        		ExpandableListAdapter listAdapter;
        		ExpandableListView expListView;
        		final List<String> listDataHeader;
        		final HashMap<String, List<String>> listDataChild;
        		final View view1 = inflater.inflate(R.layout.expandible_list_view_event, container, false);
        		// get the listview
        		expListView = (ExpandableListView) view1.findViewById(R.id.lvExp);

        		// preparing list data
        		listDataHeader = new ArrayList<String>();
        		listDataChild = new HashMap<String, List<String>>();

        		// Adding child data
        		listDataHeader.add("Festivals");
        		listDataHeader.add("Theater & Cinema");
        		listDataHeader.add("Concerts");
        		listDataHeader.add("Cultural events");

        		// Adding child data
        		List<String> Festivals = new ArrayList<String>();
        		Festivals.add("Carthage");
        		Festivals.add("Hammamat");
        		Festivals.add("SFAX ");
        		Festivals.add("Bizerte");
        		Festivals.add("Gabes");
        		
        		List<String> Cinema = new ArrayList<String>();
        		Cinema.add("Spiderman");
        		Cinema.add("Spartacus");
        		Cinema.add("Grown Ups 2");
        		Cinema.add("The Wolverine");
        		
        		List<String> Concerts = new ArrayList<String>();
        		Concerts.add("George Wassouf");
        		Concerts.add("Saber Erbbai");
        		Concerts.add("Diams");
        		Concerts.add("Adele");
        		
        		
        		List<String> TheaterCinema = new ArrayList<String>();
        		TheaterCinema.add("George Wassouf");
        		TheaterCinema.add("Saber Erbbai");
        		TheaterCinema.add("Diams");
        		TheaterCinema.add("Adele");
        		
        		listDataChild.put(listDataHeader.get(0), Festivals); // Header, Child data
        		listDataChild.put(listDataHeader.get(1), Cinema);
        		listDataChild.put(listDataHeader.get(2), Concerts);
        		listDataChild.put(listDataHeader.get(3), TheaterCinema);
        		listAdapter = new ExpandableListAdapter(view1.getContext(), listDataHeader, listDataChild);
 
        		expListView.setAdapter(listAdapter);
        		expListView.setOnGroupClickListener(new OnGroupClickListener() {
        			public boolean onGroupClick(ExpandableListView parent, View v,
        					int groupPosition, long id) {
        				// Toast.makeText(getApplicationContext(),
        				// "Group Clicked " + listDataHeader.get(groupPosition),
        				// Toast.LENGTH_SHORT).show();
        				return false;
        			}
        		});
        		

        		// Listview Group expanded listener
        		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
        			@Override
        			public void onGroupExpand(int groupPosition) {
        				Toast.makeText(getActivity(),
        						listDataHeader.get(groupPosition) + " Expanded",
        						Toast.LENGTH_SHORT).show();
        			}
        		});
        		
        		
        		
        		// Listview Group collasped listener
        		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

        			@Override
        			public void onGroupCollapse(int groupPosition) {
        				Toast.makeText(getActivity(),listDataHeader.get(groupPosition) + " Collapsed",Toast.LENGTH_SHORT).show();

        			}
        		});

        		
        		// Listview on child click listener
        		expListView.setOnChildClickListener(new OnChildClickListener() {
        			@Override
        			public boolean onChildClick(ExpandableListView parent, View v,
        					int groupPosition, int childPosition, long id) {
        				// TODO Auto-generated method stub
        				Toast.makeText(getActivity(),listDataHeader.get(groupPosition) + " : " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
        				 Uri uri = Uri.parse("http://www.google.com");
        				 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        				 startActivity(intent);        		


return false;
        			}
        		});
        		
        		
        		return view1;
        		
        		
        		
        	}
        	
///////////////////////////////////////////////////////////////////////////////////
////////////// pour le fragment numéro 2 , càd l'onglet Maps /////////////////////
//////////////////////////////////////////////////////////////////////////////////

if(getArguments().getInt(ARG_SECTION_NUMBER)==1)
{

View view = inflater.inflate(R.layout.map, container, false);
GoogleMap googleMap = ((SupportMapFragment)  getFragmentManager().findFragmentById(R.id.map)).getMap();
googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

googleMap.setMyLocationEnabled(true);
// Enable / Disable zooming controls
googleMap.getUiSettings().setZoomControlsEnabled(false);

// Enable / Disable my location button
googleMap.getUiSettings().setMyLocationButtonEnabled(true);

// Enable / Disable Compass icon
googleMap.getUiSettings().setCompassEnabled(true);

// Enable / Disable Rotate gesture
googleMap.getUiSettings().setRotateGesturesEnabled(true);

// Enable / Disable zooming functionality
googleMap.getUiSettings().setZoomGesturesEnabled(true);




double latitude = 36.915057;
double longitude = 10.063635;
//Log.i("huhuhuhuhé", "latitude" + googleMap.getMyLocation().getLatitude());
//Log.i("alti", "alti" + googleMap.getMyLocation().getAltitude());
for (int i = 1; i < 21; i++) {
// random latitude and logitude
double[] randomLocation = createRandLocation(latitude,longitude);

// Adding a marker
MarkerOptions marker = new MarkerOptions().position(
new LatLng(randomLocation[0], randomLocation[1]))
.title(" # POI " + i);

Log.e("Random", "> " + randomLocation[0] + ", "
+ randomLocation[1]);

// changing marker color
if (i == 1)
marker.icon(BitmapDescriptorFactory
.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
if (i == 2 || i ==20)
marker.icon(BitmapDescriptorFactory
.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
if (i == 3 || i ==13)
marker.icon(BitmapDescriptorFactory
.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
if (i == 4 || i == 14)
marker.icon(BitmapDescriptorFactory
.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
if (i == 5 || i == 15)
marker.icon(BitmapDescriptorFactory
.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
if (i == 6 || i == 16)
marker.icon(BitmapDescriptorFactory
.defaultMarker(BitmapDescriptorFactory.HUE_RED));
if (i == 7 || i == 17)
marker.icon(BitmapDescriptorFactory
.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
if (i == 8 || i == 18)
marker.icon(BitmapDescriptorFactory
.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
if (i == 9 || i == 19)
marker.icon(BitmapDescriptorFactory
.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));

if (i == 10)
marker.icon(BitmapDescriptorFactory
.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

googleMap.addMarker(marker);

// Move the camera to last position with a zoom level
if (i == 9) {
CameraPosition cameraPosition = new CameraPosition.Builder()
.target(new LatLng(randomLocation[0],
randomLocation[1])).zoom(15).build();

googleMap.animateCamera(CameraUpdateFactory
.newCameraPosition(cameraPosition));
}
}




return view ;
}

        	
        	////////////////////////////////////////////////////////////////////////////   
        	////////// pour le fragment numéro 2 , càd l'onglet caméra//////////////////
        	////////////////////////////////////////////////////////////////////////////
        	if(getArguments().getInt(ARG_SECTION_NUMBER)==2)
        	{   
        		
        	
        	final View view1 = inflater.inflate(R.layout.camview, container, false);
                TextView textView = (TextView) view1.findViewById(R.id.messa);
                 textView.setText(textView.getText());
                 
                 Button button = (Button) view1.findViewById(R.id.callbtn);
                 button.setText("AR cam navigation");
                 button.setOnClickListener(new View.OnClickListener() {
                         public void onClick(View v) {
                        	 Intent myIntent = new Intent(view1.getContext(),pack.wikitude.MainActivity2.class);
                        	 startActivityForResult(myIntent, 0);
                        	 FragmentCreation.this.getView().setVisibility(View.FOCUS_FORWARD);
                                         
            
                        	 
                        	 
                        	 
                         }
                 });
                 return view1;
        	
        	
        		
        	}
           
        	
        	        	
        	
        	View view = inflater.inflate(R.layout.camview, container, false);
    		
        	return view ;
        	
            
        	
        	
        	

    }
   
       




	private double[] createRandLocation(double latitude, double longitude) {
			// TODO Auto-generated method stub
		
		
			return new double[] { latitude + ((Math.random() - 0.5) / 500),
					longitude + ((Math.random() - 0.5) / 500),
					150 + ((Math.random() - 0.5) * 10) };
		}






	public static class SecondFragment extends Fragment {
        protected static final String TAG = "SecondFragmentTag";
        int key;

        public SecondFragment() {
                this.setRetainInstance(true);
                Random random = new Random();
                key = random.nextInt();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                Log.i(TAG, "onCreateView");
                View view = inflater.inflate(R.layout.camview, container, false);
             /*   TextView textView = (TextView) view.findViewById(R.id.button);
                textView.setText("messageee");
                Button button = (Button) view.findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                        	FragmentCreation firstFragment = (FragmentCreation) SecondFragment.this.getActivity()
                                                .getSupportFragmentManager().findFragmentById(R.id.button);
                                firstFragment.getView().setVisibility(View.VISIBLE);
                                SecondFragment.this.getView().setVisibility(View.GONE);
                        }

                });*/
                return view;
        }
    
    // la classe dont on a fait appel depuis chaque partie de créatio nde fragment
    // cread by YessineMaalej
   
    
}






	public LayoutInflater getSystemService(String layoutInflaterService) {
		// TODO Auto-generated method stub
		return null;
	}
}
    
    

}



