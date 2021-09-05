<?php
//getting user inputs
$username = $_POST['username'];
$password = $_POST['password'];
$email = $_POST['email'];

//array of response
$output = array();

//require database
require_once('config.php');

//checking if user exists
$conn=$dbh->prepare('SELECT * FROM `users` WHERE `username` = ?');
$conn->bindParam(1 , $username);
$conn->execute();

//results
if($conn->rowCount()!==0){
	//username exist
	$output['isSuccess'] = 0;
	$output['message'] = "Username Exist";
}else{
	//insert into db
	$conn=$dbh->prepare('INSERT INTO `users`(`username`, `email`, `password`) VALUES (?,?,?)');
	$conn->bindParam(1 , $username);
	$conn->bindParam(2 , $email);
	$conn->bindParam(3 , $password);
	$conn->execute();
	if($conn->rowCount()==0){
		$output['isSuccess'] = 0;
		$output['message'] = "Registration Failure";
	}else if($conn->rowCount()!==0){
		$output['isSuccess'] = 1;
		$output['message'] = "Registration Success";
		$output['username'] = $username;
	}
}
echo json_encode($output);
?>