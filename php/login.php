<?php
//getting user inputs
$username = $_POST['username'];
$password = $_POST['password'];


//array of response
$output = array();

//require database
require_once('config.php');

//checking if user exists
$conn=$dbh->prepare('SELECT * FROM `users` WHERE `username` = ? AND `password` = ?');
$conn->bindParam(1 , $username);
$conn->bindParam(2 , $password);
$conn->execute();

//results
if($conn->rowCount()!==0){
	//username exist
	$output['isSuccess'] = 1;
	$output['message'] = "Login Success";
}else{
	$output['isSuccess'] = 0;
	$output['message'] = "Wrong Data";
}
echo json_encode($output);
?>