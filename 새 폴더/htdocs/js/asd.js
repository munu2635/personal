
var cnt = 0;
var map_var = 13;
var a=0;
//전기차 데이터 받아오기
xhttp = new XMLHttpRequest();
xhttp.open("GET", "./assets/data.xml", false); //전기차 충전소의 데이터를 가져옴 -> 위도,경도,이름,급속,완속충전기 갯수
xhttp.send();
objDoc = xhttp.responseXML;
//console.log(objDoc)
var objRootNode;

objRootNode = objDoc.documentElement;
//   console.log(objRootNode.innerHTML)

parser = new DOMParser();

xmlDoc_xml = parser.parseFromString(objRootNode.outerHTML, "text/xml");
// console.log(xmlDoc_xml.getElementsByTagName('station')) xml의 정보를 보기위한 콘솔화면


var lat_arr = [];
var lon_arr = [];
var slow_arr = [];
var fast_arr = [];

for (var i = 0; i < 1063; i++) {//xml에서 뽑아온 데이터
        lon_arr[i] = parseFloat(xmlDoc_xml.getElementsByTagName('station')[i].childNodes[27].innerHTML);
        lat_arr[i] = parseFloat(xmlDoc_xml.getElementsByTagName('station')[i].childNodes[29].innerHTML);
        slow_arr[i] = parseInt(xmlDoc_xml.getElementsByTagName('station')[i].childNodes[9].innerHTML);
        fast_arr[i] = parseInt(xmlDoc_xml.getElementsByTagName('station')[i].childNodes[11].innerHTML);
}


