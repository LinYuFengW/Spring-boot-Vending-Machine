<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
    + path + "/";
%>
<html lang="en">
<head>
    <script type="text/javascript" src="/training/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/training/js/jquery.form.js"></script>
    <script>
        $(function () {
            $("#upload").bind('click',function () {
                var option = {
                    url:'<%=basePath%>/mutliFileUplod/upload',
                    type:'post',
                    async:true,
                    enctype:'multipart/form-data',
                    dataType:'text',
                    success: function (data) {
                        alert(data);
                    },
                    error:function () {
                       alert("upload failed!");
                    }
                };
                $("#upload-form").ajaxSubmit(option);
            });
        });
    </script>
</head>
<body>
<div>
    <form action="#" method="POST" enctype="multipart/form-data" id="upload-form">
        <input type="file" name="files"/><br/>
        <input type="file" name="files"/><br/>
        <input type="file" name="files"/><br/>

        <input type="submit" value="上传" id="upload">
    </form>
</div>
</body>
</html>