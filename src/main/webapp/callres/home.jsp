<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	
<head>
	<meta charset="UTF-8">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--   jQuery , Bootstrap CDN  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   
	
	<!--  CSS 추가 : 툴바에 화면 가리는 현상 해결 :  주석처리 전, 후 확인-->
	<style>
        body {
            padding-top : 70px;
        }
   	</style>
   	
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
       <script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=843ae0fd7d31559bce57a18dcd82bf62&libraries=services"></script>
          <script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=843ae0fd7d31559bce57a18dcd82bf62"></script>
         <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
     <script>

     
     const fetchPost = async (destX, destY) => {
             console.log("fetchPost");

             const originX = document.getElementById("originX").value;
             const originY = document.getElementById("originY").value;
             
             console.log("Origin X:", originX);
             const apiUrl = 'https://apis-navi.kakaomobility.com/v1/directions?origin=' + originX + ',' + originY + '&destination=' + destX + ',' + destY;
             
             console.log("Generated URL:", apiUrl);  
        	    try {
        	        const response = await fetch(apiUrl, {
        	            method: 'get',
        	            headers: {
        	                "Content-Type": "application/json",
        	                "Authorization": "KakaoAK bb9f3068bf970e08b9d0147524d0258f"
        	            }
        	        });

        	        // Check if the response is ok
        	        if (!response.ok) {
        	            throw new Error("Failed to fetch taxi fare");
        	        }

        	        const data = await response.json();
        	        console.log(data);

        	        const taxiFare = data.routes[0].summary.fare.taxi;
        	        console.log(taxiFare);
        	        
        	        
        	        

        	        


        	        displayTaxiFare(taxiFare);
        	        drawPolyline(data);
        	        drawPolylineAndMoveMarker(data,map3);
        	      
        	    } catch (error) {
        	        console.error("Error fetching taxi fare:", error);
        	        displayTaxiFare("Failed to fetch taxi fare");
        	    }
        	    
        	   
        	}

         const displayTaxiFare= (fare) => {
        	 $("#loanRate").text("택시비: " + fare + "원");

        	    console.log(`택시비: ${fare}원`);
        	    console.log("Inside displayTaxiFare:", fare);
        	}
         const drawPolyline = (data) => {
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
      	      polyline.setMap(map2);
      	}
         
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
       	      polyline.setMap(map3);
       	      
       	// 마커를 생성하고 지도에 표시합니다.
       	    let marker = new kakao.maps.Marker({
       	        map: map3,
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
       	    const intervalId = setInterval(1000);
       	};


       

         
         
         const keywordget = async () => {
             const startAd = document.getElementById("startAd").value;
             const apiUrl1 = 'https://dapi.kakao.com/v2/local/search/keyword.json?y=37.514322572335935&x=127.06283102249932&radius=20000&query=' + startAd;

             try {
                 const response = await fetch(apiUrl1, {
                     method: 'get',
                     headers: {
                         "Content-Type": "application/json;charset=UTF-8",
                         "Authorization": "KakaoAK bb9f3068bf970e08b9d0147524d0258f"
                     }
                 });

                 if (!response.ok) {
                     throw new Error("Failed to fetch location data");
                 }

                 const data = await response.json();

                 // Get the values
                 const destX = data.documents[0].x;
                 const destY = data.documents[0].y;

                 // Call fetchPost to get taxi fare
                 fetchPost(destX, destY);

             } catch (error) {
                 console.error("Error fetching location data:", error);
             }
         }
         
         
         navigator.geolocation.getCurrentPosition((position) => {
    	console.log(position)
    });


    var stompClient = null; // 전역 스코프에서 stompClient 초기화


    function sendLocationToServer(index) {
        if (stompClient && stompClient.connected) {
            const location = index;
            const locationData = { lat: location.getLat(), lng: location.getLng() };
            stompClient.send("/app/sendLocation", {}, JSON.stringify(locationData));
        } else {
            console.error("Websocket is not connected.");
        }
    }

    function connectWebSocket() {
        var socket = new SockJS('/ws'); // '/ws'는 서버의 웹소켓 연결 URL
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);

            // 필요한 경우 여기서 추가 구독을 설정할 수 있음
        }, function (error) {
            console.error('Websocket connection error: ', error);
        });
    }


    connectWebSocket();


     </script>
     
     <script>
    function sendRequestP() {
        window.location.href = '/callres/getRecordPassenger?callNo=1001';
    }
    function sendRequestD() {
        window.location.href = '/callres/getRecordDriver?callNo=1000';
    }
    function list() {
        window.location.href = '/callres/getRecordList';
    }
    function callAccept() {
        window.location.href = '/callres/callAccept?callNo=1004';
    }
