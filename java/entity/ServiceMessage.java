/**
 * 
 */
package entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author NeilHu
 *
 * 2018年1月25日
 */
public class ServiceMessage implements Serializable{
    

    //订单服务号
    private Integer serviceId;
    //宿舍号
    private String dormID;
    //电话号码
    private String phoneNumber;
    //维护需求详情
    private String information;
    //空闲时间
    private String freeTime;
    //创建时间
    private Timestamp creatTime;
    //服务评价
    private Integer evaluate;
    
    public Integer getServiceId() {
        return serviceId;
    }
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }
    public String getDormID() {
        return dormID;
    }
    public void setDormID(String dormID) {
        this.dormID = dormID;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getInformation() {
        return information;
    }
    public void setInformation(String information) {
        this.information = information;
    }
    public String getFreeTime() {
        return freeTime;
    }
    public void setFreeTime(String freeTime) {
        this.freeTime = freeTime;
    }
    public Timestamp getCreatTime() {
        return creatTime;
    }
    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }
    public Integer getEvaluate() {
        return evaluate;
    }
    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }
    
    
}
