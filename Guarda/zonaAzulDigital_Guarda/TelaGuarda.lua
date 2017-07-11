local composer = require("composer")

local scene = composer.newScene()

local funcaoDate = require("Bibliotecas.funcaoDate")

local widget = require("widget")

local webService = require("WebServiceComunication")

local toast = require("plugin.toast")

local letrasBox, numerosBox

local sceneGroup

local restanteCompraCartao

local caixaPositivo

local caixaNegativo

function scene:create()
    sceneGroup = self.view
    --url do icone free :  https://icons8.com/icon/245/car
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

    local consultar = widget.newButton({label = "CONSULTAR", labelColor = { default={ 1, 1, 1 },  over={0, 0, 0} },fontSize = 14 , x = display.contentWidth/2, y = display.contentHeight/2* 1, width = display.contentWidth/1.5, height = display.contentHeight/12,  shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 0.5 }, over={ 0.8, 0.8, 1} } })
    
    
    local linha3 = display.newLine(display.contentWidth/10 * 1, display.contentHeight/10 * 5.8, display.contentWidth/10 * 9, display.contentHeight/10 * 5.8)
    linha3:setStrokeColor(0.2, 0.2, 1, 1)
    linha3.strokeWidth = 2.65


    local cartaoText = display.newText({text = "CARTÃO", x = display.contentWidth/2, y = display.contentHeight/10 * 6.3, whidth = display.contentWidth/2, height = display.contentHeight/10 * 2, fontSize = 20, Textalign = "center"})
    cartaoText:setFillColor(0.2, 0.2, 1, 1)
    
    numeroText = display.newText({text = "Número: ", x = display.contentWidth/2, y = display.contentHeight/10 * 7.7, width = display.contentWidth/10 * 7, height = display.contentHeight/10 * 2, fontSize = 20, Textalign = "left"})
    numeroCartao = display.newText({text = "", x = display.contentWidth/2* 2.1, y = display.contentHeight/10 * 7.7, width = display.contentWidth/10 * 7, height = display.contentHeight/10 * 2, fontSize = 17, Textalign = "left"})
    
    placaText = display.newText({text = "Placa: ", x = display.contentWidth/2, y = display.contentHeight/10 * 8.4,width = display.contentWidth/10 * 7, height = display.contentHeight/10 * 2, fontSize = 20, Textalign = "left"})
    placaCartao = display.newText({text = "", x = display.contentWidth/2 * 1.93, y = display.contentHeight/10 * 8.4, width = display.contentWidth/10 * 7, height = display.contentHeight/10 * 2, fontSize = 17, Textalign = "left"})

    dataCompraText = display.newText({text = "Compra: ", x = display.contentWidth/2, y = display.contentHeight/10 * 9.1, width = display.contentWidth/10 * 7, height = display.contentHeight/10 * 2, fontSize = 20, Textalign = "left"})
    dataCompraCartao = display.newText({text = "", x = display.contentWidth/2*1.89, y = display.contentHeight/10 * 9, width = display.contentWidth/10 * 7, height = display.contentHeight/10 * 2, fontSize = 15, Textalign = "left"})    
    
    local restanteCompraText = display.newText({text = "Restante: ", x = display.contentWidth/2, y = display.contentHeight/10 * 9.8, width = display.contentWidth/10 * 7, height = display.contentHeight/10 * 2, fontSize = 20, Textalign = "left"})
        
    
    local caixaRect = display.newRoundedRect(display.contentWidth/2, display.contentHeight/2 * 1.6,display.contentWidth/2 * 1.6, display.contentHeight/10*3,9 )
    caixaRect:setFillColor( 0.2, 0.2, 1, 1 )

    sceneGroup:insert(caixaRect)
    sceneGroup:insert(cartaoText)
    sceneGroup:insert(numeroText)
    sceneGroup:insert(placaText)
    sceneGroup:insert(dataCompraText)
    sceneGroup:insert(numeroCartao)
    sceneGroup:insert(placaCartao)
    sceneGroup:insert(dataCompraCartao)
    sceneGroup:insert(restanteCompraText)
    


    sceneGroup:insert(linha3)
   
    consultar:addEventListener("touch", consultarPlaca)


    sceneGroup:insert(iconeTxt)
    sceneGroup:insert(iconeCar)
  
    sceneGroup:insert(linha1)
    sceneGroup:insert(linha2)
    sceneGroup:insert(hifen)
    sceneGroup:insert(consultar)

