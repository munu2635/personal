<?php
$xmlDoc = new DOMDocument();
$xmlDoc->load("./data.xml");
$mysql_hostname = "localhost"; // Example : localhost
$mysql_user = "root";
$mysql_password = "154679as";
$mysql_database = "powersupply";

$dbh = new PDO("mysql:dbname={$mysql_database};host={$mysql_hostname};port=3306", $mysql_user, $mysql_password);

$xmlObject = $xmlDoc->getElementsByTagName('station');
$itemCount = $xmlObject->length;
 
for ($i=0; $i < $itemCount; $i++){
  # $name = $xmlObject->item($i)->childNodes->item(1)->innerHTML;
   $name = $xmlObject->item($i)->getElementsByTagName('name')->item(0)->childNodes->item(0)->nodeValue;
   $lon = $xmlObject->item($i)->getElementsByTagName('longitude')->item(0)->childNodes->item(0)->nodeValue;
   $lat = $xmlObject->item($i)->getElementsByTagName('latitude')->item(0)->childNodes->item(0)->nodeValue;
   $slow = $xmlObject->item($i)->getElementsByTagName('slow')->item(0)->childNodes->item(0)->nodeValue;
   $fast = $xmlObject->item($i)->getElementsByTagName('fast')->item(0)->childNodes->item(0)->nodeValue;
   
   $sql = $dbh->prepare("INSERT INTO `supply` (`supplyname`, `lon`, `lat`, `slow_sup`, `fast_sup`) VALUES (?, ?, ?, ?, ?)");
   $sql->execute(array(
     $name,
     $lon,
     $lat,
     $slow,
     $fast
   ));
   print "Finished Item $name <br/>";
}
?>