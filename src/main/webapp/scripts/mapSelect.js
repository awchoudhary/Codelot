var languages = {
    java : {
        name : "Java",
        desc : "Java Description"
    },
    javascript : {
        name : "JavaScript",
        desc : "JavaScript Description"
    },
    python : {
        name : "Python",
        desc : "Python Description"
    }
 };

 $(function() {
    $("#signoutLink").click(function() {
        $("#submitSignout").click();
    });
    $("#settingsLink").click(function() {
        $("#submitSettings").click();
    });
    $("#homeLink, #logolink").click(function() {
        $("#submitHome").click();
    });

    $(".mapWrapper").click(selectMap);
 });

 function selectMap() {
    var selected = $(this);

    if (selected.hasClass("selectedMap")) {
        selected.removeClass("selectedMap");
        setAbout();
        return;
    }

    $(".mapWrapper").each(function() {
        $(this).removeClass("selectedMap");
    });

    selected.addClass("selectedMap");
    var language = languages[selected.attr("id")];

    setAbout(language.name, language.desc);
 }

 function setAbout(name, desc) {
    if(!name)
        $("#aboutLanguage").text("");
    else
        $("#aboutLanguage").text("About " + name);


    if(!desc)
        $("#languageDescription").text("");
    else
        $("#languageDescription").text(desc);
 }