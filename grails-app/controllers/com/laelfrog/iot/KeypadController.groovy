package com.laelfrog.iot

import grails.converters.JSON

class KeypadController {
	KeypadService keypadService

	def auth(String code) {
		KeypadCode keypadCode = keypadService.auth(code)
		if (keypadCode) {
			render([status: "success"] as JSON)
		} else {
			render([status: "failure"] as JSON)
		}
	}
}
