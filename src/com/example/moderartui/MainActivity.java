package com.example.moderartui;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.more_info) {
			doMoreInfo();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void doMoreInfo()
	{
		// TODO: use a string resource to set titles, etc
		AlertDialog.Builder moreInfoBuilder = new AlertDialog.Builder(this);
		moreInfoBuilder.setTitle("Inspired by the works of artists such as\nPiet Mondrian and Ben Nicholson.");
		moreInfoBuilder
			.setMessage("Click below to learn more!")
			.setCancelable(false)
			.setPositiveButton("Visit MOMA",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// TODO: stub out open web page
					Toast toast = Toast.makeText(getApplicationContext(), 
							"More info is not yet available!", 
							Toast.LENGTH_SHORT);
					toast.show();
				}
			})
			.setNegativeButton("Not Now",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
				dialog.cancel();
				}
			});
		
		AlertDialog moreInfoDialog = moreInfoBuilder.create();
		
		moreInfoDialog.show();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
