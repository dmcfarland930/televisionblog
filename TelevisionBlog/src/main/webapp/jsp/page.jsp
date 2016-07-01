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
                        <button class="btn btn-default"><a href="${pageContext.request.contextPath}/admin/page/create/">Create New Page</a></button>
                    </div>
                </div>
            </div>
            <br />
            <div class="row">
                <jsp:include page="adminMenu.jsp"/>

                <div class="col-md-8">
                    <table id="page-table" class="table table-bordered" style="text-align: center;">   
                        <thead>

                            <tr>
                                <th colspan="5">Static Page List</th>
                            </tr>

                            <tr>
                                <th>Title</th>
                                <th>Edit</th>
                                <th>Delete</th>
                                <th>Position</th>
                                <th>Active</th>
                            </tr>
                        </thead>
                        <tbody id="sortable">

                            <c:forEach items="${pages}" var="page">
                                <tr id="page-row-${page.id}" class="page-rows">
                                    <td><a href="${pageContext.request.contextPath}/${page.url}">${page.name}</a></td>
                                    <td><a href="${pageContext.request.contextPath}/admin/page/edit/${page.id}" class="glyphicon glyphicon-edit" style="color: green;"></a></td>
                                    <td><a href="" data-page-id="${page.id}" class="delete-page-link glyphicon glyphicon-remove" style="color:red;"></a></td>
                                        <c:choose>
                                            <c:when test="${page.position == 0}">
                                            <td>Not Set</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>${page.position}</td>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:choose>
                                        <c:when test="${page.active}">
                                            <td><a href="" data-page-id="${page.id}" class="active-page-link glyphicon glyphicon-check" style="color:dodgerblue;"></a></td>
                                            </c:when>
                                            <c:otherwise>
                                            <td><a href="" data-page-id="${page.id}" class="active-page-link glyphicon glyphicon-unchecked" style="color:red;"></a></td>
                                            </c:otherwise>
                                        </c:choose>

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div id="edit-page-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Edit Static Page</h4>
                    </div>
                    <div class="modal-body">
                        <table class="table table-bordered">

                            <input type="hidden" id="edit-page-id" />
                            <input type="hidden" id="edit-page-active" />

                            <tr>
                                <th>Title:</th>
                                <td><input type="text" id="edit-page-title" class="form-control"/></td>
                            </tr>

                            <tr>
                                <th>Page Content:</th>
                                <td><textarea type="text" id="edit-page-content" class="form-control"></textarea></td>
                            </tr>

                            <tr>
                                <th>Desired URL Path:</th>
                                <td><input type="text" id="edit-page-url" class="form-control" placeholder="http://TelevisionBlog/page/YOUR-URL-PATH-HERE"/></td>
                            </tr>

                        </table>
                        <input class="btn btn-default center-block" type="submit" id="create-submit" />

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="edit-page-button">Save changes</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->


        <script>
            var contextRoot = "${pageContext.request.contextPath}";
        </script>
        <!-- Placed at the end of the document so the pages load faster -->
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/pageApp.js"></script>
        <script src="${pageContext.request.contextPath}/js/tinymce/js/tinymce/tinymce.min.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <link rel="stylesheet" href="/resources/demos/style.css">

        <script type="text/javascript">
            tinymce.init({
                selector: '#edit-page-content',
                height: 400,
                width: 400
            });
        </script>

    </body>
</html>