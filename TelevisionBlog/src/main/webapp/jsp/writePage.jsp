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
        <div class="container">
            <h1>Dashboard</h1>
            <hr/>
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-8">
                    <div style="float: right">
                        <button class="btn btn-default"><a href="${pageContext.request.contextPath}/page/create">Create New Page</a></button>
                    </div>
                </div>
            </div>
            <br />
            <div class="row">
                <div class="col-md-4">
                    <ul class="list list-group">
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/index.jsp">Back to Blog</a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/post.jsp">Posts</a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/categories.jsp">Categories</a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/pages.jsp">Pages</a></li>
                        <li class="list-group-item"><a href="${pageContext.request.contextPath}/users.jsp">Users</a></li>
                    </ul>    
                </div>

                <div class="col-md-8">
                    <form method="POST" class="form-horizontal">

                        <div class="form-group">
                            <label for="" class="col-md-4 control-label">Title:</label>
                            <div class="col-md-8">
                                <input type="text" id="page-title-input" class="form-control"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="" class="col-md-4 control-label">Page Content:</label>
                            <div class="col-md-8">
                                <textarea type="text" id="page-content-input" class="form-control"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="" class="col-md-4 control-label">Desired URL Path:</label>
                            <div class="col-md-8">
                                <input type="text" id="page-url-input" class="form-control" placeholder="http://TelevisionBlog/page/YOUR-URL-PATH-HERE"/>
                            </div>
                        </div>
                        
                        <input class="btn btn-default center-block" type="submit" id="create-submit" />
                    </form>

                </div>
            </div>
                    
                    <script>
                        var contextRoot = "${pageContext.request.contextPath}";
                    </script>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/pageApp.js"></script>
       

    </body>
</html>
