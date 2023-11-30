<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=843ae0fd7d31559bce57a18dcd82bf62&libraries=services"></script>
          <script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=843ae0fd7d31559bce57a18dcd82bf62"></script>
</head>
<body>
    <div class="col-md-6">
        <div id="map" style="width:100%;height:710px;"></div>
    </div>
     <button id="endTripButton">운행 종료</button>

    <script>
    document.getElementById('endTripButton').addEventListener('click', function() {
        sendLocationDataToServer();
    });
    
    
    var mapContainer = document.getElementById('map'),
        mapOption = {
            center: new kakao.maps.LatLng(37.4939072071976, 127.0143838311636),
            level: 3
        };

    var map = new kakao.maps.Map(mapContainer, mapOption);
    var marker = new kakao.maps.Marker({map: map}); // 전역 스코프에서 마커 정의
	var userId = 'user01';
    var stompClient = null;
    var firstLocation = null;
    var lastLocation = null;

    function connect() {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);

            stompClient.subscribe('/topic/location/' + userId, function (message) {
                var locationData = JSON.parse(message.body);
                console.log("Received location for " + userId + ": ", locationData.lat, locationData.lng);
                if (!firstLocation) {
                    firstLocation = locationData;
                }

                // 마지막 위치 데이터 갱신
                lastLocation = locationData;
                console.log(locationData);
                moveMarker(locationData);
            });
        });
    }

    window.onload = function() {
        connect();
    };

    function moveMarker(locationData) {
        var newPosition = new kakao.maps.LatLng(locationData.lat, locationData.lng);
        marker.setPosition(newPosition);
        map.setCenter(newPosition);
    }
    
    
    function sendLocationDataToServer() {
        if (firstLocation && lastLocation) {
            // 서버에 AJAX 요청 보내기
            $.ajax({
                url: '/callres/callEnd', // 컨트롤러 URL
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    startX: firstLocation.lng,
                    startY: firstLocation.lat,
                    endX: lastLocation.lng,
                    endY: lastLocation.lat,
                }),
                success: function(response) {
                    console.log('서버에 데이터 전송 성공:', response);
                },
                error: function(error) {
                    console.error('서버에 데이터 전송 실패:', error);
                }
            });
        } else {
            console.error('위치 데이터가 충분하지 않습니다.');
        }
    }
    </script>
</body>
</html>