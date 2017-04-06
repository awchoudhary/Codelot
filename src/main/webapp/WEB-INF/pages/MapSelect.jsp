<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Codelot</title>
        <link rel="stylesheet" type="text/css" href="../stylesheets/main.css" />

        <link rel="stylesheet" type="text/css" href="../stylesheets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

        <link rel="icon" type="image/png" href="../images/CodelotShield.png" />
        <script src="../scripts/jquery-3.2.0.min.js"></script>
        <script src="../scripts/bootstrap.min.js"></script>


        <script>
            $(function() {

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
                <a href="/">Home</a>
                <a role="button" data-toggle="modal" data-target="#faqModal">FAQ</a>
                <a>Settings</a>
                <form method="post">
                    <a onclick="form.action='/signout'">Signout</a>
                </form>
            </div>


        </div>
      </nav>
      <div class="content-wrapper">
        <div class="content-row">
          <div class="column-4 wood-bg w-col w-col-10">
            <h1 class="heading-6"><strong>Choose a language</strong></h1>
            <div class="row w-row">
              <div class="mapWrapper w-col w-col-4"><img class="map" src="https://d3e54v103j8qbb.cloudfront.net/img/image-placeholder.svg" width="330">
                <div class="caption-1">Java-Ville</div>
              </div>
              <div class="mapWrapper w-col w-col-4"><img class="map" src="https://d3e54v103j8qbb.cloudfront.net/img/image-placeholder.svg" width="330">
                <div class="caption-1">JavaScript-Cove</div>
              </div>
              <div class="mapWrapper w-col w-col-4"><img class="map" src="https://d3e54v103j8qbb.cloudfront.net/img/image-placeholder.svg" width="330">
                <div class="caption-1">Python-Valley</div>
              </div>
            </div>
            <div class="div-block"><a class="button w-button"><strong>Enter</strong></a>
            </div>
          </div>
          <div class="column-3 w-col w-col-2">
            <div class="buttonblock">
              <div class="text-block"><strong>About Java</strong>
              </div>
              <p class="paragraph-6">Here goes information about each languages they are selected.
                <br>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse varius enim in eros elementum tristique. Duis cursus, mi quis viverra ornare, eros dolor interdum nulla, ut commodo diam libero vitae erat. Aenean faucibus nibh et justo cursus id rutrum lorem imperdiet. Nunc ut sem vitae risus tristique posuere.</p>
            </div>
          </div>
        </div>
      </div>
        <div class="footer-bottom">
          <div class="footer-bottom-wrap">
            <div class="legal">Information about the group and other footer content. Design made using Webflow.</div>
          </div>
        </div>

        <!-- FAQ -->
          <div class="modal fade" id="faqModal" role="dialog">
            <div class="modal-dialog">

              <!-- Modal content-->
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                  <h4 class="modal-title">FAQ</h4>
                </div>
                <div class="modal-body">
                  <p>FAQ content goes here</p>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
              </div>

            </div>
          </div>
    </body>
</html>