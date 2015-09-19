package com.laelfrog.iot

import groovy.transform.Synchronized
import groovy.util.logging.Slf4j
import purejavacomm.SerialPort
import purejavacomm.SerialPortEvent
import purejavacomm.SerialPortEventListener

@Slf4j
class KeypadCodeSerialPortEventListener implements SerialPortEventListener {
	KeypadService keypadService

	@Override
	void serialEvent(SerialPortEvent event) {
		switch (event.eventType) {
			case SerialPortEvent.DATA_AVAILABLE:
				log.debug "DATA_AVAILABLE"
				handleData(event.source)
				break;
			case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
				log.debug "OUTPUT_BUFFER_EMPTY"
				break;
			case SerialPortEvent.BI:
			case SerialPortEvent.CD:
			case SerialPortEvent.CTS:
			case SerialPortEvent.DSR:
			case SerialPortEvent.FE:
			case SerialPortEvent.OE:
			case SerialPortEvent.PE:
			case SerialPortEvent.RI:
				log.debug "unhandled SerialPortEvent(${event.eventType}"
				break;
		}
	}

	@Synchronized
	void handleData(SerialPort port) {
		InputStreamReader inputStreamReader = new InputStreamReader(port.inputStream)
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
		String code = bufferedReader.readLine()

		KeypadCode keycode = keypadService.auth(code)
		String result = keycode ? 'success' : 'failure'
		log.info "Code: ${code} ==> $result"

		OutputStream os = port.outputStream
		Writer writer = os.newWriter()

		writer.print(result)
		writer.flush()
	}
}
