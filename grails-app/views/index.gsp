<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Welcome to Grails</title>
    </head>
    <body>

    <div class="row">
        <div class="col-md-3">
            <div id="status" role="complementary" class="well">
                <h1>Application Status</h1>
                <ul>
                    <li>Environment: ${grails.util.Environment.current.name}</li>
                    <li>App profile: ${grailsApplication.config.grails?.profile}</li>
                    <li>App version: <g:meta name="info.app.version"/></li>
                    <li>Grails version: <g:meta name="info.app.grailsVersion"/></li>
                    <li>Groovy version: ${GroovySystem.getVersion()}</li>
                    <li>JVM version: ${System.getProperty('java.version')}</li>
                    <li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
                </ul>
                <h1>Artefacts</h1>
                <ul>
                    <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
                    <li>Domains: ${grailsApplication.domainClasses.size()}</li>
                    <li>Services: ${grailsApplication.serviceClasses.size()}</li>
                    <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
                </ul>
                <h1>Installed Plugins</h1>
                <ul>
                    <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                        <li>${plugin.name} - ${plugin.version}</li>
                    </g:each>
                </ul>
            </div>
        </div>
        <div class="col-md-9">
            <div id="page-body" role="main">
                <h1>Welcome to Grails</h1>
                <p>Congratulations, you have successfully started your first Grails application! At the moment
                this is the default page, feel free to modify it to either redirect to a controller or display whatever
                content you may choose. Below is a list of controllers that are currently deployed in this application,
                click on each to execute its default action:</p>

                <div id="controller-list" role="navigation">
                    <h2>Available Controllers:</h2>
                    <ul>
                        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                            <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
                        </g:each>
                    </ul>
                </div>
            </div>
        </div>
    </div>


    </body>
</html>
