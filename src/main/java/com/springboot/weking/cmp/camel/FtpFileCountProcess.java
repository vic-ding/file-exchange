package com.springboot.weking.cmp.camel;

import cn.com.goldenwater.id.util.UuidUtil;
import com.springboot.weking.app.gx.dao.GxFileSendLogDao;
import com.springboot.weking.app.gx.model.GxFileSendLog;
import com.springboot.weking.utils.SpringContextUtils;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFileMessage;

import java.io.RandomAccessFile;
import java.util.Date;

/**
 * 文件处理器,本文件处理器仅用于对文件进行日志记录。
 */
public class FtpFileCountProcess implements Processor {

    private String fromNode;
    private String toNode;

    public FtpFileCountProcess(String from, String to) {
        this.fromNode = from;
        this.toNode = to;
    }

    @Override
    public void process(Exchange exchange) {
        GenericFileMessage<RandomAccessFile> inFileMessage = (GenericFileMessage<RandomAccessFile>) exchange.getIn();
        String fileName = inFileMessage.getGenericFile().getFileName();

        insert(fileName);
        System.out.println("文件发送记录>> 发送节点" + fromNode + "，接收节点 " + toNode + ",发送文件" + fileName + ",routeId=" + exchange.getFromRouteId());

    }


    public void insert(String fileName) {
        GxFileSendLog gxFileSendLog = new GxFileSendLog();
        gxFileSendLog.setSndLsh(UuidUtil.uuid());
        gxFileSendLog.setSndCode(fromNode);
        gxFileSendLog.setRcvCode(toNode);
        gxFileSendLog.setSndFilename(fileName);
        gxFileSendLog.setSndTime(new Date());

        GxFileSendLogDao gxFileSendLogDao = (GxFileSendLogDao) SpringContextUtils.getBeanById("gxFileSendLogDao");
        if (gxFileSendLogDao == null) {
            System.out.println("gxFileSendLogDao为空");
        } else {
            gxFileSendLog.setSndStatus("1");
            gxFileSendLogDao.insert(gxFileSendLog);
            System.out.println("文件发送记录成功记录到数据库！");
        }
    }

}
