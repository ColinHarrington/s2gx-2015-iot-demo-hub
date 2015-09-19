<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'keypadCode.label', default: 'KeypadCode')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index">List Codes</g:link></li>
                <li><g:link class="btn btn-default" role="button" action="create">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                    New KeyCode
                </g:link></li>
            </ul>
        </div>
    <div class="jumbotron">
        <div class="pull-right"><asset:image src="keypad.png" class="img-circle"/></div>
        <h1>${keypadCode.code}</h1>
        <p>

        <g:form resource="${this.keypadCode}" method="DELETE">
            <g:link action="edit" id="${keypadCode.id}" class="btn btn-default" role="button">Edit</g:link>
            <g:link action="disable" id="${keypadCode.id}" class="btn btn-info" role="button">Disable</g:link>
                <input class="btn btn-danger" type="submit" value="Delete" onclick="return confirm('Are you sure?');" />
        </g:form>
        </p>
        <p>
            <a class="btn btn-primary btn-lg" href="#" role="button"><span class="glyphicon glyphicon-plus"></span> Add Action</a>
        </p>
    </div>
        <div id="show-keypadCode" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:display bean="keypadCode" />
            <g:form resource="${this.keypadCode}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.keypadCode}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
