<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main"/>
	<g:set var="entityName" value="${message(code: 'keypadCode.label', default: 'KeypadCode')}"/>
	<title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav" role="navigation">
	<ul>
		<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
		<li><g:link class="list" action="index">New Keypad Code</g:link></li>
	</ul>
</div>

<div id="create-keypadCode" class="content scaffold-create" role="main">
	<h1><g:message code="default.create.label" args="[entityName]"/></h1>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${this.keypadCode}">
		<ul class="errors" role="alert">
			<g:eachError bean="${this.keypadCode}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
						error="${error}"/></li>
			</g:eachError>
		</ul>
	</g:hasErrors>
	<g:form action="save" class="form-horizontal">
		%{--<fieldset class="form">--}%
			%{--<f:all bean="keypadCode"/>--}%
		%{--</fieldset>--}%

		<form class="form-horizontal">
			<div class="form-group">
				<label for="code" class="col-sm-2 control-label">Code</label>

				<div class="col-sm-10">
					<input type="text" class="form-control" id="code" name="code" placeholder="code">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
		<g:submitButton name="create" class="btn btn-default"
						value="${message(code: 'default.button.create.label', default: 'Create')}"/>
				</div>
			</div>
	</g:form>
</div>


</body>
</html>
