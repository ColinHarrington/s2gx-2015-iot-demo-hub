package com.laelfrog.iot

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class KeypadCodeController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond KeypadCode.list(params), model: [keypadCodeCount: KeypadCode.count()]
	}

	def show(KeypadCode keypadCode) {
		respond keypadCode
	}

	def create() {
		respond new KeypadCode(params)
	}

	@Transactional
	def save(KeypadCode keypadCode) {
		if (keypadCode == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (keypadCode.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond keypadCode.errors, view: 'create'
			return
		}

		keypadCode.save flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'keypadCode.label', default: 'KeypadCode'), keypadCode.id])
				redirect keypadCode
			}
			'*' { respond keypadCode, [status: CREATED] }
		}
	}

	def edit(KeypadCode keypadCode) {
		respond keypadCode
	}

	@Transactional
	def update(KeypadCode keypadCode) {
		if (keypadCode == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (keypadCode.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond keypadCode.errors, view: 'edit'
			return
		}

		keypadCode.save flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'keypadCode.label', default: 'KeypadCode'), keypadCode.id])
				redirect keypadCode
			}
			'*' { respond keypadCode, [status: OK] }
		}
	}

	@Transactional
	def delete(KeypadCode keypadCode) {

		if (keypadCode == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		keypadCode.delete flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'keypadCode.label', default: 'KeypadCode'), keypadCode.id])
				redirect action: "index", method: "GET"
			}
			'*' { render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'keypadCode.label', default: 'KeypadCode'), params.id])
				redirect action: "index", method: "GET"
			}
			'*' { render status: NOT_FOUND }
		}
	}
}
