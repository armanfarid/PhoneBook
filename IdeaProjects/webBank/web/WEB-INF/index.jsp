<%--
  Created by IntelliJ IDEA.
  User: armanfarid
  Date: 11/5/17
  Time: 1:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="utf-8" />
  <meta name="author" content="Script Tutorials" />
  <title>Whirling dropdown menu | Script Tutorials</title>

  <!-- add styles -->
  <link href="/css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="container" id="main" role="main">
  <ul class="menu">
    <li><a href="#">BankMenu</a></li>
    <li><a href="#s1">Customer</a>
      <ul class="submenu">
        <li><a href="#">insert</a></li>
        <li><a href="#">update</a></li>
        <li><a href="#">delete</a></li>

      </ul>
    </li>
    <li class="active"><a href="#s2">Deposit</a>
      <ul class="submenu">
        <li><a href="#">insert</a></li>
        <li><a href="#">update</a></li>
        <li><a href="#">delete</a></li>
        <li><a href="#">deposit</a></li>
        <li><a href="#">withdraw</a></li>
        <li><a href="#">transfer</a></li>

      </ul>
    </li>
    <li><a href="#">Report</a>
      <ul class="submenu">
        <li><a href="#">deposits</a></li>
        <li><a href="#">customers</a></li>
        <li><a href="#">deposit for a customer</a></li>

      </ul>
    </li>
    <li><a href="#">backup</a></li>
    <ul class="submenu">
      <li><a href="#">import to db</a> </li>
      <li><a href="#">export from db</a> </li>


    </ul>
  </ul>
</div>
</body>
</html>