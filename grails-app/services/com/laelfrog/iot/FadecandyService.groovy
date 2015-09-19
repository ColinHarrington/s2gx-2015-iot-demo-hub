package com.laelfrog.iot

import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import opc.OpcClient
import opc.OpcDevice
import opc.PixelStrip
import org.springframework.beans.factory.annotation.Value

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class FadecandyService {
	static transactional = false

	@Value('${fadecandy.host}')
	String host

	@Value('${fadecandy.port}')
	int port

	@Value('${processing.iot.host}')
	String processingHost

	@Value('${processing.iot.port}')
	int processingPort


	def mode(String mode) {
		RestBuilder rest = restBuilder
		RestResponse resp = rest.get("http://${processingHost}:${processingPort}/$mode")

		return resp.json
	}

	def animation() {
		OpcClient server = new OpcClient(host, port);
		OpcDevice fadeCandy = server.addDevice();
		PixelStrip strip = fadeCandy.addPixelStrip(0, 64);
		println(server.getConfig());
		int wait = 50;

		// Color wipe: in red, green, and blue
		int[] colors = [0xFF0000, 0x00FF00, 0x0000FF].toArray()
		for (int color : colors) {
			for (int i = 0; i < strip.getPixelCount(); i++) {
				strip.setPixelColor(i, color);
				server.show();
				Thread.sleep(wait);
			}
			server.clear();
			server.show();
		}

		// Rainbow
		for (int j = 0; j < 256; j++) {
			for (int i = 0; i < strip.getPixelCount(); i++) {
				strip.setPixelColor(i, server.colorWheel(i + j));
			}
			server.show();
			Thread.sleep(wait);
		}

		// Rainbow cycle
		for (int j = 0; j < 256 * 5; j++) {
			for (int i = 0; i < strip.getPixelCount(); i++) {
				int c = (int) Math.round(i * 256.0 / strip.getPixelCount());
				strip.setPixelColor(i, server.colorWheel(c + j));
			}
			server.show();
			Thread.sleep(wait);
		}

		server.clear();
		server.show();
		server.close();
	}

	void minecraft() {
		BufferedImage img = null;
		try {
			InputStream inputStream = this.class.getClassLoader().getResourceAsStream("minecraft-creeper.png")
			img = ImageIO.read(inputStream);
			println img.height
			println img.width
		} catch (IOException e) {
			log.error "fails", e
		}

		OpcClient server = new OpcClient(host, port);
		OpcDevice fadeCandy = server.addDevice();
		PixelStrip strip = fadeCandy.addPixelStrip(0, 64);
		println(server.getConfig());
		int wait = 50;

		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				int pixel = img.getRGB(x, y)
				strip.setPixelColor(x * 8 + y, pixel)
			}
		}
		server.show();
		Thread.sleep(wait);
		server.close();
	}

	void gameSprites() {
		BufferedImage img = null;
		try {
			InputStream inputStream = this.class.getClassLoader().getResourceAsStream("game-characters.png")
			img = ImageIO.read(inputStream);
			println img.height
			println img.width
		} catch (IOException e) {
			log.error "fails", e
		}

		OpcClient server = new OpcClient(host, port);
		OpcDevice fadeCandy = server.addDevice();
		PixelStrip strip = fadeCandy.addPixelStrip(0, 64);
		println(server.getConfig());
		int wait = 50;
		for (int charx = 0; charx < 4; charx++) {
			for (int chary = 0; chary < 4; chary++) {
				for (int x = 0; x < 8; x++) {
					for (int y = 0; y < 8; y++) {
						int xoffset = charx * 8 + charx * 2
						int yoffset = chary * 8 + chary * 2
						int pixel = img.getRGB(x + xoffset, y + yoffset)
						strip.setPixelColor(x * 8 + y, pixel)
					}
				}
				server.show();
				Thread.sleep(wait)

				Thread.sleep(2 * 1000)
			}
		}
		Thread.sleep(wait);
		server.close();
	}

	protected getRestBuilder() {
		return new RestBuilder()
	}
}
