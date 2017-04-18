<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task Page</title>
    <script src="../scripts/jquery-3.2.0.min.js"></script>

    <script>
        $(function() {
            //assign click events
            $("#btn_execute").click(function(){
                execute();
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
    <div>
        <textarea rows="20" cols="50" id="source"></textarea>
        <a class="button w-button" id="btn_execute"><strong>Execute</strong></a>
        <div id="output"><!-- Displays output for program --></div>
        <div id="compileMessage"><!-- Displays message from compiler --></div>
    </div>
</body>
</html>