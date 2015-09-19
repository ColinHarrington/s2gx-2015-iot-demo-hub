package com.laelfrog.iot

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ActionController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Action.list(params), model: [actionCount: Action.count()]
	}

	def show(Action action) {
		respond action
	}

	def create() {
		respond new Action(params)
	}

	@Transactional
	def save(Action action) {
		if (action == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (action.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond action.errors, view: 'create'
			return
		}

		action.save flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'action.label', default: 'Action'), action.id])
				redirect action
			}
			'*' { respond action, [status: CREATED] }
		}
	}

	def edit(Action action) {
		respond action
	}

	@Transactional
	def update(Action action) {
		if (action == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (action.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond action.errors, view: 'edit'
			return
		}

		action.save flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'action.label', default: 'Action'), action.id])
				redirect action
			}
			'*' { respond action, [status: OK] }
		}
	}

	@Transactional
	def delete(Action action) {

		if (action == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		action.delete flush: true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'action.label', default: 'Action'), action.id])
				redirect action: "index", method: "GET"
			}
			'*' { render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'action.label', default: 'Action'), params.id])
				redirect action: "index", method: "GET"
			}
			'*' { render status: NOT_FOUND }
		}
	}
}
