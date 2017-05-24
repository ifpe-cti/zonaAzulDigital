local composer = require("composer")

local widget = require("widget")

local scene = composer.newScene()

local motorista = require("motorista")

--local motorista1 = motorista:new()
local nome
local cpf
local senha

function scene:create()

    local sceneGroup = self.view

    local textNome = display.newText({text = "Nome:", x = display.contentWidth/2, y = display.contentHeight/7  * 1.75, fontSize = 20})
    textNome:setFillColor(0,0,0)

    local textCpf = display.newText({text = "CPF:", x = display.contentWidth/2, y = (display.contentHeight/7) * 2.75, fontSize = 20})
    textCpf:setFillColor(0,0,0)

    local textSenha = display.newText({text = "Senha:", x = display.contentWidth/2, y = (display.contentHeight/7) * 3.75, fontSize = 20})
    textSenha:setFillColor(0,0,0)

    local voltar = widget.newButton({label = "voltar",labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} }, x = display.contentWidth/4, y = display.contentHeight/7 * 5.5, width = 100, height = 40, shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 0.5 }, over={ 0.8, 0.8, 1} }})
    
    local cadastrar = widget.newButton({label = "cadastrar", labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} },x = display.contentWidth/4 * 3, y = display.contentHeight/7 * 5.5, width = 100, height = 40,shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 0.5 }, over={ 0.8, 0.8, 1} }})
    
    voltar:addEventListener("touch", voltarTelaLogin)
    cadastrar:addEventListener("touch", cadastrarMotorista)

    sceneGroup:insert(textNome)
    sceneGroup:insert(textCpf)
    sceneGroup:insert(textSenha)
    sceneGroup:insert(voltar)
    sceneGroup:insert(cadastrar)
end

function scene:show(event)

    if event.phase == "did" then

        nome = native.newTextField(display.contentWidth/6 * 3, display.contentHeight/7 * 2.25, 210, 30) 

        cpf = native.newTextField(display.contentWidth/6 * 3, display.contentHeight/7 * 3.25, 210, 30)

        senha = native.newTextField(display.contentWidth/6 * 3, display.contentHeight/7 * 4.25, 210, 30)
        senha.isSecure = true

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
        
       -- motorista1:init(nome, cpf, senha)
       local motoristaCadastrado = motorista(nome.text, cpf.text, senha.text)
        
        composer.gotoScene("TelaMotorista",{ params = { motorista = motoristaCadastrado }})

    end
end

scene:addEventListener("create", scene)
scene:addEventListener("show", scene)
scene:addEventListener("hide", scene)
return scene
