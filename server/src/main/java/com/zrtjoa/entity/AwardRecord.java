package com.zrtjoa.entity;

import java.io.Serializable;

/**
 * 日志管理-获奖情况记录表
 * @author yangli
 * @date 2018/12/25
 */
public class AwardRecord implements Serializable {
    //主键id
    private Integer id;
    //填写人id
    private Integer tid;
    //填写人姓名
    private String tname;
    //标题
    private String title;
    //内容
    private String content;
    //记录时间
    private String recordtime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname == null ? null : tname.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime;
    }
}