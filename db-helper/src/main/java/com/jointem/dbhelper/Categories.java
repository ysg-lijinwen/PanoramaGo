package com.jointem.dbhelper;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;

/**
 * 获取分类的实体
 */
@Entity
public class Categories {
    private int id;
    private String categoryName;
    private String categoryCode;
    private String parentCode;

    @Transient
    private ArrayList<Categories> sub;

    @Transient
    private boolean isSelected;//是否被选中

    public Categories() {

    }

    public Categories(String categoryCode, String categoryName, String parentCode, ArrayList<Categories> sub) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.parentCode = parentCode;
        this.sub = sub;
    }

    @Generated(hash = 334792884)
    public Categories(int id, String categoryName, String categoryCode, String parentCode) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
        this.parentCode = parentCode;
    }


    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<Categories> getSub() {
        return sub;
    }

    public void setSub(ArrayList<Categories> sub) {
        this.sub = sub;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