</script>

	 	
	
</head>
	
<body>

 <button onclick="sendRequestP()">P레코드 조회</button>
  <button onclick="sendRequestD()">D레코드 조회</button>
    <button onclick="list()">list조회</button>
     <button onclick="callAccept()">수락</button>



	<!-- 참조 : http://getbootstrap.com/css/   : container part..... -->
	<div class="container">
         <h2>택시비 조회</h2>
Origin X: <input type="text" id="originX">
Origin Y: <input type="text" id="originY">
Start Address Keyword: <input type="text" id="startAd">

<input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>

<button onclick="keywordget()">택시비 조회</button>
<p id="loanRate"></p>
<div class="row">
        <!-- 첫번째 지도 컨테이너 -->
        <div class="col-md-6">
            <div id="map" style="width:100%;height:400px;"></div>
        </div>
        <!-- 두번째 지도 컨테이너 -->
        <div class="col-md-6">
            <div id="map1" style="width:100%;height:400px;"></div>
        </div>
        <div class="col-md-6">
            <div id="map2" style="width:100%;height:400px;"></div>
        </div>
        <input type="text" id="sample5_address" placeholder="주소">
        <div class="col-md-6">
            <div id="map3" style="width:100%;height:400px;"></div>
        </div>
    </div>
  	 </div>
  	 
          <script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=843ae0fd7d31559bce57a18dcd82bf62&libraries=services"></script>
          <script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=843ae0fd7d31559bce57a18dcd82bf62"></script>
<script>
var mapContainer1 = document.getElementById('map2'), // 지도를 표시할 div
mapOption = {
    center: new daum.maps.LatLng(37.494078464694255, 127.01382976861977), // 지도의 중심좌표
    level: 5 // 지도의 확대 레벨
};

//지도를 미리 생성
var map2 = new daum.maps.Map(mapContainer1, mapOption);
//주소-좌표 변환 객체를 생성
var geocoder = new daum.maps.services.Geocoder();
//마커를 미리 생성
var marker = new daum.maps.Marker({
position: new daum.maps.LatLng(37.537187, 127.005476),
map: map2
});




var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(37.4939072071976, 127.0143838311636), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    
    // 클릭한 위도, 경도 정보를 가져옵니다 
    var latlng = mouseEvent.latLng;

    // 위도와 경도 값을 originX와 originY 입력 상자에 설정합니다.
    document.getElementById("originY").value = latlng.getLat();
    document.getElementById("originX").value = latlng.getLng();

});

</script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  	 
  	 <script>
    var mapContainer = document.getElementById('map1'), // 지도를 표시할 div
        mapOption = {
            center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
            level: 5 // 지도의 확대 레벨
        };

    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(37.537187, 127.005476),
        map: map
    });
    
    
    var container3 = document.getElementById('map3');
    var options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3
    };
    var map3 = new kakao.maps.Map(container3, options);
    
    


    function sample5_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("sample5_address").value = addr;
                document.getElementById("startAd").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result = results[0]; //첫번째 결과의 값을 활용

                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                    }
                });
            }
        }).open();
    }
    
    
    
    
    
    
    
    
    
    
    
</script>




</body>

</html>