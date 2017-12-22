package com.jointem.dbhelper;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wuht on 2017/2/13.
 */
@Entity
public class StationsHistory {
    @Id(autoincrement = true)
    private Long id;
    public String stationRound;

    @Generated(hash = 1377383609)
    public StationsHistory(Long id, String stationRound) {
        this.id = id;
        this.stationRound = stationRound;
    }

    @Generated(hash = 1493543583)
    public StationsHistory() {
    }

    public Long getId() {
        return id;
    }

    public StationsHistory setId(Long id) {
        this.id = id;
        return this;
    }

    public String getStationRound() {
        return stationRound;
    }

    public StationsHistory setStationRound(String stationRound) {
        this.stationRound = stationRound;
        return this;
    }
}
