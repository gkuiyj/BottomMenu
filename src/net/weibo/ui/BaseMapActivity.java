package net.weibo.ui;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class BaseMapActivity extends Activity {

	private Button leftButton;
	//BMapManager bMapManager = null;
	MapView mapView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//初始化地图管理对象，必须在setContentView（）之前
		//bMapManager = new BMapManager(getApplication());	
		//bMapManager.init("DB1bc13b8acaf90a331ec9f0fd43d52a", null);
		DemoApplication app = (DemoApplication)this.getApplication();
        if (app.mBMapManager == null) {
            app.mBMapManager = new BMapManager(this);
            app.mBMapManager.init(DemoApplication.strKey,new DemoApplication.MyGeneralListener());
        }

     // 设置自定义窗口标题样式
     		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
     		
		setContentView(R.layout.basemap);

		//设置自定义窗口标题、按钮
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);

		leftButton = (Button) findViewById(R.id.btn_left);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				BaseMapActivity.this.finish();
			}
		});
		
		//初始化mapView对象，并设置显示缩放控件
		mapView = (MapView) findViewById(R.id.basemapView);  
		mapView.setBuiltInZoomControls(true);	

		//定义地图控件，获取mapView的控制，设定地图范围
		MapController mapController = mapView.getController();
		GeoPoint point = new GeoPoint((int) (39.915 * 1E6),
				(int) (116.404 * 1E6));
		mapController.setCenter(point);
		mapController.setZoom(12);
	}

	@Override
	protected void onDestroy() {
		mapView.destroy();

		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mapView.onPause();

		super.onPause();
	}

	@Override
	protected void onResume() {
		mapView.onResume();

		super.onResume();
	}

}
