local composer = require("composer")

local scene = composer.newScene()

local widget = require("widget")

local webService = require("WebServiceComunication")

local toast = require("plugin.toast")

local letras, numeros

function scene:create()
    local sceneGroup = self.view

    local linha1 = display.newLine(display.contentWidth/6 * 1.3, display.contentHeight/4* 1.59, display.contentWidth/6 * 2.73, display.contentHeight/4 * 1.59)
    linha1:setStrokeColor(0.2, 0.2, 1, 1)
    linha1.strokeWidth = 2.65

    local linha2 = display.newLine(display.contentWidth/6 * 3.29, display.contentHeight/4* 1.59, display.contentWidth/6 * 4.73, display.contentHeight/4 * 1.59)
    linha2:setStrokeColor(0.2, 0.2, 1, 1)
    linha2.strokeWidth = 2.65
    
    local hifen = display.newLine(display.contentWidth/2.1, display.contentHeight/2.8, display.contentWidth/1.95, display.contentHeight/2.8)
    hifen:setStrokeColor(0, 0, 0)
    hifen.strokeWidth = 2.65

    local placa = display.newText({text = "Placa", x = display.contentWidth/2, y = display.contentHeight/4, fontSize = 25})
    placa:setFillColor( 0, 0, 0 )

    local consultar = widget.newButton({label = "consultar", labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} }, x = display.contentWidth/2, y = display.contentHeight/2* 1.1, width = display.contentWidth/1.5, height = display.contentHeight/12,  shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 0.5 }, over={ 0.8, 0.8, 1} } })

    consultar:addEventListener("touch", consultarPlaca)

    sceneGroup:insert(linha1)
    sceneGroup:insert(linha2)
    sceneGroup:insert(hifen)
    sceneGroup:insert(consultar)

end

function scene:show(event)
    if event.phase == "did" then
        letras = native.newTextField(display.contentWidth/3, display.contentHeight/2.8, display.contentWidth/4, display.contentHeight/15)
        letras.placeholder = "letras"
        letras.align = "center"
      
        numeros = native.newTextField(display.contentWidth/3 * 2, display.contentHeight/2.8, display.contentWidth/4, display.contentHeight/15)
        numeros.placeholder = "numeros"
        numeros.align = "center"
        numeros.inputType = "number"

        
    end
end

function scene:hide(event)
    if event.phase == "will" then
        
        display.remove(letras)
        display.remove(numeros)

    end
end

function consultarPlaca(event)
    if event.phase == "ended" then
        if letras.text == "" or numeros.text == "" then
            toast.show("Preencha a placa corretamente!", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.7}})  
        else
            webService:consultarPlaca(letras.text, numeros.text)
        end
    end
end
 
scene:addEventListener("create", scene)
scene:addEventListener("show", scene)
scene:addEventListener("hide", scene)

return scene