/**
 * 
 */
package DAO;

import java.util.List;

import entity.ServiceMessage;

/**
 * @author NeilHu
 *
 * 2018年1月25日
 */
public interface ServiceDAO {
    List<ServiceMessage> findAll();
    void add(ServiceMessage sm);
    void update(ServiceMessage sm);
    void delete(ServiceMessage sm);
    
}
