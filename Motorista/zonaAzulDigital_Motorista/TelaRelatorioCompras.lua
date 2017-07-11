local composer = require("composer")

local scene = composer.newScene()

local widget = require("widget")

local webServiceComunication = require("WebServiceComunication")

local sceneGroup 

function scene:create(  )
	sceneGroup = self.view

	local cadastro = widget.newButton({label = "Hello World",labelColor = { default={ 0.2, 0.2, 1, 1 }, over={0, 0, 0} },fontSize = 18 ,  textOnly = true, x = display.contentWidth/2, y = display.contentHeight/ 2.6 * 2, width = display.contentWidth/1.5, height = display.contentHeight/12, shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 0 }, over={ 0.8, 0.8, 1 } } })
	cadastro:addEventListener("touch", relatorio)

end


function relatorio(event) 
	
	if event.phase == "began" then
		webServiceComunication:consultarCompraTodosCartoes(motoristaLogado)

	end

end



scene:addEventListener("create", scene)
return scene