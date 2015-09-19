package com.laelfrog.iot

class Action {
	String event
	KeypadCode keypadCode
	String actionType

	static constraints = {
		event nullable: false, blank: false
		keypadCode nullable: true
		actionType nullable: false, blank: false
	}
}
