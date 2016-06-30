<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Users</title>
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
                        <button class="btn btn-default"><a href="" data-target="#create-user-modal" data-toggle="modal">Create User</a></button>
                    </div>
                </div>
            </div>
            <br />
            <div class="row">
                <jsp:include page="adminMenu.jsp"/>

                <div class="col-md-8">
                    <table class="table table-bordered" style="text-align: center;" id="user-table">                       

                        <tr>
                            <th colspan="6">User List</th>
                        </tr>

                        <tr>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Username</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>

                        <c:forEach items="${users}" var="user">
                            <tr id="user-row-${user.id}">
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.username}</td>
                                <td><a href="" class="glyphicon glyphicon-edit" style="color:green;" data-user-id="${user.id}" data-toggle="modal" data-target="#edit-user-modal"></a></td>
                                <td><a href="" data-user-id="${user.id}" class="delete-user-link glyphicon glyphicon-remove" style="color:red;"></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>

        <div id="create-user-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">User Creation</h4>
                    </div>
                    <div class="modal-body">
                        <form method="POST" class="form-horizontal">
                            
                            <div class="form-group">
                                <label for="" class="col-md-4 control-label">First Name:</label>
                                <div class="col-md-8"><input type="text" class="form-control" id="first-name-input"/></div>
                            </div>
                            
                            <div class="form-group">
                                <label for="" class="col-md-4 control-label">Last Name:</label>
                                <div class="col-md-8"><input type="text" class="form-control" id="last-name-input"/></div>
                            </div>
                            
                            <div class="form-group">
                                <label for="" class="col-md-4 control-label">Username:</label>
                                <div class="col-md-8"><input type="text" class="form-control" id="username-input"/></div>
                            </div>
                            
                            <div class="form-group">
                                <label for="" class="col-md-4 control-label">Password:</label>
                                <div class="col-md-8"><input type="password" class="form-control" id="password-input"/></div>
                            </div>
                            
                            
                            
                            <div class="form-group">
                                <label for="" class="col-md-4 control-label">Role:</label>
                                <div class="col-md-8"><select id="role-id-input" class="form-control">
                                            <option value="1" class="form-control">Administrator</option>                                
                                            <option value="2" class="form-control">Marketing Team</option>                                
                                    </select></div>
                            </div>
                            
                            
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary" id="create-user-submit">Create</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <div id="edit-user-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">User Editor</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered">
                            
                            <input type="hidden" id="edit-user-id" />
                            
                            <tr>
                                <td>First Name:</td>
                                <td><input type="text" class="form-control" id="edit-user-first-name" /></td>
                            </tr>
                            
                            <tr>
                                <td>Last Name:</td>
                                <td><input type="text" class="form-control" id="edit-user-last-name" /></td>
                            </tr>
                            
                            <tr>
                                <td>Username:</td>
                                <td><input type="text" class="form-control" id="edit-user-username" /></td>
                            </tr>
                            
                            <tr>
                                <td>Password:</td>
                                <td><input type="password" class="form-control" id="edit-user-password" /></td>
                            </tr>
                            
                            <tr>
                                <td>Role:</td>
                                <td><select class="form-control" id="edit-user-role">
                                       
                                        <option value="1" class="form-control">Administrator</option>
                                        <option value="2" class="form-control">Marketing Team</option>
                                      
                                    </select></td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="edit-user-button">Save changes</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->


        <script>
            var contextRoot = "${pageContext.request.contextPath}";
        </script>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/userApp.js"></script>

    </body>
</html>