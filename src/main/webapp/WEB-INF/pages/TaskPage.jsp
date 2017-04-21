<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Task Page</title>
    <link rel="stylesheet" type="text/css" href="../stylesheets/main.css" />
    <link rel="stylesheet" type="text/css" href="../stylesheets/map.css" />
    <link rel="stylesheet" type="text/css" href="../stylesheets/task.css" />

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="../stylesheets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

    <link rel="icon" type="image/png" href="../images/CodelotShield.png" />
    <script src="../scripts/jquery-3.2.0.min.js"></script>
    <script src="../stylesheets/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

    <style>

    </style>

    <script>
        $(function() {
            //assign click events
            $("#btn_execute").click(function(){
                execute();
            });
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
            $("#mapLink").click(function() {
                $("#submitMap").click();
            });

            $("#lessonLink").click(function(){
                $("#startTask").text("Close");
                $("#lessonModal").modal();
            });

            $("#hintLink").click(function() {
                $("#hintModal").modal();
            });

            $("#attemptLink").click(function() {
                $("#attemptModal").modal();
            });

            $("#lessonModal").modal();
        });

        function execute(){
            //empty messages
            $("#output").html("");
            $("#compileMessage").html("");

            //get code to execute
            var source = $("#source").val();
            var params = {source: source};

            //post to execute controller
            $.ajax({
                     type: "POST",
                     contentType: "application/json",
                     url: "/execute",
                     data: JSON.stringify(params),
                     dataType: 'json',
                     success: function (data) {
                        //print program output if applicable
                        if(data.result.stdout && data.result.stdout.length > 0){
                            var output = "";
                            for(var i = 0; i < data.result.stdout.length; i++){
                                output += data.result.stdout[i] + "<br>";
                            }
                            $("#output").html("Output: " + output);
                        }
                        //print compilemessage if there is a run-time error
                        if(data.result.compilemessage && data.result.compilemessage != ""){
                            $("#compileMessage").html("Compile Message: " + data.result.compilemessage);
                        }
                     },
                     error: function (e) {
                         alert("Error");
                     }
            });
        }

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
                    <a id="homeLink">Home</a>
                    <button style="display:none" id="submitHome" title="Home" type="submit"
                            onclick="form.action='/';">Home</button>
                    <a id="lessonLink">Review Lesson</a>
                    <a id="hintLink">Hint</a>
                    <a id="attemptLink">Attempts</a>
                    <a id="mapLink">Quit</a>
                    <button style="display:none" id="submitMap" title="Map" type="submit"
                            onclick="form.action='/map';">Map</button>


                </form>
            </div>
        </div>
    </nav>
    <div class="content-wrapper">
        <div class="content-row">
            <%-- Main Pane --%>
            <div class="column-4 wood-bg w-col w-col-10">
                <h2>Basics</h2>
                <div class="progress">
                    <div class="progress-bar" role="progressbar" aria-valuenow="${progress}"
                         aria-valuemin="0" aria-valuemax="100" style="width:${progress}%; background-color:maroon;">
                        <span class="sr-only">${progress}% Completed</span>
                    </div>
                    <p>${progress}% Completed</p>
                </div>

                <%--center content--%>
                <div id="taskDescription">
                    <h5>${warning}</h5>
                    <h4 id="flrdesc">${taskDesc}</h4>
                </div>
                <div id="compiler">
                    <p><textarea rows="20" cols="50" id="source">${baseCode}</textarea></p>
                    <p><button class="button w-button" id="btn_execute"><strong>Execute</strong></button></p>
                    <p><div id="output"><!-- Displays output for program --></div></p>
                    <p><div id="compileMessage"><!-- Displays message from compiler --></div></p>
                </div>
            </div>

                <%-- right column --%>
                <div class="column-3 w-col w-col-2">
                    <div class="buttonblock">
                        <div class="text-block" id="flrs">
                            <table id="floorsTable">
                                <c:forEach var="floor" items="${floors}">
                                    <tr>
                                        <td>
                                            <form id="floorsForm" method="post">
                                                <div class="floorWrapper">
                                                    <a class="floor" onclick="$('#Floor${floor.index}').click();">Floor ${floor.index+1}</a>
                                                    <button style="display:none" id="Floor${floor.index}" title="Floor" type="submit"
                                                            onclick="form.action='/getJavaTask';">Floor ${floor.index+1}</button></a>
                                                        <input type="hidden" value="${floor.index}" name="floorNum"/>
                                                </div>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>

        </div>
    </div>

    <div class="footer-bottom">
        <div class="footer-bottom-wrap">
            <div class="legal">Information about the group and other footer content. Design made using Webflow.</div>
        </div>
    </div>

    <%-- Modals --%>

    <!-- Lesson Modal -->
    <div class="modal fade" id="lessonModal" role="dialog">
        <div class="modal-dialog">

          <!-- Modal content-->
          <div class="modal-content">
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h4 class="modal-title">Lesson</h4>
            </div>
            <div class="modal-body">
              <p>${lesson}</p>
            </div>
            <div class="modal-footer">
              <button type="button" id="startTask" class="btn btn-default" data-dismiss="modal">Start</button>
            </div>
          </div>

        </div>
    </div>

    <!-- Hints Modal -->
    <div id="hintModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content -->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Hints</h4>
                </div>

                <div class="modal-body">
                    <ol>
                      <c:forEach var="hnt" items="${hints}">
                          <li>${hnt}</li>
                      </c:forEach>
                    </ol>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Attempts Modal -->
    <div id="attemptModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content -->
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Previous Attempts</h4>
                </div>

                <div class="modal-body">
                    <ol>
                        <c:forEach var="attempt" items="${attempts}">
                            <li>${attempt}</li>
                        </c:forEach>
                    </ol>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>

            </div>

        </div>
    </div>


</body>
</html>