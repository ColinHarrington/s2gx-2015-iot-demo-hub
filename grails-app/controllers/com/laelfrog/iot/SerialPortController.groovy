package com.laelfrog.iot

class SerialPortController {

	static responseFormats = ['json', 'html']

	SerialPortService serialPortService

	def index() {
		respond([port: serialPortService.serialPort])
	}

	def connect() {
		boolean connected = serialPortService.connect()
		respond([
				connected: connected,
				port     : serialPortService.serialPort
		])
	}

	def close() {
		serialPortService.close()
		respond([closed: true])
	}
}
