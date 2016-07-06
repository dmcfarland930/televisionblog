<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                        <button class="btn btn-default"><a href="" data-target="#create-user-modal" data-toggle="modal">Create Role</a></button>
                    </div>
                </div>
            </div>
            <br />
            <div class="row">
                <jsp:include page="adminMenu.jsp"/>

                <div class="col-md-8">

                    <jsp:include page="uacMenu.jsp"/>
                    
                    <h1>UAC Home page <br /> Work In Progress <br /> Click a Tab</h1> <br /> By the way, only the Group Roles work right now, individual Users and Custom doesn't work yet.

<!--                    <table class="table table-bordered" style="text-align: center;" id="user-table">                       

                        <tr>
                            <th colspan="6">User Roles</th>
                        </tr>

                        <tr>
                            <th>Role</th>
                            <th>Create</th>
                            <th>Read</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>

                        <c:forEach items="${roles}" var="role">
                            <tr id="role-row-${role.id}">
                                <td>${role.displayName}</td>
                                <td><input type="checkbox" data-target="${user.id}" class="checkbox checkbox-inline" name="${role.name}" value="create"></td>
                                <td><input type="checkbox" data-target="${user.id}" class="checkbox checkbox-inline" name="${role.name}" value="read"></td>
                                <td><input type="checkbox" data-target="${user.id}" class="checkbox checkbox-inline" name="${role.name}" value="update"></td>
                                <td><input type="checkbox" data-target="${user.id}" class="checkbox checkbox-inline" name="${role.name}" value="delete"></td>

                            </tr>
                        </c:forEach>
                    </table>

                    <table class="table table-bordered" style="text-align: center;" id="user-table">                       

                        <tr>
                            <th colspan="7">User List</th>
                        </tr>

                        <tr>
                            <th>User</th>
                            <th>Role</th>
                            <th>Read</th>
                            <th>Write</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>

                        <c:forEach items="${users}" var="user">
                            <tr id="user-row-${user.id}">
                                <td>${user.lastName}, ${user.firstName} (${user.username})</td>
                                <td><select id="user-role-${user.id}" class="form-control" name="user-role">
                                        <c:forEach items="${roles}" var="role">
                                            <option value="${role.name}" class="form-control">${role.displayName}</option>  
                                        </c:forEach>
                                    </select></td>
                                <td><input type="checkbox" data-target="${user.id}" class="chkbox checkbox checkbox-inline" name="user-role-${user.id}" value="create"></td>
                                <td><input type="checkbox" data-target="${user.id}" class="chkbox checkbox checkbox-inline" name="user-role-${user.id}" value="read"></td>
                                <td><input type="checkbox" data-target="${user.id}" class="chkbox checkbox checkbox-inline" name="user-role-${user.id}" value="update"></td>
                                <td><input type="checkbox" data-target="${user.id}" class="chkbox checkbox checkbox-inline" name="user-role-${user.id}" value="delete"></td>
                            </tr>
                        </c:forEach>
                    </table>-->
                </div>

            </div>
        </div>

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
