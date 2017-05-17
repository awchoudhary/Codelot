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
    <title>My Profile</title>
    <link rel="stylesheet" type="text/css" href="../stylesheets/main.css" />
    <link rel="stylesheet" type="text/css" href="../stylesheets/profileCreation.css" />

    <link rel="stylesheet" type="text/css" href="../stylesheets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

    <link rel="icon" type="image/png" href="../images/CodelotShield.png" />
    <script src="../scripts/jquery-3.2.0.min.js"></script>
    <script src="../scripts/profilePage.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Droid+Serif|Inknut+Antiqua|Lobster|Metamorphous|Playfair+Display:700" rel="stylesheet">
</head>

<style>
    body{
        font-family: 'Droid Serif', serif;
    }
</style>

<body>
<div class="formWrapper">

    <div id="createProfile" class="wood-bg">

        <h2 class="formTitle">Profile</h2>
        <h6>${note}</h6>
        <form action="/updateProfile" method="post">
            <table class="form-group createTable">
                <tr>
                    <td><label for="name">Full Name:</label></td>
                    <td><input value="${fullName}" type="text" class="form-control" id="name" name="fullname"/></td>
                </tr>
                <tr>
                    <td><label for="age">Age:</label></td>
                    <td><input value="${age}" type="number" class="form-control" id="age" name="age"/></td>
                </tr>
                <tr>
                    <td><label for="username">Username:</label></td>
                    <td><input value="${username}" type="text" class="form-control" id="username" name="username"/></td>
                </tr>
                <tr>
                <td><label for="avtr">Avatar:</label></td>
                <td>
                    <select class="form-control" name="avatar" id="avtr" onchange="switchImage();" >
                        <option  value="../../images/sprites/amg4.png" selected>Female Mage</option>
                        <option  value="../../images/sprites/avt2.png">Male Mage</option>
                        <option  value="../../images/sprites/knt1.png">Male Knight</option>
                        <option  value="../../images/sprites/knt4.png">Female Knight</option>
                    </select>
                </td>
                </tr>

                <tr>
                    <td id="avatar-td">
                        <img id="image" src ="${avatar}"/>
                        <script>
                            function switchImage() {
                                var selectedImage = document.getElementById('avtr').value;
                                document.getElementById('image').src = selectedImage;
                            }
                        </script>

                    </td>
                    <td>
                        <a class="button w-button" onclick="$('#updateUser').click();"><strong>Done</strong></a>
                        <input style="display:none" id="updateUser" type="submit" value="Update"/>
                    </td>
                </tr>

                <tr style="${display};">
                    <td colspan="2">
                        <div class="resetText"><a id="showReset">Reset progress for all languages</a>
                            <div id="resetWrapper">
                                <form method="post">
                                    <a class="button w-button" id="resetLink">Reset All</a>
                                    <button style="display:none" id="submitReset" title="Reset" type="submit"
                                            onclick="form.action='/resetRedirect';">Reset</button>
                                </form>
                            </div>
                        </div>
                    </td>
                </tr>

            </table>
        </form>
    </div>
</div>


</body>
</html>
