<%--
  Created by IntelliJ IDEA.
  User: Jane
  Date: 4/6/2017
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Codelot</title>
    <link rel="stylesheet" type="text/css" href="../stylesheets/main.css" />
    <link rel="stylesheet" type="text/css" href="../stylesheets/map.css" />


    <link rel="stylesheet" type="text/css" href="../stylesheets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

    <link rel="icon" type="image/png" href="../images/CodelotShield.png" />
    <script src="../scripts/jquery-3.2.0.min.js"></script>
    <script src="../stylesheets/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <script type="text/javascript" src="../scripts/tether.min.js"></script>
    <script type="text/javascript" src="../scripts/phaser.min.js"></script>
    <script type="text/javascript" src=${lang}></script>

    <script>
        $(function() {
            $("#signoutLink").click(function() {
                $("#submitSignout").click();
            });
            $("#settingsLink").click(function() {
                $("#submitSettings").click();
            });
            $("#homeLink, #logolink").click(function() {
                $("#submitHome").click();
            });
            $("#homeLink, #logolink").click(function() {
                $("#submitHome").click();
            });
            $("#taskLink").click(function() {
                $("#submitTask").click();
            });
            $("#langSelectLink").click(function() {
                $("#submitLangSelect").click();
            });
        });
    </script>
</head>

<body>
<%-- Navigation Bar --%>
<nav class="navbar nav-main navbar-fixed-top">
    <div class="nav-container">
        <a id="logolink" class="logo" href="#">
            <img sizes="131px" src="../images/CodelotLogo.png" width="131">
        </a>
        <div class="nav-links">
            <%--<a role="button" data-toggle="modal" data-target="#faqModal">FAQ</a>--%>
            <form id="signoutForm" method="post">
                <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"/>
                <a id="langSelectLink">Back to Language Selection</a>
                <button style="display:none" id="submitLangSelect" title="LangSelect" type="submit"
                        onclick="form.action='/languageSelection';">Language Selection</button>
                <a id="homeLink">Home</a>
                <button style="display:none" id="submitHome" title="Home" type="submit"
                        onclick="form.action='/';">Home</button>
                <a id="settingsLink">Settings</a>
                <button style="display:none" id="submitSettings" title="Settings" type="submit"
                        onclick="form.action='/profilePage';">Settings</button>
                <a id="signoutLink">Signout</a>
                <button style="display:none" id="submitSignout" title="Sign Out" type="submit"
                        onclick="form.action='/signout';">Signout</button>
            </form>
        </div>
    </div>
</nav>

<%-- Main Content --%>
<div class="content-wrapper">
    <div class="content-row">

        <%-- Map Pane --%>
        <div class="column-4 wood-bg w-col w-col-10">
            <div id="gameDiv" class="row w-row">

            </div>
        </div>

        <%-- Avatar Information --%>
        <div class="column-3 w-col w-col-2">
            <div class="buttonblock">
                <div class="text-block">
                    <p><strong>${username}</strong></p>
                    <p><img id="avtr" src="${avatar}" height="80px" width="65px"></p>
                    <p>${fullName}</p>
                    <p>${age}</p>
                    <p>${email}</p>
                </div>
            </div>
            <hr />

            <%-- Progress information --%>
            <div class="buttonblock">
                <div class="text-block">
                    Progress
                    <div class="progress">
                        <div id="progress-bar" class="progress-bar" role="progressbar" aria-valuenow="${progress}"
                             aria-valuemin="0" aria-valuemax="100" style="width:${progress}%; background-color:maroon;">
                            <span class="sr-only">${progress}%</span>
                        </div>
                    </div>
                    <p id="progess_message" >${progress}%</p>
                </div>
            </div>
        </div>

    </div>

</div>

<div class="footer-bottom">
    <div class="footer-bottom-wrap">
        <div class="legal">Information about the group and other footer content. Design made using Webflow.</div>
    </div>
</div>

</body>
</html>
