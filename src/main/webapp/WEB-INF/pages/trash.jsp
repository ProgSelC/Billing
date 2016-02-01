<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Trash - tabletki.ua</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h2 style="text-align:center">Removed Clients</h2>

  <form class="form-inline" role="form">
    <table class="table table-striped">
      <thead>
      <tr>
        <td><b></b></td>
        <td><b>Logoo</b></td>
        <td><b>Name</b></td>
        <td><b>Tarif</b></td>
        <td><b>Balance</b></td>
        <td><b>Action</b></td>
      </tr>
      </thead>
      <c:forEach items="${clis}" var="pay">
        <tr>
            <%--Добавляем чекбоксы--%>
          <td><input type="checkbox" name="selected" value="${pay.id}"></td>
          <td><img height="40" width="40" src="image/${pay.logo.id}" /></td>
          <td>${pay.name}</td>
          <td>${pay.tarif}</td>
          <td>${pay.balance}</td>
          <td>
            <a href="restore?id=${pay.id}">Restore</a>
            <br>
            <a href="delete?id=${pay.id}">Delete</a>
          </td>
        </tr>
      </c:forEach>
    </table>
    <%--Кнопка для удаления клиентов в корзину--%>
    <input type="submit" class="btn btn-success" value="Restore selected"
           formaction="restore_selected">
    <%--Кнопка для безвозвратного удаления клиентов--%>
    <input type="submit" class="btn btn-danger" value="Delete selected"
           formaction="delete_selected">
  </form>

  <br>

  <form class="form-inline" role="form" method="post">
    <input type="submit" class="btn btn-primary" value="Main page" formaction="/">
  </form>

</div>
</body>
</html>
