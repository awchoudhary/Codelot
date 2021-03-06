<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Codelot</title>
    <link rel="stylesheet" type="text/css" href="../stylesheets/main.css" />
    <link rel="stylesheet" type="text/css" href="../stylesheets/task.css" />

    <link rel="stylesheet" type="text/css" href="../stylesheets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

    <link rel="icon" type="image/png" href="../images/CodelotShield.png" />
    <script src="../scripts/jquery-3.2.0.min.js"></script>
    <script src="../stylesheets/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="../scripts/mapSelect.js"></script>
    <script src="../scripts/base.js"></script>


    <link href="https://fonts.googleapis.com/css?family=Droid+Serif|Inknut+Antiqua|Lobster|Metamorphous|Playfair+Display:700" rel="stylesheet">
</head>

<style>
    body{
        font-family: 'Droid Serif', serif;
    }
</style>

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

        <%-- Map Selection Pane --%>
        <div class="column-4 wood-bg w-col w-col-10">
            <h1 class="heading-6"><strong>Choose a language</strong></h1>
            <div class="row w-row">
                <div id="java" class="mapWrapper w-col w-col-4">

                    <div class="progress-wrapper">
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="${javaProgress}"
                                 aria-valuemin="0" aria-valuemax="100" style="width:${javaProgress}%; background-color:maroon;">
                                <span class="sr-only">${javaProgress}%</span>
                            </div>
                            <p class="progress-message">${javaProgress}%</p>
                        </div>
                    </div>

                    <img class="map" src="../images/javaworld_overview.png" width="330">
                    <div class="caption-1">Java-Ville</div>
                </div>
                <div id="python" class="mapWrapper w-col w-col-4">

                    <div class="progress-wrapper">
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="${pythonProgress}"
                                 aria-valuemin="0" aria-valuemax="100" style="width:${pythonProgress}%; background-color:maroon;">
                                <span class="sr-only">${pythonProgress}%</span>
                            </div>
                            <p class="progress-message">${pythonProgress}%</p>
                        </div>
                    </div>

                    <img class="map" src="../images/pythonworld_overview.png" width="330">
                    <div class="caption-1">Python-Valley</div>
                </div>
                <div id="javascript" class="mapWrapper w-col w-col-4">

                    <div class="progress-wrapper">
                        <div class="progress">
                            <div class="progress-bar" role="progressbar" aria-valuenow="${jsProgress}"
                                 aria-valuemin="0" aria-valuemax="100" style="width:${jsProgress}%; background-color:maroon;">
                                <span class="sr-only">${jsProgress}%</span>
                            </div>
                            <p class="progress-message">${jsProgress}%</p>
                        </div>
                    </div>

                    <img class="map" src="../images/javascriptworld_overview.png" width="330">
                    <div class="caption-1">JavaScript-Cove</div>
                </div>
            </div>

            <div class="div-block">
                <form method="post">
                    <button title="Map" type="submit" class="button w-button" onclick="form.action='/map';">
                        <strong>Enter</strong></button>
                    <input type="hidden" id="lang" name="selectedLang"/>
                </form>
            </div>
        </div>

        <%-- Avatar Information --%>
        <div class="column-3 w-col w-col-2">
            <div class="buttonblock">
                <div class="text-block">
                    <p><strong>${username}</strong></p>
                    <p><img src="${avatar}" height="80px" width="65px"></p>
                    <p>${fullName}</p>
                    <p>${age}</p>
                    <p>${email}</p>
                </div>
            </div>
            <hr />
            <div class="buttonblock">
                <div class="text-block">
                    <h2 id="aboutLanguage"></h2>
                    <p id="languageDescription"></p>
                </div>
            </div>
        </div>
    </div>

</div>
<div class="footer-bottom">
    <div class="footer-bottom-wrap">
        <div class="legal">Codelot. CSE 308 Maroon. Awaes Choudhary, Tiffany Ramroop, Brandon Cuadrado, Jane Thomas.</div>
    </div>
</div>

</body>
</html>