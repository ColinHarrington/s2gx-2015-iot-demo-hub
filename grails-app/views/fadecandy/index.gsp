<%@ page import="groovy.json.JsonOutput" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main"/>
	<title>Fadecandy</title>
</head>

<body>
<div class="jumbotron">
	<div class="pull-right"><asset:image src="fadecandy.png" width="256" height="256"/></div>

	<h1>Fadecandy</h1>
	<g:link url="https://github.com/scanlime/fadecandy">https://github.com/scanlime/fadecandy</g:link>
	<hr/>

	<h3>Processing</h3>
	<p>
		Modes:
		<g:link action="mode" params="${[mode: 'spinner']}" class="btn btn-primary">spinner</g:link>
		<g:link action="mode" params="${[mode: 'clouds']}" class="btn btn-info">clouds</g:link>
		<g:link action="mode" params="${[mode: 'success']}" class="btn btn-success">success</g:link>
		<g:link action="mode" params="${[mode: 'fail']}" class="btn btn-danger">fail</g:link>
		<g:link action="mode" params="${[mode: 'clear']}" class="btn btn-default">clear</g:link>
	</p>
	<hr/>

	<p>
		Control:
		<g:link action="mode" params="${[mode: 'pause']}" class="btn btn-warning">pause</g:link>
		<g:link action="mode" params="${[mode: 'resume']}" class="btn btn-primary">resume</g:link>
	</p>
	<hr/>

	<div class="row">

		<div class="col-xs-6 col-md-4">
			<g:link action="minecraft" class="btn btn-success center-block">
				<asset:image src="minecraft-large.png" width="128" height="128" class="center-block thumbnail"/>
				Minecraft
			</g:link>
		</div>

		<div class="col-xs-6 col-md-4">
			<g:link action="gameCharacters" class="btn btn-default center-block">
				<asset:image src="game-characters-large.png" width="128" height="128" class="center-block thumbnail"/>
				<div class="center-block">8x8 sprites</div>
			</g:link>
		</div>

		<div class="col-xs-6 col-md-4">
			<g:link action="direct" class="btn btn-info center-block">
				<asset:image src="direct-large.png" width="128" height="128" class="thumbnail center-block"/>
				Animation
			</g:link>
		</div>
	</div>
</p>

	<hr/>
	<g:if test="${flash.successMessage}">
		<div class="alert alert-success">${flash.successMessage}</div>
	</g:if>
	<g:if test="${flash.message}">
		<div class="alert alert-info">${flash.message}</div>
	</g:if>
	<g:if test="${flash.json}">
		<pre>${flash.json.toString(4)}</pre>
	</g:if>
</div>

<div class="row">
	<div class="col-md-12">
		<h2>NeoPixel NeoMatrix 8x8</h2>

		<asset:image src="neopixel-matrix.png" class="center-block img-responsive"/>
		<div class="pull-right">https://www.adafruit.com/products/1487</div>

	</div>

</div>

</body>
</html>
