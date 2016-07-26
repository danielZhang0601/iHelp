package cn.zxd.ihelp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.zxd.ihelp.model.SearchObject;
import cn.zxd.ihelp.view.util.ViewHolder;

/**
 * Created by danielzhang on 16/7/26.
 */

public class SearchAdapter extends BaseAdapter {

    private Context context;

    private List<SearchObject> objects;

    public SearchAdapter(Context context) {
        this.context = context;
        objects = new ArrayList<SearchObject>();
    }

    public void setData(List<SearchObject> objects) {
        this.objects.clear();
        this.objects.addAll(objects);
        notifyDataSetChanged();
    }

    public List<SearchObject> getObjects() {
        return objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.two_line_list_item, null, false);
        }
        TextView title = ViewHolder.get(convertView, android.R.id.text1);
        title.setText(objects.get(position).getTitle());
        TextView text = ViewHolder.get(convertView, android.R.id.text2);
        text.setText(objects.get(position).getText());
        return convertView;
    }
}
