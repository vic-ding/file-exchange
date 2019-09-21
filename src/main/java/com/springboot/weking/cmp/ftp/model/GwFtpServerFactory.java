package com.springboot.weking.cmp.ftp.model;

import org.apache.ftpserver.ConnectionConfig;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.command.CommandFactory;
import org.apache.ftpserver.ftplet.FileSystemFactory;
import org.apache.ftpserver.ftplet.Ftplet;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.ftpletcontainer.impl.DefaultFtpletContainer;
import org.apache.ftpserver.impl.DefaultFtpServer;
import org.apache.ftpserver.impl.DefaultFtpServerContext;
import org.apache.ftpserver.listener.Listener;
import org.apache.ftpserver.message.MessageResource;

import java.util.Map;


public class GwFtpServerFactory extends FtpServerFactory {

    public DefaultFtpServerContext serverContext = new DefaultFtpServerContext();

    public GwFtpServerFactory() {
    }

    public FtpServer createServer() {
        return new DefaultFtpServer(this.serverContext);
    }

    public Map<String, Listener> getListeners() {
        return this.serverContext.getListeners();
    }

    public Listener getListener(String name) {
        return this.serverContext.getListener(name);
    }

    public void addListener(String name, Listener listener) {
        this.serverContext.addListener(name, listener);
    }

    public void setListeners(Map<String, Listener> listeners) {
        this.serverContext.setListeners(listeners);
    }

    public Map<String, Ftplet> getFtplets() {
        return this.serverContext.getFtpletContainer().getFtplets();
    }

    public void setFtplets(Map<String, Ftplet> ftplets) {
        this.serverContext.setFtpletContainer(new DefaultFtpletContainer(ftplets));
    }

    public UserManager getUserManager() {
        return this.serverContext.getUserManager();
    }

    public void setUserManager(UserManager userManager) {
        this.serverContext.setUserManager(userManager);
    }

    public FileSystemFactory getFileSystem() {
        return this.serverContext.getFileSystemManager();
    }

    public void setFileSystem(FileSystemFactory fileSystem) {
        this.serverContext.setFileSystemManager(fileSystem);
    }

    public CommandFactory getCommandFactory() {
        return this.serverContext.getCommandFactory();
    }

    public void setCommandFactory(CommandFactory commandFactory) {
        this.serverContext.setCommandFactory(commandFactory);
    }

    public MessageResource getMessageResource() {
        return this.serverContext.getMessageResource();
    }

    public void setMessageResource(MessageResource messageResource) {
        this.serverContext.setMessageResource(messageResource);
    }

    public ConnectionConfig getConnectionConfig() {
        return this.serverContext.getConnectionConfig();
    }

    public void setConnectionConfig(ConnectionConfig connectionConfig) {
        this.serverContext.setConnectionConfig(connectionConfig);
    }

}
