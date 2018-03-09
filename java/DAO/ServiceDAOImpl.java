/**
 * 
 */
package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import entity.ServiceMessage;

/**
 * @author NeilHu
 *
 * 2018年1月26日
 */
public class ServiceDAOImpl implements ServiceDAO, Serializable {


    public List<ServiceMessage> findAll() {
        Connection conn=null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from service order by service_id";
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            List<ServiceMessage> list = new ArrayList<ServiceMessage>();
            
            while(rs.next()){
                System.out.println("正在从数据库获取数据...");
                ServiceMessage serviceMessage = createServiceMessage(rs);
                list.add(serviceMessage);
            }
            return list;
            
        } catch (Exception e) {
            //记录日志
            e.printStackTrace();
            //
            throw new RuntimeException("维修服务提交失败",e);
        }finally{
            DBUtil.closeConnection(conn);
        }
        
    }
    
    private ServiceMessage createServiceMessage(ResultSet rs) throws SQLException{
        ServiceMessage serviceMessage=new ServiceMessage();
       
        serviceMessage.setServiceId(rs.getInt("service_id"));
        serviceMessage.setDormID(rs.getString("dorm_id"));
        serviceMessage.setPhoneNumber(rs.getString("phone_number"));
        serviceMessage.setInformation(rs.getString("information"));
        serviceMessage.setFreeTime(rs.getString("freetime"));
        serviceMessage.setCreatTime(rs.getTimestamp("creattime"));
        serviceMessage.setEvaluate(rs.getInt("evaluate"));
        return serviceMessage;
    }
    

   
    public void add(ServiceMessage sm) {
        Connection conn=null;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into service(dorm_id,phone_number,information,freetime,"
                    + "creattime,evaluate)"+"values(?,?,?,?,null,?) ";
            PreparedStatement ps = null;
            ps = conn.prepareStatement(sql);
            ps.setString(1,sm.getDormID());
            ps.setString(2,sm.getPhoneNumber());
            ps.setString(3,sm.getInformation());
            ps.setString(4,sm.getFreeTime());
            ps.setObject(5,sm.getEvaluate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("维修请求添加失败",e);
        }
        finally{
            DBUtil.closeConnection(conn);
        }
    }

   
    public void update(ServiceMessage sm) {
        System.out.println("实现更新");
        Connection conn =null;
        try {
            conn = DBUtil.getConnection();
            String sql = "update service set dorm_id=?,phone_number=?,information=?,"
                    + "freetime=? where service_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,sm.getDormID());
            ps.setString(2, sm.getPhoneNumber());
            ps.setObject(3, sm.getInformation());
            ps.setObject(4, sm.getFreeTime());
            ps.setObject(5, sm.getServiceId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("更新失败", e);
        }
        finally{
            DBUtil.closeConnection(conn);
        }
    }

   
    public void delete(ServiceMessage sm) {
        System.out.println("删除数据");
        Connection conn = null;
        try {
            conn=DBUtil.getConnection();
            String sql = "delete from service where service_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            //建议将setInt/setDouble换成setObject处理 这样没有空指针异常情况
                ps.setObject(1, sm.getServiceId());
                ps.executeUpdate();
                
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败", e);
        }finally{
            DBUtil.closeConnection(conn);
        }
    }

}
