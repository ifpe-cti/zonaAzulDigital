local composer = require("composer")

local scene = composer.newScene()

local widget = require("widget")

local webService = require("WebServiceComunication")

local toast = require("plugin.toast")

local letras, numeros

function scene:create()
    local sceneGroup = self.view

    local iconeCar = display.newImage("Imagens/Car.png")

    iconeCar:translate(display.contentWidth/2, display.contentHeight/10)

    iconeCar:scale(0.05,0.05)

    local iconeTxt = display.newImage("Imagens/ZonaAzulLogo.png")

    iconeTxt:translate( display.contentWidth/2*1.014, display.contentHeight/10 + 55)

    iconeTxt:scale(0.5,0.5)

    local linha1 = display.newLine(display.contentWidth/6 * 1.3, display.contentHeight/10 * 4.1, display.contentWidth/6 * 2.73, display.contentHeight/10 * 4.1)
    linha1:setStrokeColor(0.2, 0.2, 1, 1)
    linha1.strokeWidth = 2.65

    local linha2 = display.newLine(display.contentWidth/6 * 3.15, display.contentHeight/10 * 4.1, display.contentWidth/6 * 4.85, display.contentHeight/10 * 4.1)
    linha2:setStrokeColor(0.2, 0.2, 1, 1)
    linha2.strokeWidth = 2.65
    
    local hifen = display.newLine(display.contentWidth/2.1, display.contentHeight/2.7, display.contentWidth/1.95, display.contentHeight/2.7)
    hifen:setStrokeColor(0, 0, 0)
    hifen.strokeWidth = 2.65

    local placa = display.newText({text = "Placa", x = display.contentWidth/2, y = display.contentHeight/10 * 2.75, fontSize = 25})
    placa:setFillColor( 0.2, 0.2, 1, 1)

    local consultar = widget.newButton({label = "Consultar", labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} }, x = display.contentWidth/2, y = display.contentHeight/2* 1, width = display.contentWidth/1.5, height = display.contentHeight/12,  shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 0.5 }, over={ 0.8, 0.8, 1} } })

    --cartaoConsultado.placa.letras cartaoConsultado.placa.numeros cartaoConsultado.dataEntacionamento  cartaoConsultado.numero  

    local linha3 = display.newLine(display.contentWidth/10 * 1, display.contentHeight/10 * 5.8, display.contentWidth/10 * 9, display.contentHeight/10 * 5.8)
    linha3:setStrokeColor(0.2, 0.2, 1, 1)
    linha3.strokeWidth = 2.65

    local cartaoText = display.newText({text = "CARTÃO", x = display.contentWidth/2, y = display.contentHeight/10 * 6.5, whidth = display.contentWidth/2, height = display.contentHeight/10 * 2, fontSize = 20, Textalign = "center"})
    cartaoText:setFillColor(0.2, 0.2, 1, 1)
    local numeroText = display.newText({text = "Número: ", x = display.contentWidth/2, y = display.contentHeight/10 * 7.5, whidth = display.contentWidth/10 * 7, height = display.contentHeight/10 * 2, fontSize = 20, Textalign = "left"})
    
    local placaText = display.newText({text = "Placa: ", x = display.contentWidth/2, y = display.contentHeight/10 * 8.5,whidth = display.contentWidth/10 * 7, height = display.contentHeight/10 * 2, fontSize = 20, Textalign = "left"})
    
    local dataText = display.newText({text = "Tempo Restante: ", x = display.contentWidth/2, y = display.contentHeight/10 * 9.5, whidth = display.contentWidth/10 * 7, height = display.contentHeight/10 * 2, fontSize = 20, Textalign = "left"})
   
    consultar:addEventListener("touch", consultarPlaca)


    sceneGroup:insert(iconeTxt)
    sceneGroup:insert(iconeCar)
    sceneGroup:insert(numeroText)
    sceneGroup:insert(placaText)
    sceneGroup:insert(dataText)
    sceneGroup:insert(linha1)
    sceneGroup:insert(linha2)
    sceneGroup:insert(hifen)
    sceneGroup:insert(consultar)

end

function scene:show(event)
    if event.phase == "did" then
        letras = native.newTextField(display.contentWidth/3, display.contentHeight/2.7, display.contentWidth/4, display.contentHeight/15)
        letras.placeholder = "Letras"
        letras.align = "center"
      
        numeros = native.newTextField(display.contentWidth/3 * 2, display.contentHeight/2.7, display.contentWidth/3.5, display.contentHeight/15)
        numeros.placeholder = "Números"
        numeros.align = "center"
        numeros.inputType = "number"

        if cartaoConsultado.letras ~= nil then
        
            numeroText.text = numeroText.text .. cartaoConsultado.numero

            placaText.text = placaText.text .. cartaoConsultado.placa.letras .. "-" .. cartaoConsultado.placa.numeros

            dataText.text = dataText.text .. cartaoConsultado.dataFim
        

        end     
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