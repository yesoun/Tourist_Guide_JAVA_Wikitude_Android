package pack.tunisiatouristicguide;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScreenSlidePageFragment extends Fragment {
	/*
	 * Cette classe permet de crés des fragments qui retournent les Layout appropriés aprés
	 * dans chaque pager de notre ViewPager 
	 */

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.camview, container, false);

        return rootView;
    }
}
