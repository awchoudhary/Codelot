<%--
  Created by IntelliJ IDEA.
  User: Jane
  Date: 4/4/2017
  Time: 5:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Profile</title>
</head>
<body>
<h2>Create Profile</h2>
    <div id="createProfile">
        <form action="/profileCreation2" method="post">
            <table>
                <tr>
                    <td>Full Name</td>
                    <td><input type="text" name="fullname"/></td>
                </tr>
                <tr>
                    <td>Age</td>
                    <td><input type="number" name="age"/></td>
                </tr>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username"/></td>
                </tr>
                    <td>Avatar</td>
                    <td>
                        <select name="avatar">
                            <option style="background-image:url(../../images/duck.png);" value="avatar1"></option>
                            <option style="background-image:url(../../images/clown.png);" value="avatar2"></option>
                        </select>
                    </td>
                </tr>
                <tr><td><input type="submit" value="Add"/></td></tr>
            </table>
        </form>
    </div>
</body>
</html>
