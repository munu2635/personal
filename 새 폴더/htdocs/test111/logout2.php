<?php
  session_start();
  session_destroy();

  echo("
       <script>
          location.href = 'test1.php';
         </script>
       ");
?>
