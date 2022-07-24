package com.industrialscansystem.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

/**
 * @Author 宋宗垚
 * @Date 2019/8/6 16:28
 * @Description TODO
 */
//@Component
//@PropertySource("classpath:application.properties")
public class EnvironmentPath {

    private static volatile EnvironmentPath instance;

    private String aiIP;//ai检测时候要找到ai服务器的ip

    public String getAiIP() {
        return aiIP;
    }

    public void setAiIP(String aiIP) {
        this.aiIP = aiIP;
    }

    //保存上传后图片的，存放图片的  tomcat的地址
    private String tomcatPath;

    public String getTomcatPath() {
        return tomcatPath;
    }

    public void setTomcatPath(String tomcatPath) {
        this.tomcatPath = tomcatPath;
    }

    // python的exe的路径
    private String pythonExEPath;
    // 检测算法的python文件的路径
    private String detectionPythonFilePath;
    // 重新训练的python项目路径
    private String retrainProjectFilePath;
    // 重新训练的python文件的路径
    private String retrainPythonFilePath;
    // xml文件转化为txt的python文件的路径
    private String xmlToTxtPythonFilePath;
    // 最优模型存放的路径
    private String bestModeSavePath;
    // 存放xml文件的路径
    private String xmlFolderPath;
    // 存放转化后的txt的文件路径，要求路径下有“images”“labels”“masks”三个文件夹
    private String txtFolderPath;
    // 远程ftp服务器的ip地址
    private String ftpServerIP;
    // 远程ftp服务器的 ftp服务的端口号，一般为21
    private int ftpServerPort;
    // 远程ftp服务器登录用的用户名
    private String ftpServerUserName;
    // 远程ftp服务器登录用的密码
    private String ftpServerPassWord;

    public static EnvironmentPath getInstance(){
        if (instance==null){
            synchronized (EnvironmentPath.class){
                if (instance==null){
                    instance = new EnvironmentPath();
                }
            }
        }
        return instance;
    }

    private EnvironmentPath(){
        Logger logger = LoggerFactory.getLogger(getClass());
        String configFileName = "config.xml";
        File directory = new File("..");
        String directoryPath = "";
        try {
            directoryPath = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String configFilePath = directoryPath +File.separator+ configFileName;


        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File(configFilePath));
        } catch (DocumentException e) {
            logger.error("读取配置xml文件失败，路径为："+configFilePath);
            // TODO Auto-generated catch block
//            e.printStackTrace();
        }
        Element rootElement = document.getRootElement();
        this.aiIP = rootElement.element("aiIP").getStringValue();
        this.tomcatPath = rootElement.element("tomcatPath").getStringValue();

        this.pythonExEPath = rootElement.element("pythonExEPath").getStringValue();
        this.detectionPythonFilePath = rootElement.element("detectionPythonFilePath").getStringValue();
        this.retrainPythonFilePath = rootElement.element("retrainPythonFilePath").getStringValue();
        this.xmlToTxtPythonFilePath = rootElement.element("xmlToTxtPythonFilePath").getStringValue();
        this.retrainProjectFilePath = rootElement.element("retrainProjectFilePath").getStringValue();
        this.bestModeSavePath = rootElement.element("bestModeSavePath").getStringValue();
        this.xmlFolderPath = rootElement.element("xmlFolderPath").getStringValue();
        this.txtFolderPath = rootElement.element("txtFolderPath").getStringValue();
        this.ftpServerIP = rootElement.element("ftpServerIP").getStringValue();
        this.ftpServerPort = Integer.parseInt(rootElement.element("ftpServerPort").getStringValue());
        this.ftpServerUserName = rootElement.element("ftpServerUserName").getStringValue();
        this.ftpServerPassWord = rootElement.element("ftpServerPassWord").getStringValue();
    }


    public String getFtpServerIP() {
        return ftpServerIP;
    }

    public void setFtpServerIP(String ftpServerIP) {
        this.ftpServerIP = ftpServerIP;
    }

    public int getFtpServerPort() {
        return ftpServerPort;
    }

    public void setFtpServerPort(int ftpServerPort) {
        this.ftpServerPort = ftpServerPort;
    }

    public String getFtpServerUserName() {
        return ftpServerUserName;
    }

    public void setFtpServerUserName(String ftpServerUserName) {
        this.ftpServerUserName = ftpServerUserName;
    }

    public String getFtpServerPassWord() {
        return ftpServerPassWord;
    }

    public void setFtpServerPassWord(String ftpServerPassWord) {
        this.ftpServerPassWord = ftpServerPassWord;
    }

    public String getPythonExEPath() {
        return pythonExEPath;
    }

    public void setPythonExEPath(String pythonExEPath) {
        this.pythonExEPath = pythonExEPath;
    }

    public String getDetectionPythonFilePath() {
        return detectionPythonFilePath;
    }

    public void setDetectionPythonFilePath(String detectionPythonFilePath) {
        this.detectionPythonFilePath = detectionPythonFilePath;
    }

    public String getRetrainPythonFilePath() {
        return retrainPythonFilePath;
    }

    public void setRetrainPythonFilePath(String retrainPythonFilePath) {
        this.retrainPythonFilePath = retrainPythonFilePath;
    }

    public String getXmlToTxtPythonFilePath() {
        return xmlToTxtPythonFilePath;
    }

    public void setXmlToTxtPythonFilePath(String xmlToTxtPythonFilePath) {
        this.xmlToTxtPythonFilePath = xmlToTxtPythonFilePath;
    }


    public String getBestModeSavePath(){
        return this.bestModeSavePath;
    }
    public void setBestModeSavePath(String bestModeSavePath){
        this.bestModeSavePath = bestModeSavePath;
    }

    public String getRetrainProjectFilePath(){
        return this.retrainProjectFilePath;
    }

    public void setRetrainProjectFilePath(String retrainProjectFilePath){
        this.retrainProjectFilePath = retrainProjectFilePath;
    }

    public String getXmlFolderPath(){return this.xmlFolderPath;}

    public void setXmlFolderPath(String xmlFolderPath) {
        this.xmlFolderPath = xmlFolderPath;
    }

    public String getTxtFolderPath() {
        return txtFolderPath;
    }

    public void setTxtFolderPath(String txtFolderPath) {
        this.txtFolderPath = txtFolderPath;
    }
}
