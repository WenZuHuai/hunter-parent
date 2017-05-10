package com.csair.csairmind.hunter.client.content;


import com.csair.csairmind.hunter.client.service.WrapService;
import com.csair.csairmind.hunter.common.inf.MgrService;

/**
 * Created by zhangcheng
 */
public class DefaultApplicationContext implements ApplicationContext {

    private static  final Object sycObject=new Object();
    private static  ApplicationContext instance;

    public  static  ApplicationContext context(){
        if(instance==null){
            synchronized (sycObject){
                if(instance==null){
                    instance=new DefaultApplicationContext();
                }
            }
        }
        return instance;
    }




    @Override
    public void start() {
        WrapService service=new WrapService();
        if(service.register()){

        }else{
            System.out.println("注册机器失败........");
        }

    }

    @Override
    public void stop() {

    }
    /**
     * 休息一下
     * @param millionSeconds
     */
    protected void takeARest(int millionSeconds){
        try {
            Thread.sleep(millionSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**获取服务对象*/
    private MgrService mgrService;
    /**会话key*/
    private String sessionKey="";
    /**机器ID*/
    private String machineId="";

    public MgrService getMgrService() {
        return mgrService;
    }

    public void setMgrService(MgrService mgrService) {
        this.mgrService = mgrService;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }




}
