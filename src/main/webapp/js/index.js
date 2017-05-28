
var countDownDate = new Date("Sep 23, 2017 00:00:00").getTime();

var x = setInterval(function() {

    var now = new Date().getTime();
    var distance = countDownDate - now;
    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
    $("#triptimer").text(days + "Days " + hours + "Hrs "
    + minutes + "Min " + seconds + "sec ");
    if (distance < 0) {
        clearInterval(x);
        $("#triptimer").text("EXPIRED");
    }
}, 1000);




$("#linkTeam").click(function(){
    $(".container").load("html/team.html");

});


$("#linkPlan").click(function(){
    $(".container").load("html/plan.html");

});

$("#linkMap").click(function(){
    $(".container").load("html/map.html");

});

$("#linkPlaces").click(function(){
    $(".container").load("html/places.html");

});


$("#linkExpense").click(function(){
    $(".container").load("html/expense.html");

});

toastr.options = {
      "closeButton": true,
      "debug": false,
      "newestOnTop": false,
      "progressBar": false,
      "positionClass": "toast-bottom-right",
      "preventDuplicates": false,
      "onclick": null,
      "showDuration": "300",
      "hideDuration": "1000",
      "timeOut": "3000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut"
    }