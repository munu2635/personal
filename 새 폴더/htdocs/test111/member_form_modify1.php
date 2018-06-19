<?php
    session_start();
?>

<head>
<meta charset="utf-8">
<script>
   function check_id()
   {
     window.open("check_id1.php?id=" + document.member_form.id.value,
         "IDcheck",
          "left=200,top=200,width=250,height=100,scrollbars=no,resizable=yes");
   }

   function check_input()
   {
      if (!document.member_form.pass.value)
      {
          alert("비밀번호를 입력하세요");
          document.member_form.pass.focus();
          return;
      }

      if (!document.member_form.pass_confirm.value)
      {
          alert("비밀번호확인을 입력하세요");
          document.member_form.pass_confirm.focus();
          return;
      }

      if (!document.member_form.name.value)
      {
          alert("이름을 입력하세요");
          document.member_form.name.focus();
          return;
      }

      if (document.member_form.pass.value !=
            document.member_form.pass_confirm.value)
      {
          alert("비밀번호가 일치하지 않습니다.\n다시 입력해주세요.");
          document.member_form.pass.focus();
          document.member_form.pass.select();
          return;
      }

      document.member_form.submit();
   }

   function reset_form()
   {
      document.member_form.id.value = "";
      document.member_form.pass.value = "";
      document.member_form.pass_confirm.value = "";
      document.member_form.name.value = "";
      document.member_form.email1.value = "";
      document.member_form.email2.value = "";

      document.member_form.id.focus();

      return;
   }
</script>
</head>
<?php
    include "dbconn1.php";

    $sql = "select * from memberi where id='$userid'";
    $result = mysql_query($sql, $connect);

    $row = mysql_fetch_array($result);

    $email = explode("@", $row[email]);
    $email1 = $email[0];
    $email2 = $email[1];

    mysql_close();
?>
<body>
<div>
  <div>
  <?php  include "top_login.php"; ?>
  </div>  <!-- end of header -->

	<div id="col2">
        <form  name="member_form" method="post" action="modify1.php">
		<div id="title">
			회원정보수정
		</div>


		<div id="form_join">
			<div id="join1">
			<ul>
			<li>* 아이디<?php= $row[id] ?></li>
			<li>* 비밀번호<input type="password" name="pass" value="<?php= $row[pass] ?>"></li>
			<li>* 비밀번호 확인<input type="password" name="pass_confirm" value="<?php= $row[pass] ?>"></li>
			<li>* 이름<input type="text" name="name" value="<?php= $row[name] ?>"></li>
			<li>&nbsp;&nbsp;&nbsp;이메일<input type="text" id="email1" name="email1" value="<?php= $email1 ?>"> @ <input type="text" name="email2"
             value="<?php= $email2 ?>"></li>
			</ul>
			</div>

			<div class="clear"></div>
			<div id="must"> * 는 필수 입력항목입니다.^^</div>
		</div>

		<div id="button"><a href="#"><img src="../img/button_save.gif"  onclick="check_input()"></a>&nbsp;&nbsp;
		                 <a href="#"><img src="../img/button_reset.gif" onclick="reset_form()"></a>
		</div>
	    </form>
	</div>
  </div>
</div>
</body>
</html>
