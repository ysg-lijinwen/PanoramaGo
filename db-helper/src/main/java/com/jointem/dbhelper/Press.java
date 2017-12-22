package com.jointem.dbhelper;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author Kevin.Li
 * @ClassName: Press
 * @Description: 动态实体
 * @date 2015-10-22 上午11:25:47
 */

@Entity
public class Press {
    @Id(autoincrement = true)
    private Long myid;

    private String id;//id 是后台返回的
    private String messageTitle;
    private String siteName;
    private String messageLink;
    private String createDate;
    private String messageImage;
    private String siteId;
    private boolean state;
    private long timestamp;

    @Generated(hash = 727098678)
    public Press(Long myid, String id, String messageTitle, String siteName,
            String messageLink, String createDate, String messageImage,
            String siteId, boolean state, long timestamp) {
        this.myid = myid;
        this.id = id;
        this.messageTitle = messageTitle;
        this.siteName = siteName;
        this.messageLink = messageLink;
        this.createDate = createDate;
        this.messageImage = messageImage;
        this.siteId = siteId;
        this.state = state;
        this.timestamp = timestamp;
    }

    @Generated(hash = 1679681320)
    public Press() {
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getMessageLink() {
        return messageLink;
    }

    public void setMessageLink(String messageLink) {
        this.messageLink = messageLink;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMessageImage() {
//        if(TextUtils.isEmpty(messageImage)){
//            return null;
//        }
//        if (messageImage.startsWith("http://")) {
//            return messageImage;
//        } else {
//            return "http://gov.jointem.com" + messageImage;
////            return "http://172.31.60.188" + messageImage;
//        }
        return messageImage;

    }

    public void setMessageImage(String messageImage) {
        this.messageImage = messageImage;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }



    public boolean getState() {
        return this.state;
    }

    public Long getMyid() {
        return this.myid;
    }

    public void setMyid(Long myid) {
        this.myid = myid;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
