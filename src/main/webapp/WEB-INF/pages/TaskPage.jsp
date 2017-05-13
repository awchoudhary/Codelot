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
    <script src="../scripts/taskPage.js"></script>
    <script src="../scripts/base.js"></script>


    <style></style>
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
                    <a style="display:none" id="homeLink">Home</a>
                    <button style="display:none" id="submitHome" title="Home" type="submit"
                            onclick="form.action='/';">Home</button>
                    <a id="lessonLink">Review Lesson</a>
                    <a id="hintLink">Hint</a>
                    <a id="attemptLink">Attempts</a>
                    <a id="mapLink">Quit</a>
                    <button style="display:none" id="submitMap" title="Map" type="submit"
                            onclick="form.action='/map';">Map</button>
                    <input type="hidden" value="${languageCode}" name="selectedLang"/>
                </form>
            </div>
        </div>
    </nav>
    <div class="content-wrapper">
        <div class="content-row">
            <%-- Main Pane --%>
            <div class="column-4 wood-bg w-col w-col-10">
                <h2>${buildingName}</h2>
                <hr />

                <%--center content--%>
                <!-- Left-aligned -->
                <div class="media char">
                  <div class="media-left">
                    <img src="../images/img_avatar1.png" class="media-object" style="width:60px">
                  </div>
                  <div class="media-body">
                    <h4 class="media-heading">Java Wizard</h4>
                      <p style="font-size:75%"><em>${warning}</em></p>
                    <p>${taskDesc}</p>
                  </div>
                </div>

                <div id="compiler">
                    <p><textarea rows="20" cols="50" id="source">${baseCode}</textarea></p>
                    <p><button data-loading-text="<img src='../images/loading.gif' class='loading' /> Executing"
                        class="button w-button" id="btn_execute">Execute</button></p>
                </div>
            </div>

                <%-- right column --%>
                <div class="column-3 w-col w-col-2">
                    <p id="progess_message" style="color:white;">${progress}% Completed</p>
                    <div class="progress">
                        <div id="progress-bar" class="progress-bar" role="progressbar" aria-valuenow="${progress}"
                             aria-valuemin="0" aria-valuemax="100" style="width:${progress}%; background-color:maroon;">
                            <span class="sr-only">${progress}%</span>
                        </div>
                    </div>

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
                                                            onclick="form.action='/tasks/task';">Floor ${floor.index+1}</button></a>
                                                    <input type="hidden" value="${floor.index}" name="floorNum"/>
                                                    <input type="hidden" value="${numBuilding}" name="numBuilding"/>
                                                    <input type="hidden" value="${languageCode}" name="languageCode"/>
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

    <!-- Results Modal -->
    <div id="compileModal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content -->
            <div class="modal-content">

                <div id="resultHeader" class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 id="resultTitle" class="modal-title">Result</h4>
                </div>

                <div class="modal-body">
                    <p><div id="output"><!-- Displays output for program --></div></p>
                    <p><div id="expectedOutput"><!-- Displays the expected output for the task--></div></p>
                    <p><div id="compileMessage"><!-- Displays message from compiler --></div></p>
                    <input type="hidden" id="currentFloor" value="${currentFloor}"/>
                    <input id="numBuilding" type="hidden" value="${numBuilding}"/>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>

            </div>

        </div>
    </div>
        <input type="hidden" id="languageCode" value="${languageCode}"/>

</body>
</html>