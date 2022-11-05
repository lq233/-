package com.example.lq.myapplication.utils;

import com.example.lq.myapplication.app.MyApp;
import com.example.lq.myapplication.bean.DbBean;
import com.example.lq.myapplication.dao.DaoMaster;
import com.example.lq.myapplication.dao.DaoSession;
import com.example.lq.myapplication.dao.DbBeanDao;

import java.util.List;

/**
 * Created by 003 on 2019/5/9.
 */

public class DbUtils {
    public static DbUtils sDbUtils;
    private final DbBeanDao mDao;

    public DbUtils() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApp.getMyApp(), "Wa.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession session = daoMaster.newSession();
        mDao = session.getDbBeanDao();
    }

    public static DbUtils getDbUtils() {
        if (sDbUtils == null) {
            synchronized (DbUtils.class) {
                if (sDbUtils == null) {
                    sDbUtils = new DbUtils();
                }
            }
        }
        return sDbUtils;
    }

    public boolean has(DbBean bean) {
        List<DbBean> list = mDao.queryBuilder().where(DbBeanDao.Properties.Title.eq(bean.getTitle())).list();
        if (list.size() > 0 && list != null) {
            return true;
        }
        return false;
    }

    public void insert(DbBean bean) {
        if (!has(bean)) {
            mDao.insert(bean);
        }
    }

    public List<DbBean> query() {
        return mDao.queryBuilder().list();
    }

    public void delete(DbBean bean) {
        if (has(bean)) {
            mDao.delete(bean);
        }
    }
}
