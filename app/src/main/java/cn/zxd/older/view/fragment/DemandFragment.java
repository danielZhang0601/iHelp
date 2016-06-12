package cn.zxd.older.view.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.zxd.older.R;


/**
 */

public class DemandFragment extends Fragment {

    @BindView(R.id.mv_map)
    MapView mv_map;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragement_demand, container, false);
        ButterKnife.bind(this, fragmentView);
        mv_map.onCreate(null);
        return fragmentView;
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
}
