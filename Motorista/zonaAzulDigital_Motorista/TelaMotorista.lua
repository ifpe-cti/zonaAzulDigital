local composer = require("composer")

local scene = composer.newScene()


function scene:create(event)
    
    local sceneGroup = self.view

    local motorista = event.params.motorista

    local msg = display.newText({text = "Nome: " .. motorista.nome .. "\n cpf: " .. motorista.cpf .. "\n senha: " .. motorista.senha,  x = display.contentWidth/2, y = (display.contentHeight/7) * 2.75, fontSize = 20 })    
    
    sceneGroup:insert(msg)
    
end

scene:addEventListener("create", scene)

return scene
