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