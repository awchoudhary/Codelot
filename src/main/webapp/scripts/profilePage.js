/**
 * Created by Jane on 5/11/2017.
 */
$(function() {
    $("#resetLink").click(function() {
        $("#submitReset").click();
    });

    $("#showReset").click(function() {
        if($("#resetWrapper").is(":hidden"))
            $("#resetWrapper").show(700);
        else
            $("#resetWrapper").hide(700);

    });
});