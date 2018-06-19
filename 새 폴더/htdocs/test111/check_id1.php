<meta charset="utf-8">
<?php
  $id = $_GET["id"];
  if(!$id)
   {
      echo("$id<br>아이디를 입력하세요.");
   }
   else
   {
      include "dbconn1.php";

      $sql = "select * from memberi where id='$id' ";

      $result = mysql_query($sql, $connect);
      $num_record = mysql_num_rows($result);

      if ($num_record)
      {
         echo "아이디가 중복됩니다!<br>";
         echo "다른 아이디를 사용하세요.<br>";
      }
      else
      {
         echo "사용가능한 아이디입니다.";
      }

      mysql_close();
   }
?>
