<%-- 
    Document   : tags
    Created on : Jun 27, 2016, 9:56:56 AM
    Author     : apprentice
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tags</title>
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
                        <button class="btn btn-default"><a href="" data-toggle="modal" data-target="#createTagModal">Create Tag</a></button>
                    </div>
                </div>
            </div>
            <br />
            <div class="row">
                <jsp:include page="adminMenu.jsp"/>

                <div class="col-md-8">
                    <table class="table table-bordered" style="text-align: center;" id="tag-table">                       

                        <tr>
                            <th colspan="6">Tag List</th>
                        </tr>

                        <tr>
                            <th>Name</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>

                        <c:forEach items="${tags}" var="tag">
                            <tr id="tag-row-${tag.id}">
                                <td>${tag.name}</td>
                                <td><a href="" class="glyphicon glyphicon-edit" style="color:green;" data-toggle="modal" data-tag-id="${tag.id}" data-target="#editTagModal"></a></td>
                                <td><a href="" data-tag-id="${tag.id}" class="glyphicon glyphicon-remove delete-tag-link" style="color:red;"></a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div id="createTagModal" class="modal fade" role="dialog">
            <div class="modal-dialog modal-sm">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header col-md-12">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Create Tag</h4>
                    </div>
                    <div class="modal-body col-md-12">
                        <div id="add-tag-form" class="form-horizontal">
                            <div class="form-group">
                                <form method="POST">
                                    <div class="col-md-4">
                                        <label for="name-input">Add Tag:</label>
                                    </div>
                                    <div class="col-md-8">
                                        <input id="name-input" class="form-control"/>
                                    </div>
                                    <div id="add-tag-validation-errors"></div>
                                    <div style = "text-align: center; margin-top: 15px">

                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-danger" id="create-submit" value="Create">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div id="editTagModal" class="modal fade" role="dialog">
            <div class="modal-dialog modal-sm">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Edit Tag</h4>
                    </div>
                    <div class="modal-body">
                        <input hidden="true" id="edit-tag-id">
                        <div class="form-group">
                            <label for="edit-tag-name">Name:</label>
                            <input class="form-control" id="edit-tag-name">
                            <div id="edit-tag-validation-errors"></div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" id="edit-tag-button">Edit</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>


        <script>
            var contextRoot = "${pageContext.request.contextPath}";
        </script>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/tags.js"></script>

    </body>
</html>
