package com.zhu.base.dto;

/**
 * copyright    <a href="http://www.qaqavr.com/">中锐</a>
 * <pre>
 *     @author      zwy
 *     @date        2019/3/1 11:16
 *     email        1092478224@qq.com
 *     desc         学生基本信息及学生报名信息
 * </pre>
 */
public class StuAndStuEnter {

    //主键id
    private Integer id;
    //学员编号（多个编号）
    private String stunumber;
    //身份证号
    private String sfzh;
    //学生姓名
    private String stuname;
    //学生性别（0：男、1：女）
    private String sex;
    //年龄
    private Integer age;
    //班级id
    private String classid;
    //班级名称
    private String classname;
    //民族
    private String nation;
    //联系方式
    private String phone;
    //紧急联系人方式
    private String emergency;
    //身份证地址
    private String sfzaddress;
    //现地址
    private String address;
    //汽车牌照号
    private String carnumber;
    //照片地址
    private String photo;
    //状态(0:启用、1：停用)
    private String status;
    //创建时间
    private String crttime;
    //修改时间
    private String updatetime;
    //入学年限
    private String entertime;
    //毕业年限
    private String graduatetime;
    //班主任姓名
    private String headMasterName ;
    //班主任id
    private Integer headMasterId ;
    //专业名称
    private String majorName ;
    //分类名称
    private String cateName ;
    //是否有学生证(0:有、1：无)
    private String iscard;
    //报名时间
    private String signtime;
    //教师id
    private Integer tid;
    //教师姓名
    private String tname;
    //发票号
    private String invoicenumber;
    //类别id
    private String cateid;
    //类别名称
    private String catename;
    //专业id
    private String majorid;
    //专业名称
    private String majorname;
    //报名费
    private String fee;
    private Integer termId ;
    private String termName ;

    public String getIscard() {
        return iscard;
    }

    public void setIscard(String iscard) {
        this.iscard = iscard == null ? null : iscard.trim();
    }

    public String getSigntime() {
        return signtime;
    }

    public void setSigntime(String signtime) {
        this.signtime = signtime;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStunumber() {
        return stunumber;
    }

    public void setStunumber(String stunumber) {
        this.stunumber = stunumber == null ? null : stunumber.trim();
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh == null ? null : sfzh.trim();
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname == null ? null : stuname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency == null ? null : emergency.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber == null ? null : carnumber.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCrttime() {
        return crttime;
    }

    public void setCrttime(String crttime) {
        this.crttime = crttime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSfzaddress() {
        return sfzaddress;
    }

    public void setSfzaddress(String sfzaddress) {
        this.sfzaddress = sfzaddress;
    }

    public String getEntertime() {
        return entertime;
    }

    public void setEntertime(String entertime) {
        this.entertime = entertime;
    }

    public String getGraduatetime() {
        return graduatetime;
    }

    public void setGraduatetime(String graduatetime) {
        this.graduatetime = graduatetime;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getHeadMasterName() {
        return headMasterName;
    }

    public void setHeadMasterName(String headMasterName) {
        this.headMasterName = headMasterName;
    }

    public Integer getHeadMasterId() {
        return headMasterId;
    }

    public void setHeadMasterId(Integer headMasterId) {
        this.headMasterId = headMasterId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
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

    public String getInvoicenumber() {
        return invoicenumber;
    }

    public void setInvoicenumber(String invoicenumber) {
        this.invoicenumber = invoicenumber;
    }

    public String getCateid() {
        return cateid;
    }

    public void setCateid(String cateid) {
        this.cateid = cateid;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

    public String getMajorid() {
        return majorid;
    }

    public void setMajorid(String majorid) {
        this.majorid = majorid;
    }

    public String getMajorname() {
        return majorname;
    }

    public void setMajorname(String majorname) {
        this.majorname = majorname;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public Integer getTermId() {
        return termId;
    }

    public void setTermId(Integer termId) {
        this.termId = termId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }
}
