<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Codelot</title>
        <link rel="stylesheet" type="text/css" href="../stylesheets/main.css" />
        <link rel="stylesheet" type="text/css" href="../stylesheets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

        <link rel="icon" type="image/png" href="../images/CodelotShield.png" />
        <script src="../scripts/jquery-3.2.0.min.js"></script>

        <script>
            $(function() {
                $(".nav-links > a").click(function() {
                    var scrollid = $(this).text();
                    $("html, body").animate({
                        scrollTop: $("#" + scrollid).position().top,
                        duration: 800
                    });
                });
            });
        </script>

    </head>

    <body>
        <nav class="navbar nav-main navbar-fixed-top">
            <div class="nav-container">
                <a class="logo" href="#">
                    <img sizes="131px" src="../images/CodelotLogo.png" width="131">
                </a>
                <div class="nav-links">
                    <a>Home</a>
                    <a>About</a>
                    <a>FAQ</a>
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
            </div>
        </div>
        <div class="logos-section">
            <div>
                <p class="paragraph-5">Student information can go down here, along with contact and social media.</p>
            </div>
        </div>

        <div id="About" class="class-section">
            <div class="a wrap-2">
                <div class="left-1">
                    <h2 class="heading-2">What is Codelot?</h2>
                    <p class="paragraph-2">If you want to learn how to code and love playing games then Codelot is for you !! Complete an adventure within the Codelot Kingdom with your chosen avatar and learn how to code in a variety of coding languages at the same time. </p>
                </div>
                <div class="right-1"><img class="screenshot" sizes="(max-width: 767px) 63vw, (max-width: 991px) 35vw, 300px" src="images/pythonworld_overview.png" srcset="images/pythonworld_overview.png 500w, images/RPGScreenshot.png 646w" width="300">
                    <p class="caption-2">Image from Codelot.</p>
                </div>
            </div>
            <div class="b wrap-2">
                <div class="left-2"><img class="screenshot" sizes="(max-width: 479px) 80vw, 300px" src="images/javascriptworld_overview.png" srcset="images/javascriptworld_overview.png 500w, images/RPGScreenshot.png 646w" width="300">
                    <p class="caption-3">Image from Codelot.</p>
                </div>
            <div class="right-2">
                <h2 class="heading-2">Virtual Coding Kingdom</h2>
                <p class="paragraph-2">There are currently three regions corresponding to three different coding languages within the Codelot kindgom. Unlock all the buildings in each region to master the language  </p>
            </div>
        </div>
    </div>

    <div id="FAQ" class="section-3">
        <div class="wrap-3">
            <h2 class="heading-5">Frequently Asked Questions</h2>
            <ul>
                <li>Question 1 - click to expand</li>
                <li>Question 2</li>
                <li>Question 3</li>
            </ul>
        </div>
    </div>
    <div class="footer-bottom">
        <div class="footer-bottom-wrap">
            <div class="legal">Information about the group and other footer content. Design made using Webflow.</div>
        </div>
    </div>
    <div class="w-embed">
        <style>
            .xxx { background: rgba(0,0,0,0.05); }
        </style>
    </div>

    </body>
</html>