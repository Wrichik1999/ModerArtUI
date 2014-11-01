package com.example.moderartui;

import java.util.Random;


// import android.support.v7.app.ActionBarActivity;
// import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.app.ActionBar;

public class MainActivity extends Activity {
	
	private SeekBar _seekBar = null;
	private View t1 = null;
	private View t2 = null;
	private View t3 = null;
	private View t4 = null;
	private View t5 = null;
	
	private int[][] colors;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		_seekBar = (SeekBar) findViewById(R.id.seekbar);
		_seekBar.setOnSeekBarChangeListener(new seekBarListener());
		
		t1 = findViewById(R.id.t1);
		t2 = findViewById(R.id.t2);
		t3 = findViewById(R.id.t3);
		t4 = findViewById(R.id.t4);
		t5 = findViewById(R.id.t5);
		
		colors = new int[5][6];
		initColors();
		
		t4.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	initColors();
		    	_seekBar.setProgress(0);
		    }
		});
		
	}
	
	private void initColors()
	{
		
		Random rnd = new Random(); 
		
		for( int i=0;i<5;i++)
		{
			if (i==3) continue;
			for (int j=0;j<6;j++)
			{
				colors[i][j]=rnd.nextInt(256);
			}
		}
		
		int w;
		w = 200+rnd.nextInt(56);
		colors[3][0] = w;
		colors[3][1] = w;
		colors[3][2] = w;
		
		w = 200+rnd.nextInt(56);
		colors[3][3] = w;
		colors[3][4] = w;
		colors[3][5] = w;
		
		setColors(0);
	}
	
	private void setColors(int pct)
	{
		int r,g,b,color;
		
		for (int i=0;i<5;i++)
		{
			r = colors[i][0] + (colors[i][3] - colors[i][0])*pct/100;
			g = colors[i][1] + (colors[i][4] - colors[i][1])*pct/100;
			b = colors[i][2] + (colors[i][5] - colors[i][2])*pct/100;
			color = Color.argb(255,r,g,b);
			
			switch (i)
			{
			case 0:
				t1.setBackgroundColor(color);
				break;
			case 1:
				t2.setBackgroundColor(color);
				break;
			case 2:
				t3.setBackgroundColor(color);
				break;
			case 3:
				t4.setBackgroundColor(color);
				break;
			case 4:
				t5.setBackgroundColor(color);
				break;
			}
		}
		
	}
	
	private class seekBarListener implements SeekBar.OnSeekBarChangeListener {
		
        public void onProgressChanged(SeekBar seekBar, int progress,
                boolean fromUser) {
        		setColors(progress);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {}

        public void onStopTrackingTouch(SeekBar seekBar) {
        	// changeColors(progressChanged);
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
		// well, this is going to take more time than I have this weekend
		// but the approach would be to create a custom theme and apply it
		// to the dialog.  Not enough time to put all the monkeys in the barrel.

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
