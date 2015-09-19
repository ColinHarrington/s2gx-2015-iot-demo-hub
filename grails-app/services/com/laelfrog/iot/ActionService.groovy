package com.laelfrog.iot

import grails.transaction.Transactional

@Transactional
class ActionService {

	SmsService smsService
	HueService hueService
	FadecandyService fadecandyService

	def execute(Action action) {
		KeypadCode keypadCode = action.keypadCode
		switch (action.actionType) {
			case "sms":
				smsService.sendMessage("Keypad Auth success: ${keypadCode.code}")
				break;

			case "lights:blink":
				hueService.alert()
				break;
			case "lights:blink:longtime":
				hueService.alert(true)
				break;
			case "lights:blink:red":
				hueService.updateGroup([hue: 0, sat: 255, alert: 'select'])
				break;
			case "lights:blink:green":
				hueService.updateGroup([hue: 25500, sat: 255, alert: 'select'])
				break;
			case "lights:red":
				hueService.updateGroup([hue: 0, sat: 255])
				break;
			case "lights:green":
				hueService.updateGroup([hue: 25500, sat: 255])
				break;
			case "lights:blue":
				hueService.updateGroup([hue: 46920, sat: 255])
				break;
			case "lights:off":
				hueService.off()
				break;

			case "fadecandy:success":
				fadecandyService.mode("success")
				break;
			case "fadecandy:fail":
				fadecandyService.mode("fail")
				break;

			case "garage:door:toggle":
				println "Toggling Garage Door, Just believe me for now"
				break;


		}
	}
}
