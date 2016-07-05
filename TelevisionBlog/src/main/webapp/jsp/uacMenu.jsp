<%-- 
    Document   : uacMenu
    Created on : Jul 1, 2016, 9:11:31 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container-fluid">
    <ul class="nav nav-tabs" data-tabs="tabs">
        <li><a href="${pageContext.request.contextPath}/admin/uac">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/uac/blogs?group=post" >Blog Posts</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/uac/pages?group=page">Static Pages</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/uac/users?group=user">Users</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/uac/categories?group=category">Categories</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/uac/tag?group=tag">Tags</a></li>

    </ul>
</div>
