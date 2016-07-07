<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Blog</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/datetimepicker-master/jquery.datetimepicker.css">
        
        <!--<link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">-->

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Blog Edit</h1>
            <hr/>
            <div class="row">
                <jsp:include page="adminMenu.jsp"/>

                <div>
                    <form action="${pageContext.request.contextPath}/blog/editsubmit/" name="editForm" method="POST">

                        <input type="hidden" name="id" value="${id}"/>
                        <input type="hidden" name="date" value="${date}"/>
                        <input type="hidden" name="draft" value="${draft}"/>

                        <fieldset class="form-group">
                            <div id="title-div" class="col-md-8">
                                <label for="title-input">Title: </label>
                                <input name="title" type="text" id="title-edit" class="form-control ${hasError}" value="${title}"/>
                            </div>
                        </fieldset>
                        <div class="error-message" id="title-error" class="col-md-8">
                        </div>
                        <fieldset class="form-group">
                            <div id="slug-div"  class="col-md-8">
                                <label for="" class="control-label">Slug:</label>
                                <div>
                                    <input name="slug" type="text" id="slug-input" class="form-control" value="${slug}" />
                                </div>
                        </fieldset>
                        <div class="error-message" id="title-error" class="col-md-8">
                        </div>
                        <fieldset class="form-group">
                            <div id="title-div" class="col-md-8">
                                <label for="title-input">Author: </label>
                                <select name="author" class="form-control" value="${author}" id="author-edit">
                                    <c:forEach items="${authors}" var="author">
                                        <option value="${author.id}">${author.firstName} ${author.lastName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset> 

                        <fieldset class="form-group">
                            <div id="title-div" class="col-md-8">
                                <label for="title-input">Category: </label>
                                <select name="category" class="form-control" value="${category}" id="category-edit">
                                    <c:forEach items="${categories}" var="category">
                                        <option value="${category.id}">${category.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </fieldset>

                        <fieldset class="form-group">
                            <div id="title-div" class="col-md-8">
                                <label for="title-input">Post Date: </label>
                                <br/>
                                <input value="${date}" type="text" name="schedule-date" id="post-date-input"/>
                                </select>
                            </div>
                        </fieldset>


                        <fieldset class="form-group">
                            <div id="title-div" class="col-md-8">
                                <label for="title-input">Expiration Date: </label>
                                <br/>
                                <input  type="text" name="expiration-date" id="expiration-date-input"/>
                                </select>
                            </div>
                        </fieldset>



                        <fieldset class="form-group">
                            <div id="blog-div"  class="col-md-8 pull-right">
                                <div>
                                    <textarea name="content" style="width: 100%;" rows="10" id="blog-post-input" >${content}</textarea>
                                </div>
                            </div>
                        </fieldset>

                        <div style="display: inline-block;" class="col-md-8 pull-right">



                            <input id="blog-edit-submit" class="btn bg-primary button-size" type="submit" value="Update Blog Post"/>


                            <input id="blog-draft-button" class="btn bg-primary button-size" type="submit" value="Save Draft"/>

                        </div>
                    </form>
                </div>
            </div>
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
                fontsize_formats: "8pt 10pt 12pt 14pt 18pt 24pt 36pt",
                images_upload_url: 'postAcceptor.php',
                images_upload_base_path: '/some/basepath',
                images_upload_credentials: true,
                plugins: ['advlist autolink lists link image charmap print preview hr anchor pagebreak',
                    'searchreplace wordcount visualblocks visualchars code fullscreen',
                    'insertdatetime media nonbreaking save table contextmenu directionality',
                    'emoticons template paste textcolor colorpicker textpattern imagetools'],
                menubar: "insert",
                toolbar1: 'mybutton | insertfile undo redo | styleselect | fontselect | fontsizeselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
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

        <script src="${pageContext.request.contextPath}/plugins/chosen/chosen.jquery.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/blogPost.js"></script>
        <script src="${pageContext.request.contextPath}/datetimepicker-master/build/jquery.datetimepicker.full.min.js"></script>

    </body>
</html>

