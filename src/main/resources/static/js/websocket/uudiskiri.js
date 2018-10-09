var stompClient = null;
var notificationsContainer = null;

function connect() {
    var socket = new SockJS('/my-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/uudiskiri', function (greeting) {
            var uudiskiri = JSON.parse(greeting.body);
            console.log(uudiskiri);
            showNotification(uudiskiri);
        });
    });
}

function showNotification(uudiskiri) {
    var notificationMessage = $(
        "<div class=\"alert alert-info\"><strong>Info</strong><br>" +
        uudiskiri.sonum.toUpperCase() + uudiskiri.email +
        "</div>");
    notificationsContainer.append(notificationMessage);

    setTimeout(function() {
        notificationMessage.remove();
    }, 3000);
}

$(function () {
    notificationsContainer = $("#subscription-notifications-container");

    // On page load, connect to websocket
    connect();

});