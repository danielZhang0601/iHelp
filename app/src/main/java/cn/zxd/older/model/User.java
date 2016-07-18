package cn.zxd.older.model;


import cn.bmob.v3.BmobUser;

/**
 */

public class User extends BmobUser {

    public static final String USER_MOBILE = "UserMobile";

    private boolean isSupply = false;

    private long latitude;

    private long longitude;

    public boolean isSupply() {
        return isSupply;
    }

    public void setSupply(boolean supply) {
        isSupply = supply;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
