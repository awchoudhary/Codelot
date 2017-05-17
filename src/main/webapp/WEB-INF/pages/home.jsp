<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Codelot</title>
        <link rel="stylesheet" type="text/css" href="../stylesheets/main.css" />
        <link rel="stylesheet" type="text/css" href="../stylesheets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

        <script src="../stylesheets/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

        <link rel="icon" type="image/png" href="../images/CodelotShield.png" />
        <script src="../scripts/home.js"></script>
        <script src="../scripts/base.js"></script>

        <link href="https://fonts.googleapis.com/css?family=Droid+Serif|Inknut+Antiqua|Lobster|Metamorphous|Playfair+Display:700" rel="stylesheet">
    </head>

    <style>
        body{
            font-family: 'Droid Serif', serif;
        }
        video {
            width: 75% !important;
            height: 100% !important;
        }

    </style>

    <body>
        <nav class="navbar nav-main navbar-fixed-top">
            <div class="nav-container">
                <a class="logo" href="#">
                    <img sizes="131px" src="../images/CodelotLogo.png" width="131">
                </a>
                <div class="nav-links">
                    <a id="homeLink">Home</a>
                    <a id="aboutLink">About</a>
                    <a id="faqLink">FAQ</a>
                </div>
            </div>
        </nav>
        <div id="Home" class="wood-bg section hero-section">
            <div class="hero-wrap">
                <div class="hero-left">
                    <h1 class="heading-1"><strong>Codelot: Virtual Coding Kingdom</strong></h1>
                    <p class="subhead-1">
                        Learn to code and study core programming concepts as easily as playing a game!
                    </p>
                    <a class="button w-button" onclick="location.href='/signin'"><strong>Play</strong></a>
                </div>
                <video loop muted autoplay poster="img/wood_cartoon.jpg"
                       class="fullscreen-bg__video">
                    <%--<source src="video/big_buck_bunny.webm" type="video/webm">--%>
                    <source src="videos/codelot_demo.mp4" type="video/mp4">
                    <%--<source src="video/big_buck_bunny.ogv" type="video/ogg">--%>
                </video>
            </div>
        </div>
        <div class="logos-section">
            <div>
            </div>
        </div>

        <div id="About" class="class-section">
            <div class="a wrap-2">
                <div class="left-1">
                    <h2 class="heading-2">What is Codelot?</h2>
                    <p class="paragraph-2">If you want to learn how to code and love playing games then Codelot is for you !! Complete an adventure within the Codelot Kingdom with your chosen avatar and learn how to code in a variety of coding languages at the same time. </p>
                </div>
                <div class="right-1"><img class="screenshot" sizes="(max-width: 767px) 63vw, (max-width: 991px) 35vw, 300px" src="images/pythonworld_overview.png" srcset="images/pythonworld_overview.png 500w" width="300">
                    <p class="caption-2">Image from Codelot.</p>
                </div>
            </div>
            <div class="b wrap-2">
                <div class="left-2"><img class="screenshot" sizes="(max-width: 479px) 80vw, 300px" src="images/javascriptworld_overview.png" srcset="images/javascriptworld_overview.png 500w" width="300">
                    <p class="caption-3">Image from Codelot.</p>
                </div>
            <div class="right-2">
                <h2 class="heading-2">Virtual Coding Kingdom</h2>
                <p class="paragraph-2">There are currently three regions corresponding to three different coding languages within the Codelot kindgom. Unlock all the buildings in each region to master the language  </p>
            </div>
        </div>
    </div>

    <div class="footer-bottom">
        <div class="footer-bottom-wrap">
            <div class="legal">Codelot. CSE 308 Maroon. Awaes Choudhary, Tiffany Ramroop, Brandon Cuadrado, Jane Thomas.</div>
        </div>
    </div>
    <div class="w-embed">
        <style>
            .xxx { background: rgba(0,0,0,0.05); }
        </style>
    </div>

    <!-- FAQ Modal -->
    <div class="modal fade" id="faqModal" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Frequently Asked Questions</h4>
            </div>
            <div class="modal-body">
                <div class="panel-group">
                    <div class="panel panel-default">
                      <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" href="#collapse1">Exploring Codelot</a>
                        </h4>
                      </div>

                      <div id="collapse1" class="panel-collapse collapse">
                        <ul class="list-group">
                          <li class="list-group-item">
                            <strong>How do I navigate Codelot?</strong>
                            <br/>
                            Navigate your character around Codelot using the directional arrows on your keyboard!
                          </li>
                          <li class="list-group-item">
                            <strong>How do I begin a coding task?</strong>
                            <br/>
                            To begin a coding task, enter a building by walking up to the location of the building.
                          </li>
                          <li class="list-group-item">
                            <strong>A building appears to be locked!</strong>
                            <br/>
                            If a building is locked, this means that other buildings are required to be completed before entering. Explore the town for the next unlocked building in your journey!
                          </li>
                        </ul>
                      </div>
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" href="#collapse2">Coding Tasks</a>
                            </h4>
                        </div>

                        <div id="collapse2" class="panel-collapse collapse">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <strong>How do I review the necessary information needed to complete a task?</strong>
                                    <br/>
                                    Click the Review Lesson link at the top of the page to review all necessary information.
                                </li>
                                <li class="list-group-item">
                                    <strong>How do I test my solution?</strong>
                                    <br/>
                                    Click the Execute button beneath your code to run your solution and determine whether it is successful or unsuccessful.
                                </li>
                                <li class="list-group-item">
                                    <strong>How do I revisit a floor?</strong>
                                    <br/>
                                    You may navigate all unlocked floors by using the floor buttons located on the right.
                                </li>
                            </ul>
                        </div>

                    </div>
                  </div>
            </div>
            <div class="modal-footer">
              <button type="button" id="startTask" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
          </div>

        </div>
    </div>

    </body>
</html>