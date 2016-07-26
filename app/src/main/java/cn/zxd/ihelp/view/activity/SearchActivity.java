package cn.zxd.ihelp.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.zxd.ihelp.R;
import cn.zxd.ihelp.model.SearchObject;
import cn.zxd.ihelp.view.adapter.SearchAdapter;

public class SearchActivity extends BaseActivity implements TextWatcher, PoiSearch.OnPoiSearchListener, AdapterView.OnItemClickListener {

    @BindView(R.id.tv_title_center)
    TextView tv_title_center;

    @BindView(R.id.et_search)
    EditText et_search;

    @BindView(R.id.lv_search)
    ListView lv_search;

    private SearchAdapter searchAdapter;

    public static void launchForResult(Activity activity, Bundle bundle, int requestCode) {
        Intent intent = new Intent(activity, SearchActivity.class);
        if (null != bundle) intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initViews();
    }

    @OnClick(R.id.iv_title_left)
    void backClick() {
        onBackPressed();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        asyncSearch(s.toString(), "");
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int i) {
        if (1000 == i) {
            ArrayList<SearchObject> searchResults = new ArrayList<SearchObject>();
            for (PoiItem item : poiResult.getPois()) {
                SearchObject searchResult = new SearchObject();
                searchResult.setLatitude(item.getLatLonPoint().getLatitude());
                searchResult.setLongitude(item.getLatLonPoint().getLongitude());
                searchResult.setTitle(item.getTitle());
                searchResult.setText(item.getSnippet());
                searchResults.add(searchResult);
            }
            searchAdapter.setData(searchResults);
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {
    }

    void initViews() {
        tv_title_center.setText("搜索");
        et_search.addTextChangedListener(this);
        searchAdapter = new SearchAdapter(this);
        lv_search.setAdapter(searchAdapter);
        lv_search.setOnItemClickListener(this);
    }

    void asyncSearch(String key, String city) {
        //POI搜索条件
        PoiSearch.Query query = new PoiSearch.Query(key, "", city);
        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);
        poiSearch.searchPOIAsyn();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        et_search.setText(searchAdapter.getObjects().get(position).getTitle());
        Intent data = new Intent();
        data.putExtra("data", searchAdapter.getObjects().get(position));
        setResult(RESULT_OK, data);
        finish();
    }
}
