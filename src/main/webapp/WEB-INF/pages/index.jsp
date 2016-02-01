<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Prog.kiev.ua</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2 style="text-align:center">Client List</h2>

    <form class="form-inline" role="form" action="/search" method="post">
        <input type="text" class="form-control" name="pattern" placeholder="Search">
        <input type="submit" class="btn btn-default" value="Search">
    </form>

    <form class="form-inline" role="form">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><b></b></td>
                <td><b>Logo</b></td>
                <td><b>Name</b></td>
                <td><b>Tarif</b></td>
                <td><b>Balance</b></td>
                <td><b>Action</b></td>
                <td><b>Finance</b></td>
            </tr>
            </thead>
            <c:forEach items="${clis}" var="pay">
                <tr>
                        <%--Добавляем чекбоксы--%>
                    <td><input type="checkbox" name="selected" value="${pay.id}"></td>
                    <td><img height="40" width="40" src="/image/${pay.logo.id}"/></td>
                    <td><a href="/payments?id=${pay.id}" class="text-primary">${pay.name}</a></td>
                    <td>${pay.tarif}</td>
                    <td>${pay.balance}</td>
                    <td>
                        <a href="/remove?id=${pay.id}" class="text-warning">Remove</a>
                        <br>
                        <a href="/delete?id=${pay.id}" class="text-danger">Delete</a>
                    </td>
                    <td>
                        <a href="/writeoff?id=${pay.id}" class="text-warning">Writeoff the fee</a>
                        <br>
                        <a href="/deposit?id=${pay.id}&sum=50" class="text-danger">Deposit 50</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <%--Кнопка для удаления клиентов в корзину--%>
        <input type="submit" class="btn btn-warning" value="Remove selected"
               formaction="/remove_selected">
        <%--Кнопка для безвозвратного удаления клиентов--%>
        <input type="submit" class="btn btn-danger" value="Delete selected"
               formaction="/delete_selected">
        <%--Кнопка для списания абонплаты у выбранных клиентов--%>
        <input type="submit" class="btn btn-primary" value="Writeoff the fee for selected"
               formaction="/writeoff_selected">
    </form>

    <br>

    <form class="form-inline" role="form" method="post">
        <input type="submit" class="btn btn-primary" value="Add new"
               formaction="/add_page">
        <input type="submit" class="btn btn-success" value="Trash"
               formaction="/view_trash">
    </form>

</div>
</body>
</html>