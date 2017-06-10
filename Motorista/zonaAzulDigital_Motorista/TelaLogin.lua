local composer = require("composer")

local scene = composer.newScene()

local widget = require("widget")

local motorista = require("Motorista")

local crypto = require("crypto")

local webService = require("WebServiceComunication")

local cpf 
local senha

function scene:create()

    local sceneGroup = self.view
    
    

    local textCpf = display.newText({text = "CPF:", x = display.contentWidth/2, y = display.contentHeight/4, fontSize = 25})
    textCpf:setFillColor(0,0,0)
    
    local textSenha = display.newText({text = "Senha:", x = display.contentWidth/2, y = (display.contentHeight/4.7) * 2, fontSize = 25})
    textSenha:setFillColor(0,0,0)

    local entrar = widget.newButton({label = "entrar", labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} }, x = display.contentWidth/2, y = display.contentHeight/3.2 * 2, shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 0.5 }, over={ 0.8, 0.8, 1} } })

    local cadastro = widget.newButton({label = "cadastre-se",labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} }, x = display.contentWidth/2, y = display.contentHeight/ 2.6 * 2,shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 0.5 }, over={ 0.8, 0.8, 1 } } })

    entrar:addEventListener("touch", fazerLogin)
	cadastro:addEventListener("touch", fazerCadastro)

    sceneGroup:insert(textCpf)
    sceneGroup:insert(textSenha)
    sceneGroup:insert(entrar)
    sceneGroup:insert(cadastro)

end

function scene:show(event)
    if event.phase == "did" then
        
        cpf = native.newTextField(display.contentWidth/2, display.contentHeight/3, 210, 30)
        senha = native.newTextField(display.contentWidth/2, (display.contentHeight/4)*2, 210, 30)
        senha.isSecure = true

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

      
        local senhaCrypto = crypto.digest(crypto.md5, senha.text)
        
        webService:logarMotorista(cpf.text, senhaCrypto)
        
    end
end

scene:addEventListener("create", scene)
scene:addEventListener("show", scene)
scene:addEventListener("hide", scene)
return scene
