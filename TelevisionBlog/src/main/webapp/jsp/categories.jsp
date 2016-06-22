<%-- 
    Document   : categories
    Created on : Jun 21, 2016, 7:50:58 PM
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
        <title>Categories</title>
    </head>
    <body>
        <table id="category-table">
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.name}</td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <h4>Add Category:</h4>
            <form method="POST">
                <label for="category-name">Name:</label>
                    <input id="category-name"/>
            </form>
        </div>
    </body>
</html>
