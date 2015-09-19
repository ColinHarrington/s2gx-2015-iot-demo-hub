package com.laelfrog.iot

class KeypadCode {
	String code
	boolean enabled = true
	boolean deleted = false

	static constraints = {
		code(nullable: false)
	}
}
