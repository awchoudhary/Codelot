var languages = {
    java : {
        name : "Java",
        desc : "Java is an Object Oriented Programming language used to create web and native applications"
    },
    javascript : {
        name : "JavaScript",
        desc : "JavaScript is a scripting programming language  used to create dynamic content on webpages"
    },
    python : {
        name : "Python",
        desc : "Python is a popular multipurpose programming language. The use for python spans to both front end(web pages) and back end"
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
     $("#resetLink").click(function() {
         $("#submitReset").click();
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