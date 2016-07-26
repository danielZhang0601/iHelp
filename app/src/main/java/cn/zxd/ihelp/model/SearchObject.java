package cn.zxd.ihelp.model;

import java.io.Serializable;

/**
 * Created by danielzhang on 16/7/26.
 */

public class SearchObject implements Serializable {

    private double longitude;//经度
    private double latitude;//纬度
    private String title;//信息标题
    private String text;//信息内容

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
