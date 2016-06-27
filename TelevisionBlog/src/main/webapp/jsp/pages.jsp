<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Static Pages</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <%@include file="navBar.jsp"%>
        <div class="container">
            <h1>Dashboard</h1>
            <hr/>
            <div class="row">
                <jsp:include page="adminMenu.jsp"/>
                <div class="col-md-8">
                    <div style="float: right">
                        <button><a href="${pageContext.request.contextPath}/page/writePage.jsp">Create New Page</a></button>
                    </div>
                   
                    <table clas="table table-bordered">                       
                        <caption>Static Page List</caption>
                       
                        <tr>
                            <th>Title</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                        
                        <c:forEach items="${pages}" var="page">
                            <tr>
                                <td>${page.title}</td>
                                <td><a href="${pageContext.request.contextPath}/page/writePage.jsp">Edit</a></td>
                                <td><a href="" data-page-id="${page.id}" class="delete-page-link"></a>Delete</td>
                            </tr>
                        </c:forEach>
                        
                    </table>
                    
                </div>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>