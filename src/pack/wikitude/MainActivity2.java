package pack.wikitude;




import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pack.tunisiatouristicguide.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;
//import com.wikitude.sdksamples.R;


// LA premiére activité qui va parcourir le dossier assets pour en chercher les noms des dossier qui seront comme option dans
// une liste view
public class MainActivity2 extends ListActivity{
	
	private static final String EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL = "activity url";
	private static final String EXTRAS_KEY_ACTIVITY_TITLE_STRING = "activity tilte";
	private static final String numero= "0";
	
	public  String EXTRAS_KEY_ACTIVITIES_ARCHITECT_WORLD_URLS_ARRAY = "activitiesArchitectWorldUrls";
	private ListView lv;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		this.setContentView( this.getContentViewId() );
		
		// ensure to clean cache when it is no longer required
		MainActivity2.deleteDirectoryContent ( ArchitectView.getCacheDirectoryAbsoluteFilePath(this) );

		
		
		
		Log.i("LA CLASSE ", "MAINACTIVITY");
		// extract names of samples from res/arrays
		final String[] values = this.getListLabels();
		//final String[] v=this.g
		// use default list-ArrayAdapter */
		//lv = (ListView) findViewById(R.id.listView1);
		this.setListAdapter( new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, android.R.id.text1, values ) );
	
	}

	
	//méthode aprés click sur une des liste de choix avec
	@Override
	protected void onListItemClick( ListView l, View v, int position, long id ) {
		super.onListItemClick( l, v, position, id );
			
		
			final List<SampleMeta> activitiesToLaunch = getActivitiesToLaunch(position);
			final String activityTitle = activitiesToLaunch.get(0).categoryId + ". " + activitiesToLaunch.get(0).categoryName.replace("$", " ");
			String[] activityTitles = new String[activitiesToLaunch.size()];
			String[] activityUrls = new String[activitiesToLaunch.size()];
			String[] activityClasses = new String[activitiesToLaunch.size()];
			SampleMeta meta = null ;
			
			for (int i= 0; i< activitiesToLaunch.size();i++) {
				 meta = activitiesToLaunch.get(i);
				activityTitles[i] = (meta.categoryId + "."  );
				Log.i(activityTitles[i], "Le numero .");
				//Log.i(toString(meta.categoryId), "Le numero");
				Log.i(meta.categoryName, "le nom");
				Log.i(meta.path, "le chemin");
				
				activityUrls[i] =  (meta.path);}
			Log.i("aaa", "aaaaaaaaa");
			
			Log.i("aaa1", "aaaaaaaaa");
		
				
				switch (meta.categoryId) {
				
				
				case 1:
					
					final Intent intent1 = new Intent( this, ActivityChargeAllPOI.class );
					intent1.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING, "poiDATAFROMWEBSERVICE");
					intent1.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator + "1_All$Poi$in$Tunisia_" + File.separator + "index.html");
					this.startActivity( intent1 );
					break;
				//hotel	
				case 2:
					
					final Intent intent2 = new Intent( this, HotelsListActivity.class );
					intent2.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING, "hhhhds");
					intent2.putExtra(numero, 2);
					intent2.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator + "2_Hotels_" + File.separator + "index.html");
					this.startActivity( intent2 );
					break;
					
				//restaurents	
				case 3:
					
					final Intent intent3 = new Intent( this, RestaurentsListActivity.class );
					intent3.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING, "poiDATAFROMWEBSERVICE");
					intent3.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator + "3_Restaurents_" + File.separator + "index.html");
					this.startActivity( intent3 );
					break;
					
					//Meuseum	
				case 4:
						
						final Intent intent4 = new Intent( this, SimpleARBrowserActivity.class );
						intent4.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING, "poiDATAFROMWEBSERVICE");
						intent4.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator + "4_Meuseum_" + File.separator + "index.html");
						this.startActivity( intent4 );
						break;
					
					
		
					
                case 5:
					
					final Intent intent5 = new Intent( this, ActivityChargeAllPOI.class );
					intent5.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING, "poiDATAFROMWEBSERVICE");
					intent5.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator + "1_Obtain$Poi$Data$From$Webservice_" + File.separator + "index.html");
					this.startActivity( intent5 );	
					break;
				
					
					
				/*case 10:
					
					final Intent intent10 = new Intent( this, SamplePoidataFromNativeAndUrlListenerRefreshActivity.class );
					intent10.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING, "presentingpoidata");
					intent10.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator + "10_Browsing_Presenting_" + File.separator + "index.html");
					this.startActivity( intent10 );
					
				case 9:
					
					final Intent intent9 = new Intent( this, ActivityChargeAllPOI.class );
					intent9.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING, "presentingpoidata");
					intent9.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator + "9_Browsing$Pois_3_Limiting$Visible$Pois_" + File.separator + "index.html");
					this.startActivity( intent9 );
					
				case 2:
						
					//final Intent intent2 = new Intent( this, ActivityChargeAllPOI.class );
					final Intent intent2 = new Intent( this, ActivityChargeAllPOI.class );
					//intent2.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING, "poiDATAFROMWEBSERVICE");
					//intent2.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator + "2_Hotels_" + File.separator + "index.html");
					
					intent2.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING, "Hotels");
					
					
					intent2.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator + "2_Hotels_" + File.separator + "index.html");
					//samples/1_POI$Poi$Data_1_From$WebService/index.html
					//Log.i("samples" + File.separator + this.getArchitectWorldUrls()[position] + File.separator + "index.html"
						//	, "samples + File.separator + this.getArchitectWorldUrls()[position] + ");
					Log.i(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "extras key activity ACTIVITY_ARCHITECT WORLD URL dans sampleslistactivity");
					
				    this.startActivity( intent2 );
					*/
					
					
					
				}
					//code fonctionnel
					/*Log.i("yesssss9", "wilyeééee");
					final String className="com.wikitude.samples.BasicArchitectActivity";
					final Intent intent = new Intent( this, SamplesListActivity.class );
					this.startActivity( intent );
					*/
					/*
					try {
						final Intent intent2 = new Intent( this, Class.forName( className ) );
						Log.i("yesssss8", "wilyeééee");
						Log.i(this.getListLabels()[position], "wilyeééee");
						
						intent2.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING, this.getListLabels()[position]);
						Log.i("yesssss9", "wilyeééee");
						Log.i(this.getListLabels()[position], "wilyeééee");
						
						
						//"samples" + File.separator + this.getArchitectWorldUrls()[position] + File.separator + "index.html"
						intent2.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples"+File.separator+ "1_All$POI$In$Tunisia_"+ File.separator +"index.html");
						Log.i("yesssss10", "wilyeééee");
						Log.i("yesssss11", "wilyeééee");
						
						this.startActivity( intent2 );
						Log.i("yesssss12", "wilyeééee");
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						Log.i("yesssss13", "errrrrrrrrrrreur");
						
						Toast.makeText( this, className + "\nnot defined/accessible", Toast.LENGTH_SHORT ).show();
					}
									
					*/
				
				}
				
				/*case 5:
						System.out.print("lllllllllllllllllllllllllllllll");
						System.out.print(i);
									
							 activityClasses[5] = ("com.wikitude.samples.SamplePoidataFromNativeAndUrlListenerActivity");
						     final String className5=activityClasses[5];
					try {
						final Intent intent5 = new Intent( this, Class.forName( className5 ) );
						intent5.putExtra(EXTRAS_KEY_ACTIVITY_TITLE_STRING, this.getListLabels()[position]);
						intent5.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator +activityUrls[5] + "index.html");
						this.startActivity(intent5);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						Toast.makeText( this, className5 + "\nnot defined/accessible", Toast.LENGTH_SHORT ).show();
						
						e.printStackTrace();
					}
						
							
							
							break;
						
					case 4:
						activityClasses[4] = ("com.wikitude.samples.BasicArchitectActivity");
					     final String className4=activityClasses[4];
							Intent intent4;
							try {
								intent4 = new Intent( this, Class.forName( className4 ) );
								intent4.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator +activityUrls[4] + "index.html");
								this.startActivity(intent4);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								Toast.makeText( this, className4 + "\nnot defined/accessible", Toast.LENGTH_SHORT ).show();
								
								e.printStackTrace();
							}
								break;
						
					case 3:
						activityClasses[3] = ("com.wikitude.samples.BasicArchitectActivity");
						 final String className3=activityClasses[3];
							Intent intent3;
							try {
								intent3 = new Intent( this, Class.forName( className3 ) );
								intent3.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator +activityUrls[3] + "index.html");
								this.startActivity(intent3);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								Toast.makeText( this, className3 + "\nnot defined/accessible", Toast.LENGTH_SHORT ).show();
								
								e.printStackTrace();
							}break;
					case 2:
						activityClasses[2] = ("com.wikitude.samples.BasicArchitectActivity");
						 final String className2=activityClasses[2];
							Intent intent2;
							try {
								intent2 = new Intent( this, Class.forName( className2 ) );
								intent2.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator +activityUrls[2] + "index.html");
								this.startActivity(intent2);
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								Toast.makeText( this, className2 + "\nnot defined/accessible", Toast.LENGTH_SHORT ).show();
								
								e.printStackTrace();
							}break;
					case 1:
						activityClasses[1] = ("com.wikitude.samples.BasicArchitectActivity");
						final String className1=activityClasses[1];
						Intent intent1;
						try {
							intent1 = new Intent( this, Class.forName( className1 ) );
							intent1.putExtra(EXTRAS_KEY_ACTIVITY_ARCHITECT_WORLD_URL, "samples" + File.separator +activityUrls[1] + "index.html");
							this.startActivity(intent1);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							Toast.makeText( this, className1 + "\nnot defined/accessible", Toast.LENGTH_SHORT ).show();
							
							e.printStackTrace();
						}break;
						
					default:
						activityClasses[i] = ("com.wikitude.samples.BasicArchitectActivity");
							break;	
						}
						break;
							
					
				}*/
						
			
	
	
	//intent.putExtra(MainActivity.EXTRAS_KEY_ACTIVITIES_ARCHITECT_WORLD_URLS_ARRAY, activityUrls);
	//EXTRAS_KEY_ACTIVITIES_ARCHITECT_WORLD_URLS_ARRAY = activityUrls;		
	/*intent.putExtra(SamplesListActivity.EXTRAS_KEY_ACTIVITIES_ARCHITECT_WORLD_URLS_ARRAY, activityUrls);
	intent.putExtra(SamplesListActivity.EXTRAS_KEY_ACTIVITIES_CLASSNAMES_ARRAY, activityClasses);
	intent.putExtra(SamplesListActivity.EXTRAS_KEY_ACTIVITIES_TILES_ARRAY, activityTitles);
	intent.putExtra(SamplesListActivity.EXTRAS_KEY_ACTIVITY_TITLE_STRING, activityTitle);
	*/
			
			
			
	
	
	
	

	protected final String[] getListLabels() {
		// extraire les noms des fonctionnalités dans la premiére activité 
		// càd selon les dossiers existants dans le dossier assets/samples on aura une line selon le nom du dossier avec le changement appropiré
			
		final Map<Integer, List<SampleMeta>> samples = getActivitiesToLaunch();
		final String[] labels = new String[samples.keySet().size()];
		for (int i = 0; i<labels.length; i++) {
			Log.i(samples.get(i).get(0).categoryName, "hhhhhhhhhhhhhhhhhhhhhhh");
			labels[i] = samples.get(i).get(0).categoryId + ". " + samples.get(i).get(0).categoryName.replace("$", " ");
		}
		return labels;
	}
	
	/// tous les URLS des dossier figurant dans le dossiers assets/samples
	protected String[] getArchitectWorldUrls() {
		return getIntent().getExtras().getStringArray("5_Means$of$transports_");
	}
	
	
	
	
	//////////////setcontentview////////////////////
	// la vue de départ list_startscreen///
	protected int getContentViewId() {
		return R.layout.list_startscreen;
	}
	
	
	// aprés click sur launch world via URLpour ouvrir un ARchitectUrlLauncherActivity/////
	public void buttonClicked(final View view)
	 {
		try {
			
			this.startActivity( new Intent( this, Class.forName( "com.wikitude.samples.utils.urllauncher.ARchitectUrlLauncherActivity" ) ) );
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	 }
	
	
	
	
	/**
	 * deletes content of given directory
	 * @param path
	 */
	
	//methode qui vide la mémoire cache utilisée de l'applicaiton quand on a plus besoins
	//ensure to clean cache when it is no longer required
	private static void deleteDirectoryContent(final String path) {
		try {
			final File dir = new File (path);
			if (dir.exists() && dir.isDirectory()) {
				final String[] children = dir.list();
		        for (int i = 0; i < children.length; i++) {
		            new File(dir, children[i]).delete();
		        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<SampleMeta> getActivitiesToLaunch(final int position){
		return getActivitiesToLaunch().get(position);
	}
	
	private Map<Integer, List<SampleMeta>> getActivitiesToLaunch(){
		final Map<Integer, List<SampleMeta>> pos2activites = new HashMap<Integer, List<SampleMeta>>();

		String[] assetsIWant;
		try {
			assetsIWant = getAssets().list("samples");
		
		
		int pos = -1;
		int lastCategoryId = -1;
		for(String asset: assetsIWant) {
			SampleMeta sampleMeta = new SampleMeta(asset);
			if (sampleMeta.categoryId!=lastCategoryId) {
				pos++;
				pos2activites.put(pos, new ArrayList<SampleMeta>());
			} 
			pos2activites.get(pos).add(sampleMeta);
			lastCategoryId = sampleMeta.categoryId;
		}
		
			return pos2activites;
			
			
			
		
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static class SampleMeta {
		
		final String path, categoryName;
		final int categoryId;
		
		public SampleMeta(String path) {
			super();
			this.path = path;
			categoryId = Integer.valueOf(path.substring(0, path.indexOf("_")));
			path = path.substring(path.indexOf("_")+1);
			categoryName = path.substring(0, path.indexOf("_"));
			path = path.substring(path.indexOf("_")+1);
			/*sampleId = Integer.valueOf(path.substring(0, path.indexOf("_")));
			path = path.substring(path.indexOf("_")+1);
			sampleName = path;*/
		}
		
		@Override
		public String toString() {
			return "categoryId:" + categoryId + ", categoryName:" + categoryName + ", path: " + this.path;
		}
	}
	

}
