package com.laelfrog.iot

import purejavacomm.CommPortIdentifier
import purejavacomm.PortInUseException
import purejavacomm.SerialPort

class SerialPortService {
	static transactional = false

	KeypadService keypadService

	SerialPort serialPort

	List<String> portNames = []

	boolean connect() {
		portNames = detectPotentialSerialPorts()

		for (String portName in portNames) {
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName)
			try {
				serialPort = (SerialPort) portIdentifier.open("iothub", 2000);
			} catch (PortInUseException piue) {
				log.warn("Port is already in use", piue)
				continue
			}

			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
			serialPort.setDTR(true); // needed for Arduino Leonardo and Micro

			serialPort.notifyOnDataAvailable(true)
			serialPort.notifyOnOutputEmpty(true)
			serialPort.notifyOnBreakInterrupt(true)
			serialPort.notifyOnCarrierDetect(true)
			serialPort.notifyOnFramingError(true)
			serialPort.notifyOnCTS(true)
			serialPort.notifyOnDSR(true)
			serialPort.notifyOnOverrunError(true)
			serialPort.notifyOnParityError(true)
			serialPort.notifyOnRingIndicator(true)

			def eventListener = new KeypadCodeSerialPortEventListener()
			eventListener.keypadService = keypadService

			serialPort.addEventListener(eventListener)

			return true
		}

		log.warn("No SerialPort Identified")
		return false
	}

	def close() {
		println "Closing"
		serialPort?.close()
		serialPort = null
	}

	String getName() {
		println serialPort?.dump()
		return serialPort?.name
	}

	protected List<String> detectPotentialSerialPorts() {
		log.info "Searching for USB Devices"
		Enumeration portIdentifiers = CommPortIdentifier.getPortIdentifiers()

		List<String> ports = []
		portIdentifiers.each { CommPortIdentifier commPortIdentifier ->
			if (commPortIdentifier.portType == CommPortIdentifier.PORT_SERIAL) {
				switch (commPortIdentifier.name) {
					case ~/ttyUSB\d+/:
					case ~/ttyACM\d+/:
						log.trace "Serial Port Found: ${commPortIdentifier.dump()}"
						ports << commPortIdentifier.name
						break;
				}
			}
		}

		log.debug("Found ${ports.size()} USB Serial devices: ${ports.join(', ')}")

		return ports
	}
}


