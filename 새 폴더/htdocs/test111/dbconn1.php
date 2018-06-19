<?php
    $connect=mysql_connect( "localhost", "root", "154679as") or
        die( "SQL server에 연결할 수 없습니다.");

    mysql_select_db("web_db",$connect);
?>
