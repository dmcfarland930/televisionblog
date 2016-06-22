<%-- 
    Document   : home
    Created on : Jun 22, 2016, 9:34:17 AM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="navBar.jsp"/>
        <table id="category-table">
            <tbody>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Category</th>
                </tr>
            </thead>
                <c:forEach items="${blogPosts}" var="blogPost">
                    <tr>
                        <td>${blogPost.title}</td>
                        <td>${blogPost.category.name}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
