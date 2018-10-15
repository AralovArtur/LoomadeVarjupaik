$(document).ready(function () {

    //Product add form
    $("#lisaAvaldus").submit(function (event) {
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        ajaxPost();
    })

});

function ajaxPost(){
    //Prepare form data
    var formData = {
        liik: $("#liik").val(),
        vanus: $("#vanus").val(),
        linn: $("#linn").val(),
    };

    //DO POST
    //Send String data
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : window.location + "/save",
        data : JSON.stringify(formData),
        dataType : 'html',
        success : function(data){
            $("#lisaAvaldus").text(data);
            console.log(data);
        },
        error : function (e) {
            alert("Error!");
            console.log("ERROR: ",e);
        }
    });

    resetData();

}

function resetData(){
    $("#liik").val(""),
        $("#vanus").val(""),
        $("#linn").val("")
}