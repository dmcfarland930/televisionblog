<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="root">
    <head>
        <title>Create Static Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/css/starter-template.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container" ng-controller="index">
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

                <div id="add-page-validation-errors"></div>

                <div class="col-md-8">
                    <form method="POST" class="form-horizontal">

                        <div class="form-group">
                            <label for="" class="control-label">Title:</label>
                            <div>
                                <input ng-model="title" type="text" id="page-title-input" class="form-control" />
                     
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="" class="control-label">Desired URL Path:</label>
                            <div>
                                <input ng-value="url" type="text" id="page-url-input" class="form-control"  />
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="" class="control-label">Page Content:</label>
                            <div class="">
                                <textarea type="text" id="page-content-input" class="form-control"></textarea>
                            </div>
                        </div>

                        <input class="btn btn-primary" type="submit" id="create-submit" />
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
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/pageApp.js"></script>
        <script src="${pageContext.request.contextPath}/js/pageAngl.js"></script>

        <script type="text/javascript">
            tinymce.init({
                selector: '#page-content-input',
                height: 400,
                width: 800,
                images_upload_url: 'postAcceptor.php',
                images_upload_base_path: '/some/basepath',
                images_upload_credentials: true,
                fontsize_formats: "8pt 10pt 12pt 14pt 18pt 24pt 36pt",
                plugins: ['advlist autolink lists link image charmap print preview hr anchor pagebreak',
                    'searchreplace wordcount visualblocks visualchars code fullscreen',
                    'insertdatetime media nonbreaking save table contextmenu directionality',
                    'emoticons template paste textcolor colorpicker textpattern imagetools'],
                menubar: "insert",
                toolbar1: 'mybutton | insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
                toolbar2: 'print preview media | forecolor backcolor emoticons',
                image_advtab: true,
                relative_urls: false,
                setup: function (editor) {
                    editor.addButton('mybutton', {
                        text: 'Upload Image',
                        icon: false,
                        onclick: function () {
                            $('#UploadModal').modal('show');
                        }
                    });
                }
            });
        </script>
        



    </body>
</html>
