package cn.zxd.older.view.util;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by danielzhang on 16/6/17.
 */

public class ViewHolder {

    private ViewHolder() {
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (null == viewHolder) {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (null == childView) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }

}
