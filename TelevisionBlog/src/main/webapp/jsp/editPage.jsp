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

                </div>
            </div>
            <br />
            <div class="row">

                <jsp:include page="adminMenu.jsp"/>

                <div class="col-md-8">
                    <form method="POST" class="form-horizontal">
                        
                        <input type="hidden" id="page-id" value="${page.id}" />
                        <input type="hidden" id="page-active" value="${page.active}" />

                        <div class="form-group">
                            <label for="" class="control-label">Title:</label>
                            <div>
                                <input type="text" id="page-title-input" class="form-control" value="${page.name}"></input>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="" class="control-label">Desired URL Path:</label>
                            <div>
                                <input type="text" id="page-url-input" class="form-control" placeholder="http://TelevisionBlog/YOUR-URL-PATH-HERE" value="${page.url}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="" class="control-label">Page Content:</label>
                            <div class="">
                                <textarea type="text" id="page-content-input" class="form-control">${page.content}</textarea>
                            </div>
                        </div>
                        <input class="btn btn-primary" type="submit" id="edit-submit" />
                    </form>
                </div>
            </div>
        </div>

        <script>
            var contextRoot = "${pageContext.request.contextPath}";
        </script>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/tinymce/js/tinymce/tinymce.min.js"></script>

        <script type="text/javascript">
            tinymce.init({
                selector: '#page-content-input',
                height: 400,
                width: 800
            });
        </script>
        <script src="${pageContext.request.contextPath}/js/pageApp.js"></script>


    </body>
</html>
