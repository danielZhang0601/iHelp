package cn.zxd.older.model;


import cn.bmob.v3.BmobUser;

/**
 */

public class User extends BmobUser {
    private boolean login = false;

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
