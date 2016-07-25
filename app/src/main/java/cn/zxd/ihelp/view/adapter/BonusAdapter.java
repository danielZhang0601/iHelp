package cn.zxd.ihelp.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.zxd.ihelp.R;
import cn.zxd.ihelp.model.Bonus;

/**
 * Created by danielzhang on 16/7/22.
 */

public class BonusAdapter extends BaseAdapter {

    private Context mContext;
    private List<Bonus> bonus;

    public BonusAdapter(Context context) {
        this.mContext = context;
    }

    public BonusAdapter(Context mContext, List<Bonus> bonus) {
        this.mContext = mContext;
        this.bonus = bonus;
    }

    public void setBonus(List<Bonus> bonus) {
        this.bonus.clear();
        this.bonus.addAll(bonus);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object getItem(int position) {
        return bonus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_bonus, parent, false);
        }




        return convertView;
    }
}
