package com.springboot.weking.app.gx.model;

import java.util.Date;

public class GxNode {

    private String dbCode;

    private String nodeCode;

    private String nodeDefinition;

    private String nodeType;

    private String nodeUrl;

    private String nodeStatus;

    private String nodeUser;

    private String nodeMail;

    private String nodeDesc;

    private String gxNodeIP;

    private String gxNodePort;

    private Date tm;

    //  校验网络连接状态
    private int webStatus;

    public int getWebStatus() {
        return webStatus;
    }

    public void setWebStatus(int webStatus) {
        this.webStatus = webStatus;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeDefinition() {
        return nodeDefinition;
    }

    public void setNodeDefinition(String nodeDefinition) {
        this.nodeDefinition = nodeDefinition;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeUrl() {
        return nodeUrl;
    }

    public void setNodeUrl(String nodeUrl) {
        this.nodeUrl = nodeUrl;
    }

    public String getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public String getNodeUser() {
        return nodeUser;
    }

    public void setNodeUser(String nodeUser) {
        this.nodeUser = nodeUser;
    }

    public String getNodeMail() {
        return nodeMail;
    }

    public void setNodeMail(String nodeMail) {
        this.nodeMail = nodeMail;
    }

    public String getNodeDesc() {
        return nodeDesc;
    }

    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }

    public String getGxNodeIP() {
        return gxNodeIP;
    }

    public void setGxNodeIP(String gxNodeIP) {
        this.gxNodeIP = gxNodeIP;
    }

    public String getGxNodePort() {
        return gxNodePort;
    }

    public void setGxNodePort(String gxNodePort) {
        this.gxNodePort = gxNodePort;
    }

    public String getDbCode() {
        return dbCode;
    }

    public void setDbCode(String dbCode) {
        this.dbCode = dbCode;
    }

    public Date getTm() {
        return tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }
}