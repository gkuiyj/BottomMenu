package net.weibo.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;

public class FirstActivity extends Activity {

	// 定义地图引擎管理类
	// private BMapManager mapManager;
	// 定义搜索服务类
	private MKSearch mMKSearch;
	private MapView mapView;
	private MapController mapController;

	private int load_Index = 0;
	private Button button;
	EditText search_text;

	private Button leftButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 设置自定义窗口标题样式
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		// mapManager = new BMapManager(getApplication());
		// mapManager.init("DB1bc13b8acaf90a331ec9f0fd43d52a", null);

		DemoApplication app = (DemoApplication) this.getApplication();
		if (app.mBMapManager == null) {
			app.mBMapManager = new BMapManager(this);
			app.mBMapManager.init(DemoApplication.strKey,
					new DemoApplication.MyGeneralListener());
		}
		setContentView(R.layout.one);

		mapView = (MapView) findViewById(R.id.one_mapView);
		mapView.setBuiltInZoomControls(true);

		mapController = mapView.getController();
		// GeoPoint point = new GeoPoint((int) (39.915 * 1E6),
		// (int) (116.404 * 1E6));
		// mapController.setCenter(point);
		mapController.enableClick(true);
		mapController.setZoom(12);

		MKSearch.setPoiPageCapacity(10);
		mMKSearch = new MKSearch();
		mMKSearch.init(app.mBMapManager, new MKSearchListener() {

			@Override
			public void onGetAddrResult(MKAddrInfo arg0, int arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetDrivingRouteResult(MKDrivingRouteResult arg0,
					int arg1) {
				// TODO Auto-generated method stub

			}

			// 处理详情页结果
			@Override
			public void onGetPoiDetailSearchResult(int type, int error) {
				if (error != 0) {
					Toast.makeText(FirstActivity.this, "抱歉，未找到结果",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(FirstActivity.this, "成功，查看详情页面",
							Toast.LENGTH_SHORT).show();
				}
			}

			// 处理POI搜索结果
			@Override
			public void onGetPoiResult(MKPoiResult res, int type, int error) {
				if (res == null) {
					return;
				}
				System.out.println("page:" + res.getPageIndex());
				if (load_Index < res.getNumPages()
						&& res.getCurrentNumPois() > 0) {
					mapView.getOverlays().clear();
					MyPoiOverlay overlay = new MyPoiOverlay(FirstActivity.this,
							mapView, mMKSearch);
					overlay.setData(res.getAllPoi());
					mapView.getOverlays().add(overlay);
					mapView.refresh();

					MKPoiInfo info = res.getPoi(0);
					mapController.setCenter(info.pt);

				} else {
					Toast.makeText(FirstActivity.this, "数据已加载完！",
							Toast.LENGTH_LONG).show();
					System.out
							.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				}

				// // 清除地图上已有的覆盖物
				// mapView.getOverlays().clear();
				//
				// // MyPoiOverlay继承于PoiOverlay，百度提供的用于显示POI的Overlay
				// MyPoiOverlay poioverlay = new
				// MyPoiOverlay(FirstActivity.this,
				// mapView, mMKSearch);
				// // 设置搜索到的POI数据
				// poioverlay.setData(res.getAllPoi());
				// // 在地图上显示POIOverlay（将搜索到的兴趣点标注到地图上）
				// mapView.getOverlays().add(poioverlay);
				// mapView.refresh();
				//
				// if (res.getNumPois() > 0) {
				// MKPoiInfo pinfo = res.getPoi(0);
				// mapController.setCenter(pinfo.pt);
				// }

				// strb.append("搜索到").append(res.getNumPois()).append("个POI\n");
				// for (MKPoiInfo info : res.getAllPoi()) {
				// strb.append("名称：").append(info.name).append("\n");
				// }

				// new AlertDialog.Builder(FirstActivity.this)
				// .setTitle("搜索到的poi信息")
				// .setMessage(strb.toString())
				// .setPositiveButton("关闭",
				// new DialogInterface.OnClickListener() {
				//
				// @Override
				// public void onClick(DialogInterface dialog,
				// int which) {
				// dialog.dismiss();
				// }
				// }).create().show();

			}

			@Override
			public void onGetShareUrlResult(MKShareUrlResult arg0, int arg1,
					int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetSuggestionResult(MKSuggestionResult res, int arg1) {

			}

			@Override
			public void onGetTransitRouteResult(MKTransitRouteResult arg0,
					int arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onGetWalkingRouteResult(MKWalkingRouteResult arg0,
					int arg1) {
				// TODO Auto-generated method stub

			}
		});

		// mMKSearch.poiSearchInCity("张家港","ATM");
		search_text = (EditText) findViewById(R.id.search_text);
		button = (Button) findViewById(R.id.search_button);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String text = search_text.getText().toString().trim();
				if (text == null || text.equals("")) {
					Toast.makeText(FirstActivity.this, "搜索关键字不能为空！",
							Toast.LENGTH_LONG).show();
				} else {
					mMKSearch.poiSearchInCity("张家港", text);

				}

			}
		});

		//设置自定义标题
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.titlebar);

		leftButton = (Button) findViewById(R.id.btn_left);
		leftButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FirstActivity.this.finish();
			}
		});
	}

	public void goToNextPage(View v) {
		// 搜索下一组poi
		int flag = mMKSearch.goToPoiPage(++load_Index);
		if (flag != 0) {
			Toast.makeText(FirstActivity.this, "先搜索开始，然后再搜索下一组数据",
					Toast.LENGTH_SHORT).show();
		}
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

//	protected Dialog onCreateDialog(int id) {
//		// 自定义对话框
//		AlertDialog.Builder builder = new Builder(FirstActivity.this);
//		// 设置标题
//		if (id == R.id.btn_left) {
//			builder.setTitle("返回···");
//		} else if (id == R.id.btn_right) {
//			builder.setTitle("主页···");
//		}
//		// 确定按钮的操作
//		builder.setPositiveButton("确认", new OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				// 销毁对话框
//				dialog.dismiss();
//				FirstActivity.this.finish();
//			}
//		});
//		// 取消按钮的操作
//		builder.setNegativeButton("取消", new OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//			}
//		});
//		return builder.create();
//	}

}
