local composer = require("composer")

local scene = composer.newScene()

local widget = require("widget")

local webService = require("WebServiceComunication")

local toast = require("plugin.toast")

local placa

function scene:create()
    local sceneGroup = self.view

   -- local textPlaca = display.newText({text = "Placa:", x = display.contentWidth/2, y = display.contentHeight/4, fontSize = 23})
    --textPlaca:setFillColor(0,0,0)

    local consultar = widget.newButton({label = "consultar", labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} }, x = display.contentWidth/2, y = display.contentHeight/2, width = display.contentWidth/2, height = display.contentHeight/12,  shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 0.5 }, over={ 0.8, 0.8, 1} } })

    consultar:addEventListener("touch", consultarPlaca)
    
    --sceneGroup:insert(textPlaca)
    sceneGroup:insert(consultar)

end

function scene:show(event)
    if event.phase == "did" then
        placa = native.newTextField(display.contentWidth/2, display.contentHeight/2.8, display.contentWidth/2, display.contentHeight/15)
        placa.placeholder = "Placa"
        placa.align = "center"
    end
end

function scene:hide(event)
    if event.phase == "will" then
        
        display.remove(placa)

    end
end

function consultarPlaca(event)
    if event.phase == "ended" then
        if placa.text == "" then
            toast.show("Preencha a placa corretamente!", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.7}})  
        else
            webService:consultarPlaca(placa.text)
        end
    end
end


scene:addEventListener("create", scene)
scene:addEventListener("show", scene)
scene:addEventListener("hide", scene)

return scene