package com.jointem.dbhelper;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * @author Kevin.Li
 * @ClassName: Site
 * @Description: 微站实体
 * @date 2015-10-22 上午11:21:45
 */
@Entity
public class Site implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String name;
    private String type;
    private String logo;
    private String website;
    private double longitude;// 经度
    private double latitude; // 纬度
    private String categoryName;
    private String categoryCode;
    private String phone;
    private String address;
    private String pv;
    private String star;
    private String follow;
    private boolean isdefault;// 是否被设为默认
    private String distance;// 距离
    private long timestamp;// 时间戳（模式设置时使用）

    private String refusePushState;

    @Generated(hash = 1225382409)
    public Site(String id, String name, String type, String logo, String website,
                double longitude, double latitude, String categoryName,
                String categoryCode, String phone, String address, String pv,
                String star, String follow, boolean isdefault, String distance,
                long timestamp, String refusePushState) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.logo = logo;
        this.website = website;
        this.longitude = longitude;
        this.latitude = latitude;
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
        this.phone = phone;
        this.address = address;
        this.pv = pv;
        this.star = star;
        this.follow = follow;
        this.isdefault = isdefault;
        this.distance = distance;
        this.timestamp = timestamp;
        this.refusePushState = refusePushState;
    }

    @Generated(hash = 1136322986)
    public Site() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public boolean isIsdefault() {
        return isdefault;
    }

    public void setIsdefault(boolean isdefault) {
        this.isdefault = isdefault;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getRefusePushState() {
        return refusePushState;
    }

    public void setRefusePushState(String refusePushState) {
        this.refusePushState = refusePushState;
    }

    public boolean getIsdefault() {
        return this.isdefault;
    }

    public String getLogo() {
        return logo;
    }
}
