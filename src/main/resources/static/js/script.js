function outputrequest() {
    alert("Request has been send")
}

// Initialize and add the map
function initMap() {
    var tartuLoomadeVP = {lat: 58.3904724, lng: 26.7462889};
    var map = new google.maps.Map(
        document.getElementById('map'), {zoom: 9, center: tartuLoomadeVP});
    // The marker, positioned at Uluru
    var marker = new google.maps.Marker({position: tartuLoomadeVP, map: map});
}