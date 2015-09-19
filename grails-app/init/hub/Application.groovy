package hub

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration

class Application extends GrailsAutoConfiguration {
	static void main(String[] args) {
		GrailsApp.run(Application, args)
	}

	void onStartup(Map<String, Object> event) {
		super.onStartup(event)


		def keypadService = applicationContext.getBean('keypadService')
		keypadService.on('keypadAuthSuccess', {
			keypadService.handleAuthSuccess(it)
		})
		keypadService.on('keypadAuthFail', { code ->
			keypadService.handleAuthFail(code)
		})
	}
}