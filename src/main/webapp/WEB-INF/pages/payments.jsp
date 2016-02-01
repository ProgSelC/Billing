<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payments - tabletki.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2 style="text-align:center">Client Info</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <td><b>Logo</b></td>
            <td><b>Name</b></td>
            <td><b>Tarif</b></td>
            <td><b>Balance</b></td>
            <td><b>Action</b></td>
            <td><b>Finance</b></td>
        </tr>
        </thead>
        <tr>
            <td><img height="40" width="40" src="image/${model.cli.logo.id}"/></td>
            <td>${model.cli.name}</td>
            <td>${model.cli.tarif}</td>
            <td>${model.cli.balance}</td>
            <td>
                <a href="remove?id=${model.cli.id}" class="text-warning">Remove</a>
                <br>
                <a href="delete?id=${model.cli.id}" class="text-danger">Delete</a>
            </td>
            <td>
                <a href="writeoff?id=${model.cli.id}" class="text-warning">Writeoff the fee</a>
                <br>
                <a href="deposit?id=${model.cli.id}&sum=50" class="text-danger">Deposit 50</a>
            </td>
        </tr>
    </table>
    <br>
    <br>
    <table class="table table-striped">
        <thead>
        <tr>
            <td><b>Date</b></td>
            <td><b>Sum</b></td>
        </tr>
        </thead>
        <c:forEach items="${model.payments}" var="pay">
            <tr class="${pay.type ? 'text-success' : 'text-danger'}">
                <td>${pay.date}</td>
                <td>${pay.sum}</td>
            </tr>
        </c:forEach>
    </table>
    <br>

    <form class="form-inline" role="form" method="post">
        <input type="submit" class="btn btn-primary" value="Main page" formaction="/">
    </form>

</div>
</body>
</html>