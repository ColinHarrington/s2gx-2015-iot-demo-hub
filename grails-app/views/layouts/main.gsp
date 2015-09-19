<!DOCTYPE html>
<!-- saved from url=(0050)http://getbootstrap.com/examples/starter-template/ -->
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<meta name="author" content=" Colin Harrington">

	<title><g:layoutTitle default="Booting IoT with Grails"/></title>


	<!-- Bootstrap Core CSS -->
	<asset:stylesheet src="bootstrap.min.css"/>

	<!-- Custom CSS -->
	<asset:stylesheet src="template.css"/>
	<asset:javascript src="jquery.js"/>
	<asset:javascript src="bootstrap.min.js"/>

	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<g:layoutHead/>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
					aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<g:link class="navbar-brand" uri="/">IoT Hub</g:link>
		</div>

		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="${controllerName ? '' : 'active'}"><g:link uri="/">Home</g:link></li>
				<li class="${controllerName == "hue" ? 'active' : ''}"><g:link controller="hue">Hue</g:link></li>
				<li class="${controllerName == "keypadCode" ? 'active' : ''}"><g:link controller="keypadCode">Keypad</g:link></li>
				<li class="${controllerName == "serialPort" ? 'active' : ''}"><g:link controller="serialPort">Serial</g:link></li>
				<li class="${controllerName == "fadecandy" ? 'active' : ''}"><g:link controller="fadecandy">Fadecandy</g:link></li>
				<li class="${controllerName == "action" ? 'active' : ''}"><g:link controller="action">Actions</g:link></li>
				<li class="${controllerName == "actuator" ? 'active' : ''}"><g:link controller="actuator" action="dashboard">Actuator</g:link></li>
			</ul>
		</div><!--/.nav-collapse -->
	</div>
</nav>

<div class="container">
	<g:layoutBody/>
</div><!-- /.container -->


<asset:deferredScripts/>

</body>
</html>