<?
	session_start();
	include "../dbconn1.php";
  $num = $_GET['num'];
	$sql = "select * from boarddb where num=$num";
	$result = mysql_query($sql, $connect);

	$row = mysql_fetch_array($result);
	$item_subject     = $row[subject];
	$item_content     = $row[content];
?>
<html>
<head>
<meta charset="utf-8">
</head>

<body>
<div id="wrap">
  <div id="header">
    <? include "../top_login.php"; ?>
  </div>  <!-- end of header -->

  <div id="content">

	<div id="col2">
		<div id="title">
			게시판
		</div>

		<div class="clear"></div>

		<div id="write_form_title">
			글쓰기
		</div>

		<div class="clear"></div>
		<form  name="board_form" method="post" action="insertb.php?mode=modify&num=<?=$num?>&page=<?=$page?>">
		<div id="write_form">
			<div class="write_line"></div>
			<div id="write_row1">
				<div class="col1"> 작성자 </div>
				<div class="col2"><?=$username?></div>
			</div>
			<div class="write_line"></div>
			<div id="write_row2"><div class="col1"> 제목   </div>
			                     <div class="col2"><input type="text" name="subject" value="<?=$item_subject?>" ></div>
			</div>
			<div class="write_line"></div>
			<div id="write_row3"><div class="col1"> 내용   </div>
			                     <div class="col2"><textarea rows="15" cols="79" name="content"><?=$item_content?></textarea></div>
			</div>
			<div class="write_line"></div>
		</div>

		<div id="write_button"><input type="image" src="../../img/ok.png">&nbsp;
								<a href="list.php?page=<?=$page?>"><img src="../../img/list.png"></a>

		</form>

	</div> <!-- end of col2 -->
  </div> <!-- end of content -->
</div> <!-- end of wrap -->

</body>
</html>
