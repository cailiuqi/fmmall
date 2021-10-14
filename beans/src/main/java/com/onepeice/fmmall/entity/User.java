package com.onepeice.fmmall.entity;

import javax.persistence.*;

public class User {
    @Id
    @Column(name = "userId")
    private Integer userid;

    @Column(name = "userName")
    private String username;


    @Column(name = "userRealname")
    private String userrealname;

    @Column(name = "userImg")
    private String userimg;

    @Column(name = "userPwd")
    private String userpwd;

    /**
     * @return userId
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return userRealname
     */
    public String getUserrealname() {
        return userrealname;
    }

    /**
     * @param userrealname
     */
    public void setUserrealname(String userrealname) {
        this.userrealname = userrealname;
    }

    /**
     * @return userImg
     */
    public String getUserimg() {
        return userimg;
    }

    /**
     * @param userimg
     */
    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    /**
     * @return userPwd
     */
    public String getUserpwd() {
        return userpwd;
    }

    /**
     * @param userpwd
     */
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
}