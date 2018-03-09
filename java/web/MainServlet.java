/**
 * 
 */
package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ServiceDAO;
import DAO.ServiceDAOImpl;
import entity.ServiceMessage;

/**
 * @author NeilHu
 *
 *         2018年3月9日
 */
public class MainServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // 获取请求路径
        String path = req.getServletPath();
        //根据规范处理路径
        if ("/findServiceMessage.do".equals(path)) {
            findServiceMessage(req,res);
        }
        else {
            throw new RuntimeException("error 404");
        }
    }

    /**
     * @param req
     * @param res
     * 查看维修信息
     */
    private void findServiceMessage(HttpServletRequest req,
            HttpServletResponse res) throws ServletException{
        //查看所有的维修信息
       ServiceDAO dao = new ServiceDAOImpl();
       List<ServiceMessage> list = dao.findAll();
       //转发到JSP
       req.setAttribute("serviceMessage", list);
       //当前:/dormitoryManagement/findServiceMessage.do
       //目标:/dormitoryManagement/WEB-INF/service/find.jsp
       try {
        req.getRequestDispatcher("WEB-INF/service/find.jsp").forward(req, res);
    } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException("查询维修信息失败");
    }
       
       
    }

}
