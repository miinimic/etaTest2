<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=843ae0fd7d31559bce57a18dcd82bf62&libraries=services"></script>
	
	<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=843ae0fd7d31559bce57a18dcd82bf62"></script>
	         <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script>
	async function loadMapData() {
	    const apiUrl = 'https://apis-navi.kakaomobility.com/v1/directions?origin=127.03515132405035,37.500518493640655&destination=127.02902706106634,37.49943894990428';
	    console.log("Generated URL:", apiUrl);  

	    try {
	        const response = await fetch(apiUrl, {
	            method: 'get',
	            headers: {
	                "Content-Type": "application/json",
	                "Authorization": "KakaoAK bb9f3068bf970e08b9d0147524d0258f"
	            }
	        });

	        if (!response.ok) {
	            throw new Error("Failed to fetch data");
	        }

	        const data = await response.json();
	        console.log(data);
	        drawPolylineAndMoveMarker(data, map);
	    } catch (error) {
	        console.error("Error fetching data:", error);
	    }
	}

	loadMapData();

    	const drawPolylineAndMoveMarker = (data,map) => {
   	    const linePath = [];
   	    data.routes[0].sections[0].roads.forEach(router => {
   	        router.vertexes.forEach((vertex, index) => {
   	           if (index % 2 === 0) {
   	               const lat = router.vertexes[index + 1];
   	               const lng = router.vertexes[index];
   	               linePath.push(new kakao.maps.LatLng(lat, lng));
   	          
   	           }
   	        });
   	    });

   	    var polyline = new kakao.maps.Polyline({
   	        path: linePath,
   	        strokeWeight: 5,
   	        strokeColor: '#000000',
   	        strokeOpacity: 0.7,
   	        strokeStyle: 'solid'
   	      }); 
   	      polyline.setMap(map);
   	      
   	// 마커를 생성하고 지도에 표시합니다.
   	    let marker = new kakao.maps.Marker({
   	        map: map,
   	        position: linePath[0], // 폴리라인의 시작점에 마커를 배치합니다.
   	    });
   	
   	// 마커를 이동시킬 인덱스 변수를 초기화합니다.
   	    let index = 0;
   	
   	 // 일정 시간 간격으로 마커를 이동시키는 함수입니다.
   	    const moveMarker = () => {
   	        if (index < linePath.length) {
   	            // 현재 인덱스의 좌표로 마커를 이동시킵니다.
   	            marker.setPosition(linePath[index]);
   	            map.setCenter(linePath[index]);
   	         sendLocationToServer(linePath[index]);
	            //////////////////////////////////////////////////////////위치보내기
  
   	            index++;
   	        } else {
   	            // 폴리라인의 끝에 도달했다면, 인터벌을 중단합니다.
   	            clearInterval(intervalId);
   	        }
   	    };

   	    // 1초마다 마커를 이동시키기 위한 인터벌 설정
   	    const intervalId = setInterval(moveMarker, 500);
   	};
   	
   	var firstLocation = null;
    var lastLocation = null;
   	var passengerNo = "${passengerNo}";
   	var stompClient = null; // 전역 스코프에서 stompClient 초기화
   	function sendLocationToServer(index) {
 	    if (stompClient && stompClient.connected) {
 	        const location = index;
 	        const locationData = { lat: location.getLat(), lng: location.getLng() };
 	        stompClient.send("/app/sendLocation" + passengerNo, {}, JSON.stringify(locationData));
 	        
 	        
 	        
 	       if (!firstLocation) {
               firstLocation = locationData;
           }

           // 마지막 위치 데이터 갱신
           lastLocation = locationData;
 	    } else {
 	        console.error("Websocket is not connected.");
 	    }
 	}
   	function connectWebSocket() {
 	    var socket = new SockJS('/ws'); // '/ws'는 서버의 웹소켓 연결 URL
 	    stompClient = Stomp.over(socket);

 	    stompClient.connect({}, function (frame) {
 	    		console.log('Connected: ' + frame);
 	    		// 추가 구독 설정
 	    		
 	    		socket.onclose = function () {
 	            console.log('WebSocket connection closed');
 	        	};
 			}, function (error) {
 	    		console.error('Websocket connection error: ', error);
 			
 			});
 	}
	connectWebSocket();
	
	
	
	
	
	


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
	                callNo: ${callNo}
	            }),
	            success: function(response) {
	                console.log('서버에 데이터 전송 성공:', response);
	                window.location.href = '/callres/home.jsp';//feedback으로 나중에 바꿔야됌
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
</head>
<body>
	<div class="col-md-6">
    	<div id="map" style="width:100%;height:710px;"></div>
    </div>
  
		<button onclick="sendLocationDataToServer()">운행종료</button>
    
    
    
    
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=843ae0fd7d31559bce57a18dcd82bf62&libraries=services"></script>
          <script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=843ae0fd7d31559bce57a18dcd82bf62"></script>
    <script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(37.4939072071976, 127.0143838311636), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };
  
var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다


    </script>
</body>
</html>