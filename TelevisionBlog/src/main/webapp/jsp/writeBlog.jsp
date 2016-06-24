<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Write Blog</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <%@include file="navBar.jsp"%>
        <div class="container">
            <h1>Blog Writer</h1>
            <hr/>
            <div>
                <form class="form form-horizontal" method="POST" enctype="multipart/form-data">
                    File to upload: <input class="form-control" type="file" name="file">

                    Name: <input class="form-control" type="text" name="name">
                    
                    <button class="form-control" id="file-upload-button" type="submit">Upload</button>Press here to upload the file!
                </form>	
            </div>
            <div id="img-display"></div>
            <form method="POST">

                <fieldset class="form-group">
                    <div id="title-div" class="col-md-8">
                        <label for="title-input">Title: </label>
                        <input type="text" id="title-input" class="form-control ${hasError}"/>
                    </div>
                    <div class="error-message" id="name-error" class="col-md-8">
                    </div>
                </fieldset>
                
	
	<div id='msgbox' title='' style='display:none'></div>
                <fieldset class="form-group">
                    <div id="title-div" class="col-md-8">
                        <label for="title-input">Category: </label>
                        <select class="form-control" name="categoryName" id="category-input">
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </fieldset>

                <fieldset class="form-group">
                    <div id="blog-div"  class="col-md-8">
                        <div>
                            <textarea style="width: 100%;" rows="10" id="blog-post-input"></textarea>
                        </div>
                    </div>
                </fieldset>

                <div style="display: inline-block;" class="col-md-8 pull">
                    <input id="blog-post-button" class="btn bg-primary button-size" type="submit" value="Create Blog Post"/>



                    <input id="blog-draft-button" class="btn bg-primary button-size" type="submit" value="Save Draft"/>

                </div>
            </form>

        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script>
            var contextRoot = '${pageContext.request.contextPath}';
        </script>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/tinymce/js/tinymce/tinymce.min.js"></script>
                <script type="text/javascript">
                    tinymce.init({
                        selector: '#blog-post-input',
                        height: 400,
                        width: 800,
                        images_upload_url: 'postAcceptor.php',
                        images_upload_base_path: '/some/basepath',
                        images_upload_credentials: true,
                        plugins: ['advlist autolink lists link image charmap print preview hr anchor pagebreak',
                        'searchreplace wordcount visualblocks visualchars code fullscreen',
                        'insertdatetime media nonbreaking save table contextmenu directionality',
                        'emoticons template paste textcolor colorpicker textpattern imagetools'],
                        menubar: "insert",
                        toolbar1: 'insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
                        toolbar2: 'print preview media | forecolor backcolor emoticons',
                        image_advtab: true
                    });
                </script>
        <script src="${pageContext.request.contextPath}/js/blogPost.js"></script>

    </body>
</html>

