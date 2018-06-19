// mysql 접근

var mysql = require('mysql');
var connection = mysql.createConnection({
  host:'127.0.0.1',port:3306, user:'root', password:'123456',database : "charger"
})

connection.connect();

//  충전소 감소 함수
var decrease = function (id) {
  var quick_charger_num = connection.executeQuery("select quick from charger where id='" + id + "'"); // 개수 갖고오기
  var normal_charger_num = connection.executeQuery("select normal from charger where id='" + id + "'");

  if(quick_charger_num > 0){ //급속충전기가 있을떄
    connection.query("UPDATE charger SET quick = '"+ quick_charger_num - 1 + " 변경값 WHERE id ='" + id + "''");
    return 1; // 어떤거 사용하는지 확인할 값
  } else if(normal_charger_num > 0) { //없을때 + 완속 충전기가 있을때
    connection.query("UPDATE charger SET quick = '"+ normal_charger_num - 1 + " 변경값 WHERE id ='" + id + "''");
    return 2;
  }
  return -1;
};

// 충전소 증가 함수
var increase = function(id, charger) {
  var quick_charger_num = connection.executeQuery("select quick from charger where id='" + id + "'"); // 개수 갖고오기
  var normal_charger_num = connection.executeQuery("select normal from charger where id='" + id + "'");

  if(charger == 1){ // 급속 충전기를 반납할때
    connection.query("UPDATE charger SET quick = '"+ quick_charger_num + 1 + " 변경값 WHERE id ='" + id + "''")
  } else if(charger == 2) { // 완속충전기를 반납할때
    connection.query("UPDATE charger SET quick = '"+ normal_charger_num + 1 + " 변경값 WHERE id ='" + id + "''")
  }
};

//connection.end();
