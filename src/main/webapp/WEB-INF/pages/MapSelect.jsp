<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Codelot</title>
        <link rel="stylesheet" type="text/css" href="../stylesheets/main.css" />
        <link rel="stylesheet" type="text/css" href="../stylesheets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

        <link rel="icon" type="image/png" href="../images/CodelotShield.png" />
        <script src="../scripts/jquery-3.2.0.min.js"></script>

        <script>

        </script>

    </head>
    <body>
      <form method="post">
        <button title="Sign Out" type="submit" onclick="form.action='/signout';">Sign Out</button>
      </form>
      <nav class="navbar nav-main navbar-fixed-top">
        <div class="nav-container">
            <a class="logo" href="#">
                <img sizes="131px" src="../images/CodelotLogo.png" width="131">
            </a>
            <div class="nav-links">
                <a>Home</a>
                <a>FAQ</a>
                <a>Settings</a>
                <a>Signout</a>
            </div>


        </div>
      </nav>
        <div class="hero-section">
            <div class="hero-wrap">
              <div class="hero-left">
                <h1 class="heading-1"><strong>Learn to code</strong></h1>
                <p class="subhead-1">Info about website goes here and it will be an informative paragraph that gives the user an idea of what they're getting themselves into when clicking the play button.</p><a class="button w-button"><strong>Play</strong></a>
              </div>
              <div class="hero-right"><img src="images/RPGScreenshot.png" width="331">
                <p class="caption-1">Replaced with screenshot of application</p>
              </div>
            </div>
          </div>
          <div class="logos-section">
            <div>
              <p class="paragraph-5">Student information can go down here, along with contact and social media.</p>
            </div>
          </div>
          <div class="class-section">
            <div class="a wrap-2">
              <div class="left-1">
                <h2 class="heading-2">What is Codelot?</h2>
                <p class="paragraph-2">Brief description of Codelot. Here the user can find information about the Codelot experience and what we offer. This is going to be in paragraph form.</p>
              </div>
              <div class="right-1"><img class="screenshot" src="images/RPGScreenshot.png" width="300">
                <p class="caption-2">Image from Codelot.</p>
              </div>
            </div>
            <div class="b wrap-2">
              <div class="left-2"><img class="screenshot" src="images/RPGScreenshot.png" width="300">
                <p class="caption-3">Image from Codelot.</p>
              </div>
              <div class="right-2">
                <h2 class="heading-2">Virtual Coding Kingdom</h2>
                <p class="paragraph-2">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent luctus aliquet neque, sit amet pretium velit mattis ac. Aenean laoreet urna ac ex tempus, vitae bibendum enim consequat. Nulla facilisi.&nbsp;</p>
              </div>
            </div>
          </div>
          <div class="section-3">
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