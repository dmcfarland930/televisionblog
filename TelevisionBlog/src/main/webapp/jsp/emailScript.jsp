<%-- 
    Document   : emailScript
    Created on : Jun 29, 2016, 11:22:31 AM
    Author     : apprentice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Send Script</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
        
    </head>
    <body>
        <%@include file="navBar.jsp"%>
        <div class="container">
            <div id="script-contact-info" class="col-md-12" style="text-align: center">
                <h4>Your script will be sent to:</h4>
                <h4>Patrick Toner</h4>
                <h4>(patstvblog@gmail.com)</h4>
            </div>
            <form class="form form-horizontal" method="POST" enctype="multipart/form-data">
                <label for="name-">Enter your name:</label>
                <input class="form-control" type="text" id="name-input">
                <label for="file">Upload Script</label>
                <input class="form-control" type="file" name="file">
                <button class="btn btn-primary" id="script-upload-button" type="submit">Upload</button>
            </form>
        </div>
        <script>
            var contextRoot = '${pageContext.request.contextPath}';
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/tinymce/js/tinymce/tinymce.min.js"></script>

        
        <script src="${pageContext.request.contextPath}/js/contact.js"></script>
    </body>
</html>
