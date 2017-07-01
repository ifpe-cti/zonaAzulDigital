local composer = require ("composer")

local scene = composer.newScene()

local menu = require("Bibliotecas.menu_slider")

local widget = require("widget")

local webServiceComunication = require("webServiceComunication")

local menuMotorista

function scene:create(event)

	local sceneGroup = self.view
    

    menuMotorista = menu:new({
        data={
            {text="Cartões Ativos", scene="TelaMotoristaInicial"},
            {text="Comprar cartão", scene="TelaCompraMotorista"},
            {text="Histórico de Compras"},
            {text="Sair", callback=destroyMenu}
        }, 
        containers={
            topContainerProperties={
                bgColor={0.2, 0.2, 1, 1}, 
                strokeColor={1,1,0.8}, 
                text="ZonAzul Digital",

                }
            }
        })
    local botaoAtualizar = widget.newButton(
        {
            width = 50,
            height = 40,
            x = display.contentWidth/7 * 6.4 ,
            y = display.contentHeight/7 ,
            defaultFile = "Imagens/AtualizarApp.png",
            onEvent = atualizaDados
        }
    )
    
    sceneGroup:insert(botaoAtualizar)
    
end


function atualizaDados(event)
    if event.phase == "began" then

        webServiceComunication:atualizarMotorista(motoristaLogado)
    end
end



function destroyMenu(event)
    menuMotorista:destroy()
    motoristaLogado = nil
    composer.removeScene("TelaMotoristaInicial")
    composer.gotoScene("TelaLogin")
end

scene:addEventListener("create", scene)

return scene