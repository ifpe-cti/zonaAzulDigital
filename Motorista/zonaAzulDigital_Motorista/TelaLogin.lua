local composer = require("composer")

local scene = composer.newScene()

local widget = require("widget")

local motorista = require("motorista")

local crypto = require("crypto")

local webService = require("WebServiceComunication")

local toast = require("plugin.toast")

local cpf 
local senha

function scene:create()

    local sceneGroup = self.view
    
    local linha1 = display.newLine(display.contentWidth/6, display.contentHeight/7 * 2.5, display.contentWidth/6 * 5, display.contentHeight/7 * 2.5)
    linha1:setStrokeColor(0.2, 0.2, 1, 1)
    linha1.strokeWidth = 2.65

    local linha2 = display.newLine(display.contentWidth/6, display.contentHeight/7 * 3.5, display.contentWidth/6 * 5, display.contentHeight/7 * 3.5)
    linha2:setStrokeColor(0.2, 0.2, 1, 1)
    linha2.strokeWidth = 2.65
   
   -- local textCpf = display.newText({text = "CPF:", x = display.contentWidth/2, y = display.contentHeight/4, fontSize = 25})
    --textCpf:setFillColor(0,0,0)
    
    --local textSenha = display.newText({text = "Senha:", x = display.contentWidth/2, y = (display.contentHeight/4.7) * 2, fontSize = 25})
    --textSenha:setFillColor(0,0,0)

    local entrar = widget.newButton({label = "entrar", labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} }, x = display.contentWidth/2, y = display.contentHeight/3.2 * 2, width = display.contentWidth/1.5, height = display.contentHeight/12, shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 1 }, over={ 0.8, 0.8, 1} } })

    local cadastro = widget.newButton({label = "cadastre-se",labelColor = { default={ 0.2, 0.2, 1, 1 }, over={0, 0, 0} }, x = display.contentWidth/2, y = display.contentHeight/ 2.6 * 2, width = display.contentWidth/1.5, height = display.contentHeight/12, shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 0 }, over={ 0.8, 0.8, 1 } } })

    entrar:addEventListener("touch", fazerLogin)
	cadastro:addEventListener("touch", fazerCadastro)

    --sceneGroup:insert(textCpf)
    --sceneGroup:insert(textSenha)
    sceneGroup:insert(linha1)
    sceneGroup:insert(linha2)
    sceneGroup:insert(entrar)
    sceneGroup:insert(cadastro)

end

function scene:show(event)
    if event.phase == "did" then
        
        cpf = native.newTextField(display.contentWidth/2, display.contentHeight/7 * 2.25, display.contentWidth/1.5, 30)
        cpf.inputType = "number"
        cpf.placeholder = "CPF"
        cpf.align = "center"
        
        senha = native.newTextField(display.contentWidth/2, (display.contentHeight/7)*3.25, display.contentWidth/1.5, 30)
        senha.isSecure = true
        senha.placeholder = "Senha"
        senha.align = "center"
    end
end

function scene:hide(event)
    if event.phase == "will" then
        
        display.remove(cpf)
        display.remove(senha)

    end
end


function fazerCadastro(event)
    if event.phase == "ended" then
        composer.gotoScene("TelaCadastro")
    end
end

function fazerLogin(event)
    if event.phase == "ended" then

        if senha.text == "" or cpf.text == "" then
            
            toast.show("Preencha os campos corretamente!", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.7}})  
        
        else
           
            local senhaCrypto = crypto.digest(crypto.md5, senha.text)

            webService:logarMotorista(cpf.text, senhaCrypto)

        end
    end
end

scene:addEventListener("create", scene)
scene:addEventListener("show", scene)
scene:addEventListener("hide", scene)
return scene
