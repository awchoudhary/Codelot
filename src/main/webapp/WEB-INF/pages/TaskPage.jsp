<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Task Page</title>
    <link rel="stylesheet" type="text/css" href="../stylesheets/main.css" />
    <link rel="stylesheet" type="text/css" href="../stylesheets/map.css" />


    <link rel="stylesheet" type="text/css" href="../stylesheets/bootstrap-3.3.7-dist/css/bootstrap.min.css" />

    <link rel="icon" type="image/png" href="../images/CodelotShield.png" />
    <script src="../scripts/jquery-3.2.0.min.js"></script>
    <script src="../scripts/bootstrap.min.js"></script>

    <style>
        .leftcol {
            width:170px;
            height:auto;
            float:left;
        }

        .floorIcon {
            width:930px
            height:auto;
            float:left;
        }
        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            padding-top: 100px; /* Location of the box */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }

        /* Modal Content */
        .modal-content {
            /*background-color: #fefefe;*/
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            color: black;
        }

        /* The Close Button */
        .close {
            color: #aaaaaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }
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
            $("#taskLink").click(function() {
                $("#submitTask").click();
            });
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
                    <a id="taskLink">Return to Map</a>
                    <button style="display:none" id="submitTask" title="Task" type="submit"
                            onclick="form.action='/map';">Task</button>
                    <a id="settingsLink">Settings</a>
                    <button style="display:none" id="submitSettings" title="Settings" type="submit"
                            onclick="form.action='/changeSettings1';">Settings</button>
                </form>
            </div>
        </div>
    </nav>
    <div class="content-wrapper">
        <div class="content-row">
            <%-- Main Pane --%>
            <div class="column-4 wood-bg w-col w-col-10">
                <%--left column--%>
                <div class="leftcol" id="flrs">
                    <table>
                    <c:forEach var="floor" items="${floors}">
                        <tr>
                            <td>
                                <form id="floorsForm" method="post">
                                <div class=floorIcon">
                                    <button class="button w-button" title="Floor" type="submit"
                                            onclick="form.action='/getJavaTask';">Floor ${floor.index+1}</button>
                                    <input type="hidden" value="${floor.index}" name="floorNum"/>
                                </div>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </table>
                </div>

                <%--center content--%>
                <div id="taskDescription">
                    <h5>${warning}</h5>
                    <h4 id="flrdesc">${taskDesc}</h4>
                </div>
                <div id="compiler">
                    <p><textarea rows="20" cols="50" id="source"></textarea></p>
                    <p><button class="button w-button" id="btn_execute"><strong>Execute</strong></button></p>
                    <p><div id="output"><!-- Displays output for program --></div></p>
                    <p><div id="compileMessage"><!-- Displays message from compiler --></div></p>
                </div>
            </div>

            <%-- right column --%>
            <div class="column-3 w-col w-col-2">
                <div class="buttonblock">
                    <div class="text-block">
                        <div id="hintSection">
                        <button class="button w-button" id="hint_btn" title="hint">Hints</button>
                        <div id="hintModal" class="modal">
                            <!-- Modal content -->
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <h4>Hints</h4>
                                <ol>
                                    <c:forEach var="hnt" items="${hints}">
                                        <li>${hnt}</li>
                                    </c:forEach>
                                </ol>
                            </div>

                            <script>
                                // hint modal
                                var hintModal = document.getElementById("hintModal");
                                var hintbtn = document.getElementById("hint_btn");
                                var btn_span = document.getElementsByClassName("close")[0];

                                hintbtn.onclick = function() {
                                    hintModal.style.display = "block";
                                }
                                btn_span.onclick = function() {
                                    hintModal.style.display = "none";
                                }
                                window.onclick = function(event) {
                                    if (event.target == hintModal) {
                                        hintModal.style.display = "none";
                                    }
                                }
                            </script>
                        </div>
                        </div>

                        <div id="attSection">
                        <button class="button w-button" id="attempts_btn" title="attempts">Attemps</button>
                        <div id="attModal" class="modal">
                            <!-- Modal content -->
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <h4>Previous Attemps</h4>
                                <ol>
                                    <c:forEach var="attempt" items="${attempts}">
                                        <li>${attempt}</li>
                                    </c:forEach>
                                </ol>
                            </div>

                            <script>
                                // attempts modal
                                var attModal = document.getElementById("attModal");
                                var attbtn = document.getElementById("attempts_btn");
                                var btn_span = document.getElementsByClassName("close")[1];

                                attbtn.onclick = function() {
                                    attModal.style.display = "block";
                                }
                                btn_span.onclick = function() {
                                    attModal.style.display = "none";
                                }
                                window.onclick = function(event) {
                                    if (event.target == attModal) {
                                        attModal.style.display = "none";
                                    }
                                }
                            </script>
                        </div>
                        </div>

                    </div>
                </div>
                <hr />

                <%-- Progress information --%>
                <div class="buttonblock">
                    <div class="text-block">

                        <div id="lessonSection">
                            <button class="button w-button" id="lesson_btn" title="lesson">Lesson</button>
                            <div id="lessonModal" class="modal">
                                <!-- Modal content -->
                                <div class="modal-content">
                                    <span class="close">&times;</span>
                                    <h4>Review Lesson</h4>
                                    <p>${lesson}</p>
                                </div>

                                <script>
                                    // attempts modal
                                    var lessonModal = document.getElementById("lessonModal");
                                    var lessonbtn = document.getElementById("lesson_btn");
                                    var btn_span = document.getElementsByClassName("close")[2];

                                    lessonbtn.onclick = function() {
                                        lessonModal.style.display = "block";
                                    }
                                    btn_span.onclick = function() {
                                        lessonModal.style.display = "none";
                                    }
                                    window.onclick = function(event) {
                                        if (event.target == lessonModal) {
                                            lessonModal.style.display = "none";
                                        }
                                    }
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</body>
</html>