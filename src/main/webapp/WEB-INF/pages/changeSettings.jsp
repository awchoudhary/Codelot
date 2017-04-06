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
    <title>Update Profile</title>
    <link rel="stylesheet" type="text/css" href="../stylesheets/main.css" />
    <link rel="stylesheet" type="text/css" href="../stylesheets/profileCreation.css" />

    <link rel="stylesheet" type="text/css" href="../stylesheets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

    <link rel="icon" type="image/png" href="../images/CodelotShield.png" />
    <script src="../scripts/jquery-3.2.0.min.js"></script>

</head>

<body>
<div class="formWrapper">

    <div id="createProfile" class="wood-bg">

        <h2 class="formTitle">Update Profile</h2>
        <form action="/changeSettings2" method="post">
            <table class="form-group createTable">
                <tr>
                    <td><label for="name">Full Name:</label></td>
                    <td><input value=${fullName} type="text" class="form-control" id="name" name="fullname"/></td>
                </tr>
                <tr>
                    <td><label for="age">Age:</label></td>
                    <td><input value=${age} type="number" class="form-control" id="age" name="age"/></td>
                </tr>
                <tr>
                    <td><label for="username">Username:</label></td>
                    <td><input value=${username} type="text" class="form-control" id="username" name="username"/></td>
                </tr>
                <td><label for="avtr">Avatar:</label></td>
                <td>
                    <select class="form-control" name="avatar" id="avtr" onchange="switchImage();" >
                        <option  value="../../images/duck.png">Duck</option>
                        <option  value="../../images/clown.png">Clown</option>
                        <option  value="../../images/wabbit.png">Rabbit</option>
                    </select>
                </td>
                </tr>

                <tr>
                    <td id="avatar-td">
                        <img id="image" src =${avatar}/>
                        <script>
                            function switchImage() {
                                var selectedImage = document.getElementById('avtr').value;
                                document.getElementById('image').src = selectedImage;
                            }
                        </script>

                    </td>
                    <td>
                        <a class="button w-button" onclick="$('#updateUser').click();"><strong>Update</strong></a>
                        <input style="display:none" id="updateUser" type="submit" value="Update"/>
                    </td>
                </tr>

            </table>
        </form>
    </div>
</div>


</body>
</html>
