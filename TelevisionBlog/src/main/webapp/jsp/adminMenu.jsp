<%-- 
    Document   : adminMenu
    Created on : Jun 27, 2016, 9:40:21 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="col-md-4">
    <ul class="list list-group">
        <li class="list-group-item"><a href="${pageContext.request.contextPath}">Back to Blog</a></li>
        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/">Pending Posts</a></li>
        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/post/">Blog List</a></li>
        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/category/">Category List</a></li>
        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/tag/">Tag List</a></li>
        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/page/">Page List</a></li>
        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/user/">User List</a></li>
        <li class="list-group-item"><a href="${pageContext.request.contextPath}/admin/uac/">User Access Control</a></li>
    </ul>    

    <a class="btn bg-primary" href="${pageContext.request.contextPath}/authenticate/logout">Log Out</a>

</div>
