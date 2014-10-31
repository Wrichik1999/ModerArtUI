package com.example.moderartui;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	
	private SeekBar _seekBar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		_seekBar = (SeekBar) findViewById(R.id.seekbar);
		_seekBar.setOnSeekBarChangeListener(new seekBarListener());
	}
	
	private void changeColors(int colorIndex)
	{
		// TODO: set the colors
		Toast.makeText(MainActivity.this,"Seek color set:"+colorIndex, 
				Toast.LENGTH_SHORT).show();
	}	
	
	private class seekBarListener implements SeekBar.OnSeekBarChangeListener {
		
		int progressChanged = 0;

        public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser) {
        	progressChanged = progress;
        }

        public void onStartTrackingTouch(SeekBar seekBar) {}

        public void onStopTrackingTouch(SeekBar seekBar) {
        	changeColors(progressChanged);
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
		AlertDialog.Builder moreInfoBuilder = new AlertDialog.Builder(this);
		
		TextView textView = new TextView(this);
		textView.setText(getString(R.string.info_title));
		textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		
		// TODO: make the buttons nicer too

		moreInfoBuilder
			.setCustomTitle(textView)
			.setCancelable(false)
			.setPositiveButton(getString(R.string.info_visit_moma),new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					Intent browserIntent = new Intent(Intent.ACTION_VIEW, 
							Uri.parse("http://www.moma.org/"));
					startActivity(browserIntent);
				}
			})
			.setNegativeButton(getString(R.string.info_not_now),new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
				dialog.cancel();
				}
			});
		
		AlertDialog moreInfoDialog = moreInfoBuilder.create();
		
		moreInfoDialog.show();
	}

}
