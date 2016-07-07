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
                        <button class="btn btn-default"><a href="" data-target="#create-role-modal" data-toggle="modal">Create Role</a></button>
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
                                <h4><center>Tag Rights</center></h4>
                            </div>
                            <div class="panel panel-body">
                                <%@include file="uacRoleTable.jsp"%>
                            </div>
                            <div class="panel panel-body">
                                <%@include file="uacUserTable.jsp"%>
                            </div>
                            
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <%@include file="uacRoleModals.jsp"%>

        <script>
            var contextRoot = "${pageContext.request.contextPath}";
        </script>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/userApp.js"></script>
        <script src="${pageContext.request.contextPath}/js/roleApp.js"></script>

    </body>
</html>

