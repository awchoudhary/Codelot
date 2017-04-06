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
<%--<div style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; background-color:Lime;">--%>
    <div style="width:20%; height:100%; background-color:Gray;">
        <div style="text-align:center; display:inline-block; vertical-align:top; padding: 25px;">
            <form method="post">
                <button style="padding: 10px;" title="Sign Out" type="submit" onclick="form.action='/signout';">Sign Out</button>
            </form>
            <p></p><img src="${avatar}" height="80px" width="65px"></p>
            <p>${fullName}</p>
            <p>${username}</p>
            <p>${email}</p>
        </div>
    </div>
    <div style="width:80%; height:100%;">
        <script type="text/javascript" src="../../scripts/phaser.min.js"></script>
        <script type="text/javascript" src="../../scripts/main.js"></script>
    </div>
<%--</div>--%>



</body>
</html>
