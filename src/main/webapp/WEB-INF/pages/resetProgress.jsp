<%--
  Created by IntelliJ IDEA.
  User: Jane
  Date: 5/11/2017
  Time: 12:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm Reset</title>
    <link rel="stylesheet" type="text/css" href="../stylesheets/main.css" />
    <link rel="stylesheet" type="text/css" href="../stylesheets/profileCreation.css" />
    <link rel="stylesheet" type="text/css" href="../stylesheets/task.css" />
    <link rel="stylesheet" type="text/css" href="../stylesheets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

    <link rel="icon" type="image/png" href="../images/CodelotShield.png" />
    <script src="../scripts/jquery-3.2.0.min.js"></script>
</head>

<script>
    $(function() {
        $("#resetLink").click(function() {
            $("#submitReset").click();
        });
        $("#noResetLink").click(function() {
            $("#submitNoReset").click();
        });
    });
</script>

<body>
<div class="formWrapper">

    <div id="createProfile" class="wood-bg">

        <h2 class="formTitle">Reset Progress</h2>
        <p>Are you sure you want to reset progress?</p>
        <form method="post">

            <table class="form-group createTable">
                <tr>
                    <td>
                        <a class="floor" id="resetLink">Yes</a>
                        <button style="display:none" id="submitReset" title="Yes Reset" type="submit"
                                onclick="form.action='/reset';">Yes</button>
                    </td>
                    <td>
                        <a class="floor" id="noResetLink">No</a>
                        <button style="display:none" id="submitNoReset" title="No Reset" type="submit"
                                onclick="form.action='/languageSelection';">No</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>

