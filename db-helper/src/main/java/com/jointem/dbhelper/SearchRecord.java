package com.jointem.dbhelper;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Kevin.Li
 * @ClassName: SearchRecord
 * @Description: 搜索记录实体
 * @date 2015-10-22 下午4:12:24
 */
@Entity
public class SearchRecord {
    @Id(autoincrement = true)
    private Long id;
    private String key;
    private long date;
    //    @Id(column = "type")
    private int type;//0——搜索  1——政务指南搜索 2--新闻资讯搜索
    @Generated(hash = 1963989225)
    public SearchRecord(Long id, String key, long date, int type) {
        this.id = id;
        this.key = key;
        this.date = date;
        this.type = type;
    }
    @Generated(hash = 839789598)
    public SearchRecord() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getKey() {
        return this.key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public long getDate() {
        return this.date;
    }
    public void setDate(long date) {
        this.date = date;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }

}
