package cn.zxd.older.view.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zxd.older.R;
import cn.zxd.older.view.activity.SearchActivity;


/**
 */

public class DemandFragment extends Fragment implements AMapLocationListener, LocationSource, AMap.OnCameraChangeListener, AMap.OnMapTouchListener, AMap.OnMapClickListener, AMap.OnMapLongClickListener {

    public static final int DEFAULT_ZOOM_SIZE = 15;
    public static final int REQUEST_SEARCH_ACTIVITY = 100;

    @BindView(R.id.mv_map)
    MapView mv_map;

    @BindView(R.id.tv_dest_location)
    TextView tv_dest_location;

    @BindView(R.id.tv_now_demand)
    TextView tv_now_demand;

    AMapLocationClient aMapLocationClient;


    OnLocationChangedListener locationChangedListener = new OnLocationChangedListener() {
        @Override
        public void onLocationChanged(Location location) {
            //添加Marker
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragement_demand, container, false);
        ButterKnife.bind(this, fragmentView);
        mv_map.onCreate(null);
        initLocation();
        initListener();
        return fragmentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startLocate();
    }

    @Override
    public void onPause() {
        super.onPause();
        mv_map.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mv_map.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mv_map.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mv_map.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SEARCH_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                if (null != data) {

                }
            }
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (null != aMapLocation && 0 == aMapLocation.getErrorCode()) {
            stopLocate();
            locationChangedListener.onLocationChanged(aMapLocation);
            tv_dest_location.setText(aMapLocation.getAddress());
            LatLng mLatLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
            if (null != mv_map && null != mv_map.getMap())
                mv_map.getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, DEFAULT_ZOOM_SIZE));
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        startLocate();
    }

    @Override
    public void deactivate() {
        stopLocate();
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        //逆地理信息反查
        GeocodeSearch geocodeSearch = new GeocodeSearch(getActivity());
        geocodeSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
            @Override
            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
                tv_dest_location.setText(regeocodeResult.getRegeocodeAddress().getFormatAddress());
            }

            @Override
            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

            }
        });
        LatLonPoint point = new LatLonPoint(cameraPosition.target.latitude, cameraPosition.target.longitude);
        RegeocodeQuery query = new RegeocodeQuery(point, 50, GeocodeSearch.AMAP);
        geocodeSearch.getFromLocationAsyn(query);
        //向服务器请求数据
        String resultStr = String.format("附近有%d位陪护人员", (int)(Math.random() * 100));
        tv_now_demand.setText(resultStr);
        //TODO 根据服务器返回数据添加地图mark点
    }

    @Override
    public void onTouch(MotionEvent motionEvent) {

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @OnClick(R.id.lv_search)
    protected void searchClick() {
        SearchActivity.launch(getActivity(), null, REQUEST_SEARCH_ACTIVITY);
    }

    void initLocation() {
        mv_map.getMap().setLocationSource(this);
        mv_map.getMap().getUiSettings().setMyLocationButtonEnabled(false);
        mv_map.getMap().setMyLocationEnabled(true);
    }

    void initListener() {
        mv_map.getMap().setOnCameraChangeListener(this);
        mv_map.getMap().setOnMapTouchListener(this);
        mv_map.getMap().setOnMapClickListener(this);
        mv_map.getMap().setOnMapLongClickListener(this);
    }

    void startLocate() {
        if (null == aMapLocationClient) {
            aMapLocationClient = new AMapLocationClient(getActivity());
            aMapLocationClient.setLocationListener(this);
        }
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);

        aMapLocationClient.setLocationOption(option);
        aMapLocationClient.startLocation();
    }

    void stopLocate() {
        if (null != aMapLocationClient) {
            aMapLocationClient.stopLocation();
            aMapLocationClient.onDestroy();
            aMapLocationClient = null;
        }
    }


}
