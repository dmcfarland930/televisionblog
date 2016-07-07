<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Access Control</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.12/css/jquery.dataTables.css">




        <style>
            th {
                text-align: center;
            };

        </style>
    </head>
    <body>
        <div class="container">
            <h1>Dashboard</h1>
            <hr/>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-8">
                    <div style="float: right">
                        <!--<button class="btn btn-default"><a href="" data-target="#create-role-modal" data-toggle="modal">Create Role</a></button>-->
                    </div>
                </div>
            </div>
            <br />
            <div class="row">

                <jsp:include page="adminMenu.jsp"/>
                <div class="col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel panel-default">
                            <jsp:include page="uacMenu.jsp"/>
                            <div class="panel panel-heading">
                                <h4><center>User Access Control Panel</center></h4>
                            </div>
                            <div class="panel panel-body">
                                <table class="table table-bordered">

                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th colspan="4">Blog Posts</th>
                                            <th colspan="4">Static Pages</th>
                                            <th colspan="4">Users</th>
                                            <th colspan="4">Categories</th>
                                            <th colspan="4">Tags</th>
                                        <tr>
                                        <tr>
                                            <th></th>
                                                <c:forEach items="${allRights}" var="right">
                                                    <c:if test="${not empty right.name}">
                                                    <th>${right.name}</th>
                                                    </c:if>
                                                </c:forEach>
                                        </tr>
                                    </thead>
<!--                                    <tbody>
                                        <c:forEach items="${roles}" var="role">
                                            <tr>
                                                <td>${role.name}</td>
                                                <c:forEach var="i" begin="1" end="20">
                                                    <c:forEach items="${role.allUserRights}" var="right">
                                                        <c:choose>
                                                            <c:when test="${right.id == i}">

                                                                <td><span class="glyphicon glyphicon-check"></span></td>

                                                            </c:when>
                                                            <c:otherwise>
                                                                <td><span class="glyphicon glyphicon-remove-circle"></span></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:forEach>
                                                    </c:forEach>
                                            </tr>
                                        </c:forEach>
                                    </tbody>-->

                                </table>


                                <c:forEach items="${roles}" var="role">
                                    <div class="panel panel-info">
                                        <div class="panel panel-heading text-center">
                                            ${role.name} <br />
                                        </div>
                                        <div class="panel panel-body text-center">
                                            <ul class="list list-inline">
                                                <li>
                                                    Blog Posts
                                                    <ul>
                                                        <c:forEach items="${role.allUserRights}" var="rights">
                                                            <c:if test="${rights.groupName == 'POST'}">
                                                                <li>${rights.name}</li>
                                                                </c:if>
                                                            </c:forEach>
                                                    </ul>
                                                </li>
                                                <li>
                                                    Static Pages
                                                    <ul>
                                                        <c:forEach items="${role.allUserRights}" var="rights">
                                                            <c:if test="${rights.groupName == 'PAGE'}">
                                                                <li>${rights.name}</li>
                                                                </c:if>
                                                            </c:forEach>
                                                    </ul>
                                                </li>
                                                <li>
                                                    Categories
                                                    <ul>
                                                        <c:forEach items="${role.allUserRights}" var="rights">
                                                            <c:if test="${rights.groupName == 'CATEGORY'}">
                                                                <li>${rights.name}</li>
                                                                </c:if>
                                                            </c:forEach>
                                                    </ul>
                                                </li>
                                                <li>
                                                    Tags
                                                    <ul>
                                                        <c:forEach items="${role.allUserRights}" var="rights">
                                                            <c:if test="${rights.groupName == 'TAG'}">
                                                                <li>${rights.name}</li>
                                                                </c:if>
                                                            </c:forEach>
                                                    </ul>
                                                </li>
                                                <li>
                                                    Users
                                                    <ul>
                                                        <c:forEach items="${role.allUserRights}" var="rights">
                                                            <c:if test="${rights.groupName == 'USER'}">
                                                                <li>${rights.name}</li>
                                                                </c:if>
                                                            </c:forEach>
                                                    </ul>
                                                </li>
                                            </ul>

                                        </div>
                                    </div>
                                </c:forEach>





                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <script>
            var contextRoot = "${pageContext.request.contextPath}";
        </script><!--
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.12/js/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/js/userApp.js"></script>
        <script src="${pageContext.request.contextPath}/js/roleApp.js"></script>

    </body>
</html>
