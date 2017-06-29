local composer = require("composer")

local widget = require("widget")

local scene = composer.newScene()

local motorista = require("motorista")

local crypto = require("crypto")

local webService = require("WebServiceComunication")

local toast = require("plugin.toast")

local nome
local cpf
local senha

function scene:create()

    local sceneGroup = self.view

    local iconeCar = display.newImage("Imagens/Car.png")

    iconeCar:translate( display.contentWidth/2, display.contentHeight/10 + 10 )

    iconeCar:scale(0.05,0.05)



    local iconeTxt = display.newImage("Imagens/ZonaAzulLogo.png")

    iconeTxt:translate( display.contentWidth/2, display.contentHeight/10 + 55)

    iconeTxt:scale(0.3,0.3)


    local linha1 = display.newLine(display.contentWidth/6, display.contentHeight/7 * 2.5, display.contentWidth/6 * 5, display.contentHeight/7 * 2.5)
    linha1:setStrokeColor(0.2, 0.2, 1, 1)
    linha1.strokeWidth = 2.65

    local linha2 = display.newLine(display.contentWidth/6, display.contentHeight/7 * 3.5, display.contentWidth/6 * 5, display.contentHeight/7 * 3.5)
    linha2:setStrokeColor(0.2, 0.2, 1, 1)
    linha2.strokeWidth = 2.65

    
    local linha3 = display.newLine(display.contentWidth/6, display.contentHeight/7 * 4.5, display.contentWidth/6 * 5, display.contentHeight/7 * 4.5)
    linha3:setStrokeColor(0.2, 0.2, 1, 1)
    linha3.strokeWidth = 2.65
    
    local voltar = widget.newButton({label = "Voltar",labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} }, x = display.contentWidth/4, y = display.contentHeight/7 * 5.5, width = display.contentWidth/2.9, height = display.contentHeight/13, shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 1 }, over={ 0.8, 0.8, 1} }})
    
    local cadastrar = widget.newButton({label = "Cadastrar", labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} },x = display.contentWidth/4 * 3, y = display.contentHeight/7 * 5.5, width = display.contentWidth/2.9, height = display.contentHeight/13,shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 1 }, over={ 0.8, 0.8, 1} }})
    
    voltar:addEventListener("touch", voltarTelaLogin)
    cadastrar:addEventListener("touch", cadastrarMotorista)

    
    sceneGroup:insert(linha1)
    sceneGroup:insert(linha2)
    sceneGroup:insert(linha3)
    sceneGroup:insert(voltar)
    sceneGroup:insert(cadastrar)
    sceneGroup:insert(iconeTxt)
    sceneGroup:insert(iconeCar)
end

function scene:show(event)

    if event.phase == "did" then

        nome = native.newTextField(display.contentWidth/6 * 3, display.contentHeight/7 * 2.25, display.contentWidth/1.5, 30) 
        nome.placeholder = "Nome"
        nome.align = "center"

        cpf = native.newTextField(display.contentWidth/6 * 3, display.contentHeight/7 * 3.25, display.contentWidth/1.5, 30)
        cpf.inputType = "number"
        cpf.placeholder = "CPF"
        cpf.align = "center"
        
        senha = native.newTextField(display.contentWidth/6 * 3, display.contentHeight/7 * 4.25,  display.contentWidth/1.5, 30)
        senha.isSecure = true
        senha.placeholder = "Senha"
        senha.align = "center"
    end
end

function scene:hide(event)
    if event.phase == "will" then
        display.remove(nome)
        display.remove(cpf)
        display.remove(senha)
    end
end
function voltarTelaLogin(event)
    if event.phase == "ended" then
        composer.gotoScene("TelaLogin")
    end
end

function cadastrarMotorista(event)

    if event.phase == "began" then
        
        local validacaoCampos = validaCampos(nome.text,cpf.text,senha.text)

        if validacaoCampos == false then

            local senhaCrypto = crypto.digest(crypto.md5, senha.text)
        
            local motoristaCadastrado = motorista(nome.text, cpf.text, senhaCrypto)
        
            webService:cadastrarMotorista(motoristaCadastrado)
        end
      
    end
end


function validaCampos(nome,cpf,senha)

    if nome == "" then
        
        toast.show("Nome inválido", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.8}}) 
        return true 

    elseif cpf == "" then
        
        toast.show("CPF inválido", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.8}})  
        return true

    elseif senha == "" then
        
        toast.show("Senha inválida", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.8}})  
        return true

    end

    return false
end

scene:addEventListener("create", scene)
scene:addEventListener("show", scene)
scene:addEventListener("hide", scene)

return scene