package com.jointem.dbhelper.areacity;

import android.content.Context;
import android.util.Log;

import com.jointem.dbhelper.AreaCity;
import com.jointem.dbhelper.AreaCityDao;
import com.jointem.dbhelper.GreenDaoManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理城市数据的工具类
 */
public class DBAreaUtil {
    private AreaCityDao areaCityDao;


    /**
     * 私有化构造方法，并构造出{@link AreaCityDao}对象
     *
     * @param context 最好传入applicationContext，保证{@link com.jointem.dbhelper.DaoMaster}的生命周期
     */
    private DBAreaUtil(Context context) {
        areaCityDao = GreenDaoManager.getInstance(context).getNewSession().getAreaCityDao();
    }


    /**
     * 静态内部类，该内部类的实例只有被调用时才会装载，从而实现延迟加载
     */
    private static class DBAreaHolder {
        private static DBAreaUtil instance;

        private static DBAreaUtil getInstance(Context context) {
            if (instance == null) {
                //有JVM来保证线程安全
                instance = new DBAreaUtil(context);
            }
            return instance;
        }
    }


    /**
     * 通过单例来调用该类的实例方法
     *
     * @param context 最好传入applicationContext，保证{@link com.jointem.dbhelper.DaoMaster}的生命周期
     * @return 类的具体实例
     */
    public static DBAreaUtil getInstance(Context context) {
        return DBAreaHolder.getInstance(context);
    }


    //************************************具体操作的逻辑和方法*********************************************/

    /**
     * 根据名字来查找对应的id
     * 有可能会出现查找不到的问题和由于城市同名查到多个的问题。
     *
     * @param cityName 城市名称
     * @return 530402
     */
    public String findRegionIdByCityName(String cityName) {
        AreaCity areaCity;
        try {
            areaCity = areaCityDao.queryBuilder()
                    .where(AreaCityDao.Properties.Name.eq(cityName)).uniqueOrThrow();
        } catch (Exception e) {
            Log.e("---DBAreaUtil---", "查询AreaCity异常");
            return "";
        }
        return areaCity.getStringId();
    }

    public AreaCity findAreaCityByCityName(String cityName) {
        AreaCity areaCity;
        try {
            areaCity = areaCityDao.queryBuilder()
                    .where(AreaCityDao.Properties.Name.eq(cityName)).unique();
        } catch (Exception e) {
            Log.e("---DBAreaUtil---", "查询AreaCity异常");
            return null;
        }
        return areaCity;
    }


    /**
     * 根据id来查找对应的城市
     *
     * @param id 唯一标识
     * @return AreaCity实体，需要什么属性自己取
     */
    public AreaCity findAreaCityById(String id) {
        AreaCity areaCity;
        try {
            areaCity = areaCityDao.queryBuilder()
                    .where(AreaCityDao.Properties.Id.eq(id)).uniqueOrThrow();
        } catch (Exception e) {
            Log.e("---DBAreaUtil---", "查询AreaCity异常");
            return null;
        }
        return areaCity;
    }

    /**
     * 获取数据表中所有的省
     *
     * @return 所有省份的table{云南省，北京市，重庆市，河南省...}
     */
    public List<AreaCity> getAllProvinces() {
        List<AreaCity> areaCityList;
        try {
            areaCityList = areaCityDao.queryBuilder()
                    .where(AreaCityDao.Properties.LevelType.eq("1")).list();
        } catch (Exception e) {
            Log.e("---DBAreaUtil---", "查询AreaCity异常");
            return new ArrayList<>();
        }
        return areaCityList;
    }

    /**
     * 根据id获取其直接下一级所有的地区列表
     *
     * @param areaId 玉溪市
     * @return {红塔区，华宁....}
     */
    public List<AreaCity> getChildAreaById(String areaId) {
        List<AreaCity> areaCityList;
        try {
            areaCityList = areaCityDao.queryBuilder()
                    .where(AreaCityDao.Properties.ParentId.eq(areaId)).list();
        } catch (Exception e) {
            Log.e("---DBAreaUtil---", "查询AreaCity异常");
            return new ArrayList<>();
        }
        return areaCityList;
    }

}
