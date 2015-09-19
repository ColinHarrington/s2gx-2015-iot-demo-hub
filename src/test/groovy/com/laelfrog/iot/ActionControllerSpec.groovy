package com.laelfrog.iot

import grails.test.mixin.*
import spock.lang.*

@TestFor(ActionController)
@Mock(Action)
class ActionControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.actionList
            model.actionCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.action!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def action = new Action()
            action.validate()
            controller.save(action)

        then:"The create view is rendered again with the correct model"
            model.action!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            action = new Action(params)

            controller.save(action)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/action/show/1'
            controller.flash.message != null
            Action.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def action = new Action(params)
            controller.show(action)

        then:"A model is populated containing the domain instance"
            model.action == action
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def action = new Action(params)
            controller.edit(action)

        then:"A model is populated containing the domain instance"
            model.action == action
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/action/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def action = new Action()
            action.validate()
            controller.update(action)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.action == action

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            action = new Action(params).save(flush: true)
            controller.update(action)

        then:"A redirect is issued to the show action"
            action != null
            response.redirectedUrl == "/action/show/$action.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/action/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def action = new Action(params).save(flush: true)

        then:"It exists"
            Action.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(action)

        then:"The instance is deleted"
            Action.count() == 0
            response.redirectedUrl == '/action/index'
            flash.message != null
    }
}
