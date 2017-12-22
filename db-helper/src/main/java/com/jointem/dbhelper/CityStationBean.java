package com.jointem.dbhelper;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wuht on 2017/4/20.
 */
@Entity
public class CityStationBean {
    // @Id(column = "id")
    //private int id;
    private String code;//城市的拼音简写
    private String isCanOrder;//该车站是否可以预定
    private String name;//城市名称
    //用于搜索
    private String pinyin;
    private String nameFullPinyin;//城市拼音

    public CityStationBean() {///////数据库的时候要写一下默认的这个
    }



    public CityStationBean(String code, String name) {
        this.code = code;
        this.name = name;
    }



    @Generated(hash = 765280063)
    public CityStationBean(String code, String isCanOrder, String name,
            String pinyin, String nameFullPinyin) {
        this.code = code;
        this.isCanOrder = isCanOrder;
        this.name = name;
        this.pinyin = pinyin;
        this.nameFullPinyin = nameFullPinyin;
    }

    /**
     * 返回拼音首字母
     */
    public String getTag() {
        return code.substring(0, 1);
    }

    public CityStationBean setName(String name) {
        this.name = name;
        return this;
    }



    public String getCode() {
        return this.code;
    }



    public void setCode(String code) {
        this.code = code;
    }



    public String getIsCanOrder() {
        return this.isCanOrder;
    }



    public void setIsCanOrder(String isCanOrder) {
        this.isCanOrder = isCanOrder;
    }



    public String getName() {
        return this.name;
    }



    public String getPinyin() {
        return this.pinyin;
    }



    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }



    public String getNameFullPinyin() {
        return this.nameFullPinyin;
    }



    public void setNameFullPinyin(String nameFullPinyin) {
        this.nameFullPinyin = nameFullPinyin;
    }
}

