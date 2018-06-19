<?
	session_start();
	$userid = $_SESSION['userid'];
	$username = $_SESSION['username'];
?>
<html>
<head>
<meta charset="utf-8">
</head>

<body>
<div id="wrap">
  <div id="header">
    <? include "top_login.php"; ?>
  </div>  <!-- end of header -->

  <div id="content">

	<div id="col2">
		<div id="title">
			<p>가입인사</p>
		</div>
		<div class="clear"></div>

		<div id="write_form_title">
			<p>글쓰기</p>
		</div>
		<div class="clear"></div>

		<form  name="board_form" method="post" action="insertb.php">
		<div id="write_form">
			<div class="write_line"></div>
			<div id="write_row1">
				<div class="col1"> 작성자 <?=$username?></div>
				<div class="col3"><input type="checkbox" name="html_ok" value="y"> HTML 쓰기</div>
			</div>
			<div class="write_line"></div>
			<div id="write_row2"><div class="col1"> 제목   </div>
			                     <div class="col2"><input type="text" name="subject"></div>
			</div>
			<div class="write_line"></div>
			<div id="write_row3"><div class="col1"> 내용   </div>
			                     <div class="col2"><textarea rows="15" cols="79" name="content"></textarea></div>
			</div>
			<div class="write_line"></div>
		</div>

		<div id="write_button"><input type="image" src="../../img/ok.png">&nbsp;
								<a href="list.php?page=<?=$page?>"><img src="../../img/list.png"></a>
		</div>
		</form>

	</div> <!-- end of col2 -->
  </div> <!-- end of content -->
</div> <!-- end of wrap -->

</body>
</html>
