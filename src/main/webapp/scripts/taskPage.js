//called on page load
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
    $("#btn_execute").button("loading");

    //empty messages
    $("#result").html("");
    $("#output").html("");
    $("#expectedOutput").html("");
    $("#compileMessage").html("");

    //get parameters
    var source = $("#source").val();
    var currentFloor = $("#currentFloor").val();
    var numBuilding = $("#numBuilding").val();
    var params = {source: source, currentFloor: currentFloor, numBuilding: numBuilding};

    var resultSuccess = function(data) {
        $("#resultHeader").removeClass("failed").addClass("success");
        $("#resultTitle").text("Success");

        //update the progress bar
        $("#progess_message").html(data.progress + "% Completed");
        $("#progress-bar").css("width", data.progress + "%");
    }

    var resultFailed = function(data) {
        $("#resultHeader").removeClass("success").addClass("failed");
        $("#resultTitle").text("Failed");
    }

    //post to execute controller
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/task/execute",
        data: JSON.stringify(params),
        dataType: 'json',
        success: function (data) {
            //print compilemessage if there is a run-time error
            if(data.compilemessage){
                $("#compileMessage").html("Error: " + data.compilemessage);
            }
            else{
                //display the outcome
                if(data.outcome == "true"){
                    resultSuccess(data);
                }
                else{
                    resultFailed(data);
                }

                //show the program output and expected output. TODO: Print out all outputs
                $("#output").html("Your Output: " + data.stdout[0]);
                $("#expectedOutput").html("Expected Output: " + data.expectedOutputs[0]);
            }
            $("#compileModal").modal();
            $("#btn_execute").button("reset");
        },
        error: function (e) {
            alert("Error");
            $("#btn_execute").button("reset");
        }
    });
}