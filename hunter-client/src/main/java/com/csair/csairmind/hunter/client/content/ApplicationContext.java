package com.csair.csairmind.hunter.client.content;


import com.csair.csairmind.hunter.common.inf.MgrService;

/**
 * Created by zhangcheng
 */
public interface ApplicationContext {
    /**
     * 启动服务
     */
    public void start();
    /**
     * 退出服务
     */
    public  void stop();


    public MgrService getMgrService();

    public void setMgrService(MgrService mgrService);

    public String getSessionKey();

    public void setSessionKey(String sessionKey);

    public String getMachineId();

    public void setMachineId(String machineId);
}
