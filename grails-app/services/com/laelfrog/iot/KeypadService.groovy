package com.laelfrog.iot

import grails.transaction.Transactional

@Transactional
class KeypadService {

	ActionService actionService

	KeypadCode auth(String code) {
		if (code == '') {
			return null
		}
		KeypadCode keypadCode = KeypadCode.findByCodeAndEnabledAndDeleted(code, true, false)

		if (keypadCode) {
			notify("keypadAuthSuccess", keypadCode)
		} else {
			notify("keypadAuthFail", code)
		}

		return keypadCode

	}

	void handleAuthSuccess(KeypadCode keypadCode) {
		println "Zuccess ${keypadCode.code}"

		def criteria = Action.createCriteria()
		List<Action> actions = criteria {
			eq('event', 'keypadAuthSuccess')
			or {
				eq('keypadCode', keypadCode)
				isNull("keypadCode")
			}
		}
		println actions
		for (Action action in actions) {
			println action.actionType
			println action.event
			actionService.execute(action)
		}
	}

	void handleAuthFail(String code) {
		println "fail '${code}'"
		List<Action> actions = Action.findAllByEventAndKeypadCodeIsNull('keypadAuthFail')
		for (Action action in actions) {
			println action.actionType
			println action.event
			actionService.execute(action)
		}
	}
}
