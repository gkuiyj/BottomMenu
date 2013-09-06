package net.weibo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.Toast;

public class ThreeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.three);
		setTitle("第一");

		Gallery gallery = (Gallery) findViewById(R.id.home_gallery);
		GridView gv = (GridView) findViewById(R.id.home_grid);
		gallery.setAdapter(new GalleryImageAdapter(this));
		gv.setAdapter(new HomeGridImageAdapter(this));

		gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View position,
					int arg2, long id) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();

				switch (position) {
				case 0:
					intent.setClass(ThreeActivity.this, FirstActivity.class);
					 startActivity(intent);
					break;
				case 1:
					 intent.setClass(ThreeActivity.this, BaseMapActivity.class);
					 startActivity(intent);
					 break;
				case 2:
					Toast.makeText(ThreeActivity.this, "" + position,
							Toast.LENGTH_SHORT).show();
					break;
				case 3:
					 intent.setClass(ThreeActivity.this, PoiSearchDemo.class);
					 startActivity(intent);
					 break;

				}
				// Intent intent = new Intent();
				// intent.setClass(ThreeActivity.this, FirstActivity.class);
				// startActivity(intent);
			}
		});
	}
}