end

function scene:show(event)
    if event.phase == "will" then
        if cartaoConsultado ~= nil then 
                
                if cartaoConsultado.numero ~=0 then
                    numeroCartao.text = cartaoConsultado.numero
                    if cartaoConsultado.placa ~= nil then
                        placaCartao.text = cartaoConsultado.placa.letras.."-"..cartaoConsultado.placa.numeros
                    end
                
                    if cartaoConsultado.dataInicio ~= nil then
                        local hora,data = funcaoDate:formataData(cartaoConsultado.dataInicio)
                        dataCompraCartao.text =" ".. data.."\n"..hora
                    end
                
                    if cartaoConsultado.tempoRestante ~= nil then
                        
                        caixaPositivo = display.newRoundedRect(display.contentWidth/10*7.2, display.contentHeight/10*9.1 ,display.contentWidth/10*3, display.contentHeight/20, 9  )
                        caixaPositivo:setFillColor( 0, 1, 0 )
                        
                        restanteCompraCartao = display.newText({text = "Hr:".. cartaoConsultado.tempoRestante, x = display.contentWidth/2*1.9, y = display.contentHeight/10 *9.9, width = display.contentWidth/10 * 7, height = display.contentHeight/10 * 2, fontSize = 15, Textalign = "left"})
                        restanteCompraCartao:setFillColor(0,0,0)
                        sceneGroup:insert(caixaPositivo) 
                        sceneGroup:insert(restanteCompraCartao)
                    
                    end
                else
                    numeroCartao.text = ""
                    placaCartao.text = ""
                    dataCompraCartao.text = ""

                    caixaNegativo = display.newRoundedRect(display.contentWidth/10*6.8, display.contentHeight/10*9.1 ,display.contentWidth/10*4, display.contentHeight/20, 9  )
                    restanteCompraCartao = display.newText({text = "Tempo Esgotado!", x = display.contentWidth/2*1.7, y = display.contentHeight/10 *9.9, width = display.contentWidth/10 * 7, height = display.contentHeight/10 * 2, fontSize = 15, Textalign = "left"})
                    caixaNegativo:setFillColor( 1, 0, 0 )
                        
                    sceneGroup:insert(caixaNegativo) 
                    sceneGroup:insert(restanteCompraCartao)
                end
        end

        letrasBox = native.newTextField(display.contentWidth/3, display.contentHeight/2.7, display.contentWidth/4, display.contentHeight/15)
        letrasBox.placeholder = "Letras"
        letrasBox.align = "center"
      
        numerosBox = native.newTextField(display.contentWidth/3 * 2, display.contentHeight/2.7, display.contentWidth/3.5, display.contentHeight/15)
        numerosBox.placeholder = "Números"
        numerosBox.align = "center"
        numerosBox.inputType = "number"

     
    end
end

function scene:hide(event)
    if event.phase == "will" then
        
        display.remove(letrasBox)
        display.remove(numerosBox)
        display.remove(restanteCompraCartao)
        display.remove(caixaNegativo)
        display.remove(caixaPositivo)
    end
end

function consultarPlaca(event)
    if event.phase == "ended" then
        if letrasBox.text == "" or numerosBox.text == "" then
            toast.show("Preencha a placa corretamente!", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.7}})  
        else
            webService:consultarPlaca(letrasBox.text, numerosBox.text)
        end
    end
end
 
scene:addEventListener("create", scene)
scene:addEventListener("show", scene)
scene:addEventListener("hide", scene)

return scene