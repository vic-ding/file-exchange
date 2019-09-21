package com.springboot.weking.app.gx.model;

import com.springboot.weking.app.sys.model.ExtendEntity;

import java.util.Date;

public class GxFileSendLog extends ExtendEntity {

    private String sndLsh;

    private String sndCode;

    private String rcvCode;

    private String sndType;

    private String sndStatus;

    private String sndFilename;

    private Date sndTime;

    private Date endTime;

    private Integer sndTotal;

    private Integer sucNum;

    private Integer errNum;

    private String errMessage;

    private Date modiTime;

    public String getSndLsh() {
        return sndLsh;
    }

    public void setSndLsh(String sndLsh) {
        this.sndLsh = sndLsh;
    }

    public String getSndCode() {
        return sndCode;
    }

    public void setSndCode(String sndCode) {
        this.sndCode = sndCode;
    }

    public String getRcvCode() {
        return rcvCode;
    }

    public void setRcvCode(String rcvCode) {
        this.rcvCode = rcvCode;
    }

    public String getSndType() {
        return sndType;
    }

    public void setSndType(String sndType) {
        this.sndType = sndType;
    }

    public String getSndStatus() {
        return sndStatus;
    }

    public void setSndStatus(String sndStatus) {
        this.sndStatus = sndStatus;
    }

    public String getSndFilename() {
        return sndFilename;
    }

    public void setSndFilename(String sndFilename) {
        this.sndFilename = sndFilename;
    }

    public Date getSndTime() {
        return sndTime;
    }

    public void setSndTime(Date sndTime) {
        this.sndTime = sndTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getSndTotal() {
        return sndTotal;
    }

    public void setSndTotal(Integer sndTotal) {
        this.sndTotal = sndTotal;
    }

    public Integer getSucNum() {
        return sucNum;
    }

    public void setSucNum(Integer sucNum) {
        this.sucNum = sucNum;
    }

    public Integer getErrNum() {
        return errNum;
    }

    public void setErrNum(Integer errNum) {
        this.errNum = errNum;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public Date getModiTime() {
        return modiTime;
    }

    public void setModiTime(Date modiTime) {
        this.modiTime = modiTime;
    }
}