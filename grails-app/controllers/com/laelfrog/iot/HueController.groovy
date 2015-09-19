package com.laelfrog.iot

class HueController {
	static responseFormats = ['json', 'html']

	HueService hueService

	def index() {

	}

	def api() {
		//http://192.168.2.113/debug/clip.html
		respond([result: hueService.api()])
	}

	def on() {
		flash.message = "Lights ON"
		flash.json = hueService.on()
		redirect(action: 'index')
	}

	def off() {
		flash.message = "Lights OFF"
		flash.json = hueService.off()

		redirect(action: 'index')
	}

	def alert() {
		flash.message = "Alert"
		flash.json = hueService.alert()

		redirect(action: 'index')
	}

	def longalert() {
		flash.message = "Long Alert"
		flash.json = hueService.alert(true)

		redirect(action: 'index')
	}

	def red() {
		flash.message = "Red"
		flash.json = hueService.updateGroup([hue: 0, sat: 255])
		redirect(action: 'index')
	}

	def green() {
		flash.message = "Green"
		flash.json = hueService.updateGroup([hue: 25500, sat: 255])
		redirect(action: 'index')
	}

	def blue() {
		flash.message = "Blue"
		flash.json = hueService.updateGroup([hue: 46920, sat: 255])
		redirect(action: 'index')
	}

	def white() {
		flash.message = "White"
		flash.json = hueService.updateGroup([hue: 46920, sat: 0])
		redirect(action: 'index')
	}
}
