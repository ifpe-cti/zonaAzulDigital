local composer = require ("composer")

local scene = composer.newScene()

local menu = require("Bibliotecas.menu_slider")

local widget = require("widget")

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
    
end

function destroyMenu(event)
    menuMotorista:destroy()
    motoristaLogado = nil
    composer.removeScene("TelaMotoristaInicial")
    composer.gotoScene("TelaLogin")
end

scene:addEventListener("create", scene)

return scene