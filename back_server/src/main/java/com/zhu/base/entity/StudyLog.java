package com.zhu.base.entity;

import java.io.Serializable;

/**
 * 学委会日志
 * @author yangli
 * @date 2018/12/25
 */
public class StudyLog implements Serializable {
    //主键id
    private Integer id;
    //标题
    private String title;
    //内容
    private String content;
    //开始时间
    private String starttime;
    //创建时间
    private String crttime;
    //填写人id
    private Integer tid;
    //填写人姓名
    private String tname;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
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
        this.tname = tname;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }
}
