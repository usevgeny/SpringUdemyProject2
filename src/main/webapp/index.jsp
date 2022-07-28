<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.w3.org/199/xhtml">
<head>
<meta charset="UTF-8">
<title>List of subscribers</title>

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Poppins', sans-serif;
}

body {
	overflow: hidden;
}

section {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	background: linear-gradient(to bottom, #FFF, #e6ebff);
}

.container {
	position: relative;
	width: 800px;
	text-align: left;
	margin: 0 auto;
	padding: 50px;
	background: rgba(255, 255, 255, 0.1);
	box-shadow: 0 25px 45px rgba(0, 0, 0, 0.1);
	border: 1px solid rgba(255, 255, 255, 0.25);
	border-right: 1px solid rgba(255, 255, 255, 0.1);
	border-bottom: 1px solid rgba(255, 255, 255, 0.1);
	backdrop-filter: blur(5px);
	border-radius: 10px;
	z-index: 1;
	overflow: hidden;
	color: black;
}

.container h1 {
	font-size: 50px;
}

.container a {
	color: blue;
	text-decoration: none;
}

.container a:hover {
	color: #A3D9FF;
	text-decoration: none;
}

.container h2 {
	width: 100%;
	color: #fff;
	text-align: center;
	font-size: 36px;
	margin-bottom: 20px;
}

table.customTab {
	width: 90%;
	margin: 0 auto;
}

table.customTab, table.customTab tr, table.customTab th, table.customTab td
	{
	border-collapse: collapse;
	border: 1px solid black;
}

table.customTab tr:nth-child(odd) {
	background-color: gray;
	color: white;
}
</style>

</head>
<body>
	<section>

		<div class="container">

			<h1>Welcome to the virtual library PROJECT 2</h1>
			<h3>
				<a href="#">Home</a> &nbsp;<a href="/books">Books list</a> &nbsp; <a
					href="/subscribers">Subscribers</a>
			</h3>


			
		</div>
	</section>
</body>
</html>