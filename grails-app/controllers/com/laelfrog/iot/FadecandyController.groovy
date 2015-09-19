package com.laelfrog.iot

class FadecandyController {
	static responseFormats = ['json', 'html']

	FadecandyService fadecandyService

	def index() {

	}

	def mode(String mode) {
		flash.json = fadecandyService.mode(mode)
		redirect(action: 'index')
	}

	def direct() {
		fadecandyService.animation()
		flash.message = "Animation was successful"
		redirect(action: 'index')
	}

	def minecraft() {
		fadecandyService.minecraft()
		flash.successMessage = "Minecraft FTW! HISSS ......BOOM...."
		redirect(action: 'index')
	}

	def gameCharacters() {
		fadecandyService.gameSprites()
		flash.message = "8bit gaming was a success!"
		redirect(action: 'index')
	}
}
