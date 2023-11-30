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
  
   	            index++;
   	        } else {
   	            // 폴리라인의 끝에 도달했다면, 인터벌을 중단합니다.
   	            clearInterval(intervalId);
   	        }
   	    };

   	    // 1초마다 마커를 이동시키기 위한 인터벌 설정
   	    const intervalId = setInterval(moveMarker, 500);
   	};


   
