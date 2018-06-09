<?php

 
 	 $db_name="localnews";
	 $mysql_username="root";
	 $mysql_password="";
	 $server_name="localhost";
	 $conn=mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
?>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
</head>
<body>
<?php
	$sql=" SELECT * FROM news ;";
	$result=mysqli_query($conn,$sql);

	while($row=mysqli_fetch_row($result)){
		echo("<div style='width:30%;background-color:#29d;>");
		echo "<p>$row[1]</p><img src=$row[3]><p>$row[2]</p>";
		echo("</div>");
	}
?>
	
</body>
</html>