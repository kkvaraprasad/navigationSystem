function submitPath(){
    $.ajax({url: "/navFileReader?", success: function(result){
        $("#result").html(result);
    }});
}