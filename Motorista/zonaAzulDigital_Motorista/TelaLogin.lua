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

    local iconeCar = display.newImage("imagens/car.png")

    iconeCar:translate( display.contentWidth/2, display.contentHeight/10 + 10 )

    iconeCar:scale(0.05,0.05)

    local iconeTxt = display.newImage("imagens/zonaAzulLogo.png")

    iconeTxt:translate( display.contentWidth/2*1.014, display.contentHeight/10 + 55)

    iconeTxt:scale(0.5,0.5)

    
    local linha1 = display.newLine(display.contentWidth/6, display.contentHeight/7 * 2.5, display.contentWidth/6 * 5, display.contentHeight/7 * 2.5)
    linha1:setStrokeColor(0.2, 0.2, 1, 1)
    linha1.strokeWidth = 2.65

    local linha2 = display.newLine(display.contentWidth/6, display.contentHeight/7 * 3.5, display.contentWidth/6 * 5, display.contentHeight/7 * 3.5)
    linha2:setStrokeColor(0.2, 0.2, 1, 1)
    linha2.strokeWidth = 2.65
    

    local entrar = widget.newButton({label = "Entrar", labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} }, x = display.contentWidth/2, y = display.contentHeight/3.2 * 2, width = display.contentWidth/1.5, height = display.contentHeight/12, shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 1 }, over={ 0.8, 0.8, 1} } })

    local cadastro = widget.newButton({label = "Cadastre-se",labelColor = { default={ 0.2, 0.2, 1, 1 }, over={0, 0, 0} }, x = display.contentWidth/2, y = display.contentHeight/ 2.6 * 2, width = display.contentWidth/1.5, height = display.contentHeight/12, shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 0 }, over={ 0.8, 0.8, 1 } } })

    entrar:addEventListener("touch", fazerLogin)
	cadastro:addEventListener("touch", fazerCadastro)

    local botaoLocked = widget.newButton(
        {
            width = 20,
            height = 20,
            x = display.contentWidth/7 * 6.1,
            y = display.contentHeight/7 * 3.23,
            defaultFile = "imagens/keyLocked.png",
            onEvent = lockedFunctionLogin
        }
    )

    
    sceneGroup:insert(botaoLocked)
    sceneGroup:insert(linha1)
    sceneGroup:insert(linha2)
    sceneGroup:insert(entrar)
    sceneGroup:insert(cadastro)
    sceneGroup:insert(iconeTxt)
    sceneGroup:insert(iconeCar)

end


function lockedFunctionLogin( event )
    if event.phase == "began" then
        if senha.text ~="" then
            if senha.isSecure == false then
                senha.isSecure = true
            else
                senha.isSecure = false
           end
        end   
    end
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
            
            toast.show("Preencha os campos corretamente!", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.8}})  
        
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
