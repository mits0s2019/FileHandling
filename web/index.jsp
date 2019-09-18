
<%@page import="java.util.List"%>
<%@page import="model.MyFile"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <title>JSP Page</title>

        <style>
            .btn {
                background-color: DodgerBlue;
                border: none;
                color: white;
                padding: 9px 26px;
                cursor: pointer;
                font-size: 15px;
            }
            .fa-close{
                background-color: red;      
            }
            #delete{
                background-color: red;  
            }
            .btn:hover {
                background-color: RoyalBlue;
            }

        </style>
    </head>



    <body>
        <div class="container-fluid pt-5 w-50">
            <form method="POST" action="upload" enctype="multipart/form-data">
                <div class="input-group">

                    <div class="input-group-prepend">
                        <input type="submit" value="Upload" class="input-group-text">
                    </div>

                    <div class="custom-file">
                        <input type="file" class="custom-file-input" name="myfile" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
            </form>
        </div>




        <c:if test="${filesList.size()>0}">
            <c:set var="count" value="0" scope="page" />
            <div class="container-fluid w-50 float-center pt-5 "  >
                <table class="table table-bordered table-hover" >
                    <thead class="table-dark">
                        <tr>
                            <th>#</th> 
                            <th>File</th> 
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${filesList}" var="file"  >
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <td>${count}</td>
                                <td>${file.file_name}</td>
                                <td><a href="download?id=${file.id}" class="btn pl-2 pr-2"><i class="fa fa-download"></i> Download</a> </td>
                                <td><a href="delete?id=${file.id}" id="delete" class="btn pl-3 pr-4"><i class="fa fa-close"></i> Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody> 
                </table>
            </div>
        </c:if>




        <script>
            $(".custom-file-input").on("change", function () {
                var fileName = $(this).val().split("\\").pop();
                $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
            });
        </script>
    </body>
</html>
