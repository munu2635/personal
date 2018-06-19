// mysql 접근

var mysql = require('mysql');
var connection = mysql.createConnection({
  host:'127.0.0.1', port:3306, user:'root', password:'123456', database : "charger"
})

connection.connect(function(err){
    if (err){
        console.log('mysql connection is fail ');
        console.log(err);
        throw err;
    } else {
        console.log('mysql connection is success');
    }
});

//  충전소 감소 함수
var decrease = function (id) {
	var quick_charger_num 
	var normal_charger_num
	var sql = 'select quick from charger where id=?';
	var  p
	
	connection.query("select quick from charger where id='" + id + "'", function (error, results, fields) {
	if (error) throw error;
  	quick_charger_num = results;
	}); 
	connection.query("select normal from charger where id='" + id + "'", function (error, results, fields) {
  	if (error) throw error;
  	normal_charger_num = results;
	}); 
  	
	if(quick_charger_num > 0){
    		connection.query("UPDATE charger SET quick = '"+ quick_charger_num - 1 + " 변경값 WHERE id ='" + id + "''");
    		return 1; 
  	} else if(normal_charger_num > 0) { 
    		connection.query("UPDATE charger SET quick = '"+ normal_charger_num - 1 + " 변경값 WHERE id ='" + id + "''");
   	 	return 2;
  	}
  	return -1;
};

/*
var increase = function(id, charger) {
  var quick_charger_num = connection.executeQuery("select quick from charger where id='" + id + "'"); 
  var normal_charger_num = connection.executeQuery("select normal from charger where id='" + id + "'");

  if(charger == 1){
    connection.query("UPDATE charger SET quick = '"+ quick_charger_num + 1 + " 변경값 WHERE id ='" + id + "''")
  } else if(charger == 2) { 
    connection.query("UPDATE charger SET quick = '"+ normal_charger_num + 1 + " 변경값 WHERE id ='" + id + "''")
  }
};
*/
var a = decrease(1);
//console.log(a);

connection.end();
