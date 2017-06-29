local composer = require("composer")

local scene = composer.newScene()

local widget = require("widget")

local placaObject = require("placa")

local webServiceComunication = require("WebServiceComunication")

local toast = require("plugin.toast")

local senha
local letras
local numeros

function scene:create(event )
	
	local sceneGroup = self.view
	local comprar = widget.newButton({label = "Comprar",onRelease = compraCartao, labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} }, x = display.contentWidth/2, y = display.contentHeight/3.2 * 2, width = display.contentWidth/1.5, height = display.contentHeight/12, shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 1 }, over={ 0.8, 0.8, 1} } })
	
    local hifen = display.newLine( display.contentWidth/10*4.9, display.contentHeight/7 *2.9, display.contentWidth/10 * 4.9 + 10, display.contentHeight/7 *2.9)
    local txtPlaca  = display.newText({text = "Placa:", x = display.contentWidth/2, y = display.contentHeight/7 * 2.3, fontSize = 25})

	sceneGroup:insert(comprar)
    sceneGroup:insert(hifen)
    sceneGroup:insert(txtPlaca)
end


function scene:show(event)
    if event.phase == "did" then
        
        letras = native.newTextField(display.contentWidth/10 * 3.7, display.contentHeight/7 *2.9, display.contentWidth/10 * 2.2, 30)
        letras.placeholder = "Letras"
        letras.align = "center"

        numeros = native.newTextField(display.contentWidth/10 * 6.7, display.contentHeight/7 *2.9, display.contentWidth/10 * 2.6, 30)
        numeros.placeholder = "Números"
        numeros.align = "center"
        numeros.inputType = "number"
        
        senha = native.newTextField(display.contentWidth/2, (display.contentHeight/7)*3.70, display.contentWidth/1.5, 30)
        senha.isSecure = true
        senha.placeholder = "Senha"
        senha.align = "center"
    end
end


function scene:hide(event)
    if event.phase == "will" then
        
        display.remove(senha)
        display.remove(letras)
        display.remove(numeros)

    end
end


function compraCartao(event)
 
    local b = true

    if letras.text == "" or numeros.text == "" then
        b = false
        toast.show("Placa vazia!!", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.8}})  
    
    elseif senha.text == "" then
        b= false
        toast.show("Senha é obrigátoria", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.8}})  

    end

    if b == true then
        
        local senhaCrypto = crypto.digest(crypto.md5, senha.text)
        motoristaLogado.senha = senhaCrypto
        

        local placaCompra = placaObject(letras.text,numeros.text)

        webServiceComunication:compraCartao(motoristaLogado,placaCompra)
        motoristaLogado.senha = nil
    end


end


scene:addEventListener("create", scene)
scene:addEventListener("show",scene)
scene:addEventListener("hide",scene)

return scene