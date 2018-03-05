<?php
header('Access-Control-Allow-Origin: *');
$r = 6371;
$lat1 = $_GET['lat1'];
$lng1 = $_GET['lng1'];
$lat2 = $_GET['lat2'];
$lng2 = $_GET['lng2'];
$d = 0;
$error = '';
if(isset($lat1, $lng1, $lat2, $lng2)){
	if($lat1 > 90 or $lat1 < -90 or $lat2 > 90 or $lat2 < -90 or $lng1 > 180 or $lng1 < -180 or $lng2 > 180 or $lng2 < -180) {
		$error = "Invalid coordinates";
	} else {
		$lat1 = deg2rad($lat1);
		$lng1 = deg2rad($lng1);
		$lat2 = deg2rad($lat2);
		$lng2 = deg2rad($lng2);
		$cosA = sin($lat1)*sin($lat2) + cos($lat1)*cos($lat2)*cos(abs($lng1 - $lng2));
		$d = $r*acos($cosA);
	}
}
$arr = array('error' => $error, 'distance' => $d);
echo json_encode($arr);
?>
