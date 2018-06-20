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

var decrease = function (id) {
	var quick_charger_num 
	var normal_charger_num 
	
	connection.query('SELECT quick from charger WHERE id=?', [id] , function (error, results, fields) {
		if (error) console.log(error);
		else quick_charger_num = results;
	}); 
	connection.query('SELECT normal from charger WHERE id=?', [id], function (error, results, fields) {
  		if (error) console.log(error);
  		else normal_charger_num = results;
	}); 
  	
	if(quick_charger_num > 0){
    		connection.query('UPDATE charger SET quick=? WHERE id =?' [ quick_charger_num-1 , id], function (error, results, fields) {
  			if (error) console.log(err);
	 		else console.log(rows);
		}); 
    		return 1; 
  	} else if(normal_charger_num > 0) { 
    		connection.query('UPDATE charger SET normal=? WHERE id=?', [normal_charger_num-1 , id], function (error, results, fields) {
  			if (error) console.log(err);
	 		else console.log(rows);
		}); 
   	 	return 2;
  	}
  	return -1;
};


var increase = function(id, charger) {
	var quick_charger_num 
	var normal_charger_num 
	
	connection.query('SELECT quick from charger WHERE id=?', [id] , function (error, results, fields) {
		if (error) console.log(error);
		else quick_charger_num = results;
	}); 
	connection.query('SELECT normal from charger WHERE id=?', [id], function (error, results, fields) {
  		if (error) console.log(error);
  		else normal_charger_num = results;
	}); 
  	

  	if(charger == 1){
   		 connection.query('UPDATE charger SET quick=? WHERE id =?' [ quick_charger_num+1 , id], function (error, results, fields) {
  			if (error) console.log(err);
	 		else console.log(rows);
		}); 
  	} else if(charger == 2) { 
    		query('UPDATE charger SET normal=? WHERE id=?', [normal_charger_num+1 , id], function (error, results, fields) {
  			if (error) console.log(err);
	 		else console.log(rows);
		}); 
  	}
};

connection.end();
