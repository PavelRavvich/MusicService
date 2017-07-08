<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
</head>
<body>
<h3>User menu.</h3><br />

<form method="POST" action="user_menu">

    <label for="editMusic">Edit profile</label>
    <input type="radio" name="act" value="edit" id="editMusic"><br>

    <label for="addMusic">Add to my music</label>
    <input type="radio" name="act" value="add" id="addMusic"><br>

    <label for="deleteMusic">Delete from my music</label>
    <input type="radio" name="act" value="delete" id="deleteMusic"><br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

<c:forEach var="music" items="${requestScope.allMusic}">
    <ul>
        <li>Name: <c:out value="${music.name}"/></li>
    </ul><hr align="center" width="90%" size="5" color="#dddddd" />
</c:forEach>
</body>
</html>
