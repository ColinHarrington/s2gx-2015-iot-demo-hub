import com.laelfrog.iot.KeypadCode
import com.laelfrog.iot.SerialPortService

class BootStrap {
    SerialPortService serialPortService

    def init = { servletContext ->

        new KeypadCode(code: '12345').save(failOnError: true)
        new KeypadCode(code: '111').save(failOnError: true)
        new KeypadCode(code: '8675309').save(failOnError: true)
        new KeypadCode(code: '8675309999').save(failOnError: true)
        new KeypadCode(code: '123').save(failOnError: true)

        serialPortService.connect()
    }
    def destroy = {
    }
}
