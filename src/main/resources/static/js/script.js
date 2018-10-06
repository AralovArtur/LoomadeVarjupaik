function outputrequest() {
    alert("Request has been send")
}

// Initialize and add the map
function initMap() {
    var tartuLoomadeVP = {lat: 58.3904724, lng: 26.7462889};
    var map = new google.maps.Map(
        document.getElementById('map'), {zoom: 9, center: tartuLoomadeVP});
    // The marker, positioned at Uluru

    var image = "css/images/marker.png";
    marker = new google.maps.Marker({
        map: map,
        animation: google.maps.Animation.DROP,
        position: {lat: 58.3904724, lng: 26.7462889},
        icon: image
    });
    marker.addListener('click', toggleBounce);
}

function toggleBounce() {
    if (marker.getAnimation() !== null) {
        marker.setAnimation(null);
    } else {
        marker.setAnimation(google.maps.Animation.BOUNCE);
    }
}

//Praktikum 5
var stompClient = null;
var notificationsContainer = null;

function connect() {
    var socket = new SockJS('/my-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/subscriptions', function (greeting) {
            var subscription = JSON.parse(greeting.body);
            console.log(subscription);
            showNotification(subscription);
        });
    });
}

function showNotification(subscription) {
    var notificationMessage = $(
        "<div class=\"alert alert-info\"><strong>Info</strong><br>" +
        subscription.name.toUpperCase() + " subscribed with e-mail: " + subscription.email +
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