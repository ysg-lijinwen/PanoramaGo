package com.jointem.dbhelper;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AreaCity {
    @Id
    private Long id;
    private String name;
    private String parentId;
    private String shortName;
    private double levelType;
    private String cityCode;
    private String zipCode;
    private String mergerName;
    private String lng;
    private String lat;
    private String pinyin;
    @Generated(hash = 24698192)
    public AreaCity(Long id, String name, String parentId, String shortName,
            double levelType, String cityCode, String zipCode, String mergerName,
            String lng, String lat, String pinyin) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.shortName = shortName;
        this.levelType = levelType;
        this.cityCode = cityCode;
        this.zipCode = zipCode;
        this.mergerName = mergerName;
        this.lng = lng;
        this.lat = lat;
        this.pinyin = pinyin;
    }
    @Generated(hash = 219952363)
    public AreaCity() {
    }

    /**
     * 获取字符串类型的id
     */
    public String getStringId(){
        return String.valueOf(id);
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getParentId() {
        return this.parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getShortName() {
        return this.shortName;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public double getLevelType() {
        return this.levelType;
    }
    public void setLevelType(double levelType) {
        this.levelType = levelType;
    }
    public String getCityCode() {
        return this.cityCode;
    }
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
    public String getZipCode() {
        return this.zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public String getMergerName() {
        return this.mergerName;
    }
    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }
    public String getLng() {
        return this.lng;
    }
    public void setLng(String lng) {
        this.lng = lng;
    }
    public String getLat() {
        return this.lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getPinyin() {
        return this.pinyin;
    }
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
}