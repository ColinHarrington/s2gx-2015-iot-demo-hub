<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main"/>
	<g:set var="entityName" value="${message(code: 'keypadCode.label', default: 'KeypadCode')}"/>
	<title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="row">
	<div class="nav" role="navigation">
		<g:link class="btn btn-default" role="button" action="create">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			New KeyCode
		</g:link>
	</div>

</div>

<div class="row">
	<div class="col-md-8">
		<div id="list-keypadCode" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]"/></h1>
			<g:if test="${flash.message}">
				<div class="message alert alert-info" role="status">${flash.message}</div>
			</g:if>
			<table class="table table-striped">
				<thead>
				<tr>
					<th>Code</th>
					<th>Deleted</th>
					<th>Enabled</th>
				</tr>

				</thead>
				<tbody>
				<g:each var="keypadCode" in="${keypadCodeList}">
					<tr>
						<td><g:link action="show" id="${keypadCode.id}">${keypadCode.code}</g:link></td>
						<td>${keypadCode.deleted}</td>
						<td>${keypadCode.enabled}</td>
					</tr>
				</g:each>

				</tbody>
			</table>

			<div class="pagination">
				<g:paginate total="${keypadCodeCount ?: 0}"/>
			</div>
		</div>
	</div>
	<div class="col-md-4">
		<asset:image src="keypad.png" class="img-circle"/>
	</div>
</div>
</body>
</html>