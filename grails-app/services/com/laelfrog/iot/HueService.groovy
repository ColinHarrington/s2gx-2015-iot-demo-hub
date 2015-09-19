package com.laelfrog.iot

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import org.grails.web.json.JSONElement
import org.springframework.beans.factory.annotation.Value

class HueService {
	@Value('${hue.username}')
	String username

	@Value('${hue.host}')
	String host


	JSONElement api() {
		RestResponse resp = restBuilder.get("http://${host}/api/${username}")
		return resp.json
	}

	JSONElement on() {
		return updateGroup([on: true])
	}

	JSONElement off() {
		return updateGroup([on: false])
	}

	JSONElement alert(boolean longAlert = false) {
		String alertValue = longAlert ? "lselect" : "select"
		return updateGroup([alert: alertValue])
	}

	JSONElement updateGroup(Map payload) {
		RestResponse resp = restBuilder.put("http://${host}/api/${username}/groups/1/action") {
			json payload
		}

		return resp.json
	}

	protected getRestBuilder() {
		return new RestBuilder()
	}
}
