<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New - tabletki.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form role="form" enctype="multipart/form-data" class="form-horizontal" action="add" method="post">
        <h2 style="text-align:center">New client</h2>
        <h4>Complete the form</h4>

        <table class="table table-striped">
            <colgroup>
                <col class="col-md-1">
                <col class="col-md-7">
            </colgroup>
            <tbody>
            <tr>
                <td><b>Name</b></td>
                <td><input type="text" class="form-control" name="name" maxlength="100"></td>
            </tr>
            <tr>
                <td><b>Tarif</b></td>
                <td><input type="text" class="form-control" name="tarif" maxlength="100"></td>
            </tr>
            <tr>
                <td><b>Balance</b></td>
                <td><input type="text" class="form-control" name="balance" maxlength="100" value="0.0"></td>
            </tr>
            <tr>
                <td><b>Photo</b></td>
                <td><input type="file" name="logo" accept="image/jpeg,image/png"></td>
            </tr>
            </tbody>
        </table>

        <input type="submit" class="btn btn-primary" value="Add Client">
    </form>

    <br>

</div>
</body>
</html>