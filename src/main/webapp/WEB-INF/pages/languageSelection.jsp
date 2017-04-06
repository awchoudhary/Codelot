<%--
  Created by IntelliJ IDEA.
  User: ramroop
  Date: 4/5/17
  Time: 1:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body>
<%--<div style="position: absolute; left: 0; top: 0; right: 0; bottom: 0;">--%>
    <%--<div style=" height:100%; background-color:Gray;">--%>
        <form method="post">
            <button style="padding: 10px;" title="Sign Out" type="submit" onclick="form.action='/signout';">Sign Out</button>
        </form>
        <div>
            <p></p><img src="${avatar}" height="50px" width="40px"></p>
            <p>${fullName}</p>
            <p>${username}</p>
            <p>${email}</p>
        </div>
    <%--</div>--%>
    <div>
        <script type="text/javascript" src="../../scripts/phaser.min.js"></script>
        <script type="text/javascript" src="../../scripts/main.js"></script>
    </div>
<%--</div>--%>



</body>
</html>
