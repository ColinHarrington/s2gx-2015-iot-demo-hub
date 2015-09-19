<%@ page import="groovy.json.JsonOutput" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main"/>
	<title>Hue</title>
</head>

<body>
<div class="jumbotron">
	<div class="pull-right"><asset:image src="philips-hue-glamour-v1.png"/></div>
	<h1>Hue Lights</h1>
	<p>

		Action:
		<g:link action="on" class="btn btn-info">On</g:link>
		<g:link action="off" class="btn btn-default">Off</g:link>

		Alerts:
		<g:link action="alert" class="btn btn-default">alert</g:link>
		<g:link action="longalert" class="btn btn-default">long alert</g:link>
	<g:link action="api">api</g:link>
	</p>
	<hr>

	<p>

		Colors:
		<g:link action="red" class="btn btn-danger">Red</g:link>
		<g:link action="green" class="btn btn-success">Green</g:link>
		<g:link action="blue" class="btn btn-primary">Blue</g:link>
		<g:link action="white" class="btn btn-default">White</g:link>
	</p>
	<hr/>
	<g:if test="${flash.message}">
		<div class="alert alert-info">${flash.message}</div>
	</g:if>
	<g:if test="${flash.json}">
		<pre>${flash.json.toString(4)}</pre>
	</g:if>
</div>

</body>
</html>
