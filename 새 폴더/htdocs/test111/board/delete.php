<?
   session_start();

   include "../dbconn1.php";
   $num = $_GET['num'];
   $sql = "delete from boarddb where num = $num";
   mysql_query($sql, $connect);

   mysql_close();

   echo "
	   <script>
	    location.href = 'list.php';
	   </script>
	";
?>
