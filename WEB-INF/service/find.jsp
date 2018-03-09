<%@page pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>宿舍管理系统</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
        <script language="javascript" type="text/javascript">
            //排序按钮的点击事件
            function sort(btnObj) {
                if (btnObj.className == "sort_desc")
                    btnObj.className = "sort_asc";
                else
                    btnObj.className = "sort_desc";
            }

            //启用
            function startFee() {
                var r = window.confirm("确定要启用此事件吗？启用后将不能修改和删除。");
                document.getElementById("operate_result_info").style.display = "block";
            }
            //删除
            function deleteFee(id) {
                var r = window.confirm("确定要删除此项吗？");
                if(r) {
                	//删除选中的资费
                	location.href = "deleteCost.do?id="+id;
                }
                //document.getElementById("operate_result_info").style.display = "block";
            }
        </script>        
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
           <c:import url="../logo.jsp"></c:import>       
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">                        
            		<c:import url ="../menu.jsp"></c:import>
        
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">
            <form action="" method="">
                <!--排序-->
                <div class="search_add">
                    <div>
                        <!--<input type="button" value="月租" class="sort_asc" onclick="sort(this);" />-->
                        <input type="button" value="基费" class="sort_asc" onclick="sort(this);" />
                        <input type="button" value="时长" class="sort_asc" onclick="sort(this);" />
                    </div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddCost.do';" />
                </div> 
                <!--启用操作的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div>    
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th>服务订单号</th>
                            <th class="width100">宿舍号 </th>
                            <th>联系电话</th>
                            <th>维修内容</th>
                           	<th>空闲时间</th>
                           	<th>提交时间</th>
                           	<th>维修评价</th>
                        </tr> 
                        <c:forEach items="${serviceMessage}" var="c">                    
                        <tr>
                            <td>${c.serviceId}</td>
                            <td><a href="fee_detail.html">${c.dormID}</a></td>
                            <td>${c.phoneNumber}</td>
                            <td>${c.information}</td>
                            <td>${c.freeTime}</td>
                            <td><fmt:formatDate value="${c.creatTime}" pattern="yyyy-MM-dd"/></td>
                            <td>${c.evaluate}</td>
                            <td>                                
                                <input type="button" value="确定" class="btn_start" onclick="startFee();" />
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateCost.do?id=${c.serviceId}';" />
                                <input type="button" value="删除" class="btn_delete" onclick="location.href='deleteCost.do?id=${c.serviceId}';" />
                            </td>
                        </tr>
                        </c:forEach> 
                    </table>
                    <p>业务说明：<br />
                    1、创建订单时，状态为未维修，记载创建时间；<br />
                    2、未维修状态下，可修改，可删除；<br />
                    3、已提交后，记载开通时间，且开通后不能修改、不能再停用、也不能删除；<br />
                    </p>
                </div>
                <!--分页-->
                <div id="pages">
                	<!-- 如果当前页是第1页，不能点上一页 -->
                	<c:choose>
                		<c:when test="${costPage.currentPage==1}">
                			<a href="#">上一页</a>
                		</c:when>
                		<c:otherwise>
                			<a href="findCost.do?currentPage=${costPage.currentPage-1}">上一页</a>
                		</c:otherwise>
                	</c:choose>
                    
                    <!-- 循环输出页码，从1开始到totalPage结束 -->
                    <c:forEach begin="1" end="${costPage.totalPage}" var="p">
                    	<!-- 如果是当前页有样式，否则没有样式 -->
                    	<c:choose>
                    		<c:when test="${p==costPage.currentPage}">
                    			<a href="findCost.do?currentPage=${p}" class="current_page">${p}</a>
                    		</c:when>
                    		<c:otherwise>
                    			<a href="findCost.do?currentPage=${p}">${p}</a>
                    		</c:otherwise>
                    	</c:choose>
                    </c:forEach>
                    
                    <!-- 如果当前页是最后一页，不能点下一页 -->
                    <c:choose>
                    	<c:when test="${costPage.currentPage==costPage.totalPage}">
                    		<a href="#">下一页</a>
                    	</c:when>
                    	<c:otherwise>
                    		<a href="findCost.do?currentPage=${costPage.currentPage+1}">下一页</a>
                    	</c:otherwise>
                    </c:choose>
                    
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[14数媒2班胡冀之毕业设计--宿舍管理系统]</p>
            <p>DormitoryManagement--NeilHu</p>
        </div>
    </body>
</html>
