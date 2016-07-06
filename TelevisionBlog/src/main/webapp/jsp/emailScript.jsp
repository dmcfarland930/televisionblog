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
        <link href="https://fonts.googleapis.com/css?family=Cabin" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <%@include file="navBar.jsp"%>
        <div class="container">
            <div class="script-upload col-md-4 col-md-offset-4">
                <div id="script-contact-info" style="text-align: center">
                    <p id="script-head">Think your words are the best words? </p>
                    <p>Send us your script and you might just get your ideas thrown on the little screen!</p>
                    <p>Your script will be sent to:</p>
                    <p>Patrick Toner</p>
                    <p>(patstvblog@gmail.com)</p>
                    <hr>
                </div>
                <form class="form form-horizontal" method="POST" enctype="multipart/form-data">
                    <label for="name-">Your Name:</label>
                    <input class="form-control" type="text" id="name-input">
                    <label for="name-">Your Email:</label>
                    <input class="form-control" type="text" id="email-input">
                    <br/>
                    <label for="file">Upload Script:</label>
                    <input class="form-control" type="file" name="file">
                    <button class="upload-button btn btn-primary pull-right" id="script-upload-button" type="submit">Upload</button>
                </form>
            </div>
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
