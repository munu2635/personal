<?php session_start();
$userid = $_SESSION['userid'];
$username = $_SESSION['username'];

?>

<html>
<head>
<meta charset="utf-8">
</head>

<body>
  <?php
  if(!$userid)
{
    ?>
  <div>
        <form  name="member_form" method="post" action="login1.php">
		<div>
			로그인
		</div>
		<div>
		     아이디와 비밀번호를 입력해 주세요.
		<div class="clear"></div>
			 <div>
				<div>
					<div>
						<ul>
						<li>아이디&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="id"></li>
						<li>비밀번호&nbsp;&nbsp;<input type="password" name="pass"></li>
						</ul>
					</div>
					<div>
						<input type="image" src="../img/login_button.gif">
					</div>
				</div>
				<div class="clear"></div>

				<div> <a href="member_form2.php">회원가입하기</a></div>
			 </div>
		</div> <!-- end of form_login -->

	    </form>
	</div> <!-- end of col2 -->
  <?php
    }
    else {
  ?>
    <?php=$username?> |
    <a href="logout2.php">로그아웃</a> | <a href="member_form_modify1.php">정보수정</a>
    <div>  <a href="board/list.php">게시판</a> </div>
    <?php
  }
?>
</body>
</html>
