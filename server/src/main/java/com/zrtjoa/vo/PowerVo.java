package com.zrtjoa.vo;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2019/2/18 17:37
 *     email        1092478224@qq.com
 *     desc         权限VO
 * </pre>
 */
public class PowerVo {
    private Integer id ;
    private Integer pId ;
    private String name ;
    private Boolean open ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
