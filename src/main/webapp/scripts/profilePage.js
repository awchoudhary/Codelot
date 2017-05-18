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

    var currentAvatar = $("#currentAvatar").val();
    if (currentAvatar)
        $("option[value='" + currentAvatar + "']").attr("selected", "selected");
    else
        switchImage();
});

var switchImage = function () {
    var selectedImage = document.getElementById('avtr').value;
    document.getElementById('image').src = selectedImage;
}