setInterval(
        function () {
        
            if(a==1)
            {
                return 0;
            }
                if (cnt > 0) {
                		var divId = document.getElementsByTagName('div');
                      
                     document.getElementById(divId[map_var].id).style.display = "none";

                        map_var+=20;

                }

                console.log(document.getElementsByTagName('div'));
            
            
                var xhr_data = new XMLHttpRequest();
                xhr_data.onreadystatechange = function () {
                        if (xhr_data.readyState == 4 && xhr_data.status == 200) {
                                // mobius 컨테이너에 접속하여 저장되어있는 값들을 읽는다.

                                parser = new DOMParser();

                                xmlDoc = parser.parseFromString(xhr_data.responseText, "text/xml");

                                // 특정 테그를 기준으로 변수에 담는다
                                var xml = xmlDoc.getElementsByTagName('m2m:uril');

                                var list = [];
                                list = xml[0].innerHTML.split(" ");

                                console.log("컨테이너 값 : " + list[0])//list[0]은 가장최신의 값을 받아온다.-> 차의 정보

                                var xhr_data_list = new XMLHttpRequest();
                                xhr_data_list.onreadystatechange = function () {
                                        if (xhr_data_list.readyState == 4 && xhr_data_list.status == 200) {

                                                // 1. 지도 띄우기
                                                map = new Tmap.Map({
                                                        div: 'map_div',
                                                        width: "70%",
                                                        height: "720px",
                                                });

                                                parser = new DOMParser();

                                                xmlDoc = parser.parseFromString(xhr_data_list.responseText, "text/xml");

                                                // 특정 테그를 기준으로 변수에 담는다
                                                var xml = xmlDoc.getElementsByTagName('con');

                                                list = JSON.parse(xml[0].textContent);//텍스트문서를 파싱하여 json파일로 변경
                                                //console.log(list.value);//cin값-> 스트링으로 나옴-> 차량 데이터 값 & 주행거리 & 위도

                                                var result = [];//문자열을 스페이스바 구분으로 나눈다.
                                                result = list.value.split(" ");

                                                //console.log(result);
                                                //모비우스에서 받아온 데이터를 변수화 한다.
                                                var car_num = parseInt(result[0].substring(8, 12));
                                                console.log("car_num : " + car_num)
                                                var lat = parseFloat(result[1].substring(4, 22));
                                                console.log("lat : " + lat)
                                                var lon = parseFloat(result[2].substring(4, 22));
                                                console.log("lon : " + lon)
                                                var km = parseFloat(result[3].substring(3, 19));
                                                console.log("km : " + km)

                                                var name = [];
                                                var temp_num = [];
                                                var min, min_index;

                                                for (var i = 0; i < 1063; i++) {
                                                        name[i] = xmlDoc_xml.getElementsByTagName('station')[i].childNodes[1].innerHTML;
                                                        temp_num[i] = Math.sqrt((110 * Math.abs(lat - lat_arr[i])) * (110 * Math.abs(lat - lat_arr[i])) + (88 * Math.abs(lon - lon_arr[i])) * (88 * Math.abs(lon - lon_arr[i])));
                                                        //가장 가까운지 아닌지//직선거리를 구하는 코드
                                                }

                                                //  console.log(temp_num)


                                                //가장가까운 충전소가 어디에 있는지 구하는 코드
                                                // min = temp_num[0];
                                                // for (var i = 1; i < 1063; i++) {
                                                //     if (min > temp_num[i]) {
                                                //         min = temp_num[i];
                                                //         min_index = i;
                                                //     }
                                                // }

                                                // if (slow_arr[min_index] + fast_arr[min_index] == 0) {
                                                //     console.log("log 실행")
                                                //     alert("여분 충전기가 존재하지 않아 새로운 경로를 탐색합니다.");
                                                //     temp_num[min_index] = 9999999;//절대적으로 범위를 넘겨 검색에서 제외
                                                //     min = temp_num[0];
                                                //     for (var i = 1; i < 1063; i++) {
                                                //         if (min > temp_num[i]) {
                                                //             min = temp_num[i];
                                                //             min_index = i;
                                                //         }
                                                //     }
                                                // }

                                                //trigger 생성
                                                //강제적으로 xml데이터 주입(중간에 충전기 갯수를 바꿔버려 변화를줌)
                                                //시연에 필요한 상황을 넣는것
                                                // if(cnt==2){
                                                //     slow_arr[644]=0;
                                                //     fast_arr[644]=0;
                                                // }
                                                // if(cnt==5)
                                                // {
                                                //     slow_arr[643]=0;
                                                //     fast_arr[643]=0;
                                                // }
                                                //위의 코드를 합친 알고리즘

                                                min = temp_num[0];
                                                for (var i = 1; i < 1063; i++) {
                                                        if ((slow_arr[i] + fast_arr[i]) != 0) {
                                                                if (min > temp_num[i]) {
                                                                        min = temp_num[i];
                                                                        min_index = i;
                                                                }
                                                        }
                                                }

                                                //가장가까운 충전소 출력
                                                console.log("min_index= " + min_index)
                                                var lat_arrive = lat_arr[min_index];
                                                var lon_arrive = lon_arr[min_index];
                                                console.log("충전소 위치 : " + name[min_index])
                                                console.log("lat_arr : " + lat_arr[min_index])
                                                console.log("lon_arr : " + lon_arr[min_index])

                                                // =========================================================================================
                                                //html에 값 입히기
                                                document.getElementById('car_number').innerHTML = "차량번호 : " + car_num;
                                                document.getElementById('lat_info_car').innerHTML = "위도 : " + lat;
                                                document.getElementById('lon_info_car').innerHTML = " 경도 : "+ lon;
                                                document.getElementById('km_info_car').innerHTML = "주행가능거리 : " + km + "km";

                                                document.getElementById('station_name').innerHTML = "충전소 이름  :" +name[min_index];
                                                document.getElementById('lat_info_station').innerHTML = "위도 : " + lat_arr[min_index];
                                                document.getElementById('lon_info_station').innerHTML = "경도 : " + lon_arr[min_index];
                                                document.getElementById('rapid').innerHTML = "급속 충전기 : " + fast_arr[min_index];
                                                document.getElementById('slow').innerHTML = "완속 충전기 : " + slow_arr[min_index];
                                                document.getElementById('distance').innerHTML = "거리 : " + temp_num[min_index]+"km";

                                                // =========================================================================================

                                                map.setCenter(new Tmap.LonLat(lat, lon).transform("EPSG:4326", "EPSG:3857"), 15);
                                                var routeLayer = new Tmap.Layer.Vector("route");
                                                map.addLayer(routeLayer);

                                                // 2. 시작, 도착 심볼찍기
                                                // 시작

                                                var markerStartLayer = new Tmap.Layer.Markers("start");
                                                map.addLayer(markerStartLayer);
                                                if (size) {
                                                        size = null;
                                                }
                                                var size = new Tmap.Size(24, 38);
                                                var offset = new Tmap.Pixel(-(size.w / 2), -size.h);
                                                var icon = new Tmap.IconHtml("<img src='http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_s.png' />", size, offset);
                                                var marker_s = new Tmap.Marker(new Tmap.LonLat(lat, lon).transform("EPSG:4326", "EPSG:3857"), icon);
                                                markerStartLayer.addMarker(marker_s);

                                                // 도착
                                                var markerEndLayer = new Tmap.Layer.Markers("end");
                                                map.addLayer(markerEndLayer);


                                                var size = new Tmap.Size(24, 38);
                                                var offset = new Tmap.Pixel(-(size.w / 2), -size.h);
                                                var icon = new Tmap.IconHtml("<img src='http://tmapapis.sktelecom.com/upload/tmap/marker/pin_r_m_e.png' />", size, offset);
                                                var marker_e = new Tmap.Marker(new Tmap.LonLat(lat_arrive, lon_arrive).transform("EPSG:4326", "EPSG:3857"), icon);
                                                markerEndLayer.addMarker(marker_e);

                                                // 4. 경로 탐색 API 사용요청
                                                var startX = lat;
                                                var startY = lon;
                                                var endX = lat_arrive;
                                                var endY = lon_arrive;
                                                var prtcl;
                                                var headers = {};
                                                headers["appKey"] = "697870fc-4410-4ace-a2de-525221b9667f";
                                                $.ajax({
                                                        method: "POST",
                                                        headers: headers,
                                                        url: "https://api2.sktelecom.com/tmap/routes?version=1&format=xml",//
                                                        async: false,
                                                        data: {
                                                                startX: startX,
                                                                startY: startY,
                                                                endX: endX,
                                                                endY: endY,

                                                                reqCoordType: "WGS84GEO",
                                                                resCoordType: "EPSG3857",
                                                                angle: "172",
                                                        },
                                                        success: function (response) {
                                                                prtcl = response;

                                                                // 5. 경로 탐색  결과 Line 그리기
                                                                //경로 탐색  결과 POINT 찍기
                                                                /* -------------- Geometry.Point -------------- */
                                                                var pointLayer = new Tmap.Layer.Vector("point");
                                                                var prtclString = new XMLSerializer().serializeToString(prtcl);//xml to String	
                                                                xmlDoc = $.parseXML(prtclString),
                                                                        $xml = $(xmlDoc),
                                                                        $intRate = $xml.find("Placemark");
                                                                var style_red = {
                                                                        fillColor: "#FF0000",
                                                                        fillOpacity: 0.2,
                                                                        strokeColor: "#FF0000",
                                                                        strokeWidth: 3,
                                                                        strokeDashstyle: "solid",
                                                                        pointRadius: 2,
                                                                        title: "this is a red line"
                                                                };
                                                                $intRate.each(function (index, element) {
                                                                        var nodeType = element.getElementsByTagName("tmap:nodeType")[0].childNodes[0].nodeValue;
                                                                        if (nodeType == "POINT") {
                                                                                var point = element.getElementsByTagName("coordinates")[0].childNodes[0].nodeValue.split(',');
                                                                                var geoPoint = new Tmap.Geometry.Point(point[0], point[1]);
                                                                                var pointFeature = new Tmap.Feature.Vector(geoPoint, null, style_red);
                                                                                pointLayer.addFeatures([pointFeature]);
                                                                        }
                                                                });
                                                                map.addLayer(pointLayer);
                                                                /* -------------- Geometry.Point -------------- */
                                                                //경로 탐색  결과 Line 그리기
                                                                routeLayer.style = {
                                                                        fillColor: "#FF0000",
                                                                        fillOpacity: 0.2,
                                                                        strokeColor: "#FF0000",
                                                                        strokeWidth: 3,
                                                                        strokeDashstyle: "solid",
                                                                        pointRadius: 2,
                                                                        title: "this is a red line"
                                                                }
                                                                var kmlForm = new Tmap.Format.KML().read(prtcl);
                                                                routeLayer.addFeatures(kmlForm);

                                                                // 6. 경로탐색 결과 반경만큼 지도 레벨 조정
                                                                map.zoomToExtent(routeLayer.getDataExtent());

                                                        },
                                                        error: function (request, status, error) {
                                                                console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
                                                        }

                                                });

                                                // if (km < 20) {
                                                //     alert("주행가능한 거리가 20km 미만입니다.")
                                                // }
                                        }
                                }
                                xhr_data_list.open("GET", "http://192.168.0.7:7579/" + list[0]);//가장 최신의 차량데이터를 넣는다.

                                xhr_data_list.setRequestHeader('Accept', 'application/onem2m-resource+xml');
                                xhr_data_list.setRequestHeader('From', 'mobius');
                                xhr_data_list.setRequestHeader('X-M2M-RI', '12345');
                                xhr_data_list.setRequestHeader('X-M2M-Origin', 'origin');

                                xhr_data_list.send();
                        }
                }
                xhr_data.open("GET", "http://192.168.0.7:7579/Mobius/mobius/car8?fu=1");

                xhr_data.setRequestHeader('Accept', 'application/onem2m-resource+xml');
                xhr_data.setRequestHeader('From', 'mobius');
                xhr_data.setRequestHeader('X-M2M-RI', '12345');
                xhr_data.setRequestHeader('X-M2M-Origin', 'origin');

                xhr_data.send();

                cnt++;

        }, 6000);
