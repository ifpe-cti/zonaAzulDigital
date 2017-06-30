local composer = require ("composer")

local scene = composer.newScene()

local menu = require("Bibliotecas.menu_slider")

local widget = require("widget")

local menuMotorista

--local saldo

local sceneSaldo 


function scene:create(event)

	local sceneGroup = self.view

    sceneSaldo = self.view

    local caixaSaldo = display.newRoundedRect(  display.contentWidth/2,  display.contentHeight/7 * 2, 170, 70, 12 )
    caixaSaldo:setFillColor( 0.2, 0.2, 1, 1 )
    local textSaldo = display.newText({text = "Seu saldo é de:", x = display.contentWidth/2,y = display.contentHeight/7  * 1.75, fontSize = 20})
    saldo = display.newText({text = "R$ ".. motoristaLogado.credito..".00", x = display.contentWidth/2,y = display.contentHeight/7  * 2.1, fontSize = 20})


    menuMotorista = menu:new({
        data={
            {text="Inicio", scene="TelaMotoristaInicial"},
            {text="Status"},
            {text="Comprar cartão", scene="TelaCompraMotorista"},
            {text="Relátorio de Compras"},
            {text="Sair", callback=destroyMenu}
        }, 
        containers={
            topContainerProperties={
                bgColor={0.2, 0.2, 1, 1}, 
                strokeColor={1,1,0.8}, 
                text="Bem vindo ".. motoristaLogado.nome.."!",

                }
            }
        })

    

    sceneGroup:insert(caixaSaldo)
    sceneGroup:insert(textSaldo)
    sceneGroup:insert(saldo)
    
end


function scene:show(event)
     saldo.text = "R$ ".. motoristaLogado.credito..".00"
     
end


function destroyMenu(event)
    menuMotorista:destroy()
    motoristaLogado = nil
    composer.removeScene("TelaMotoristaInicial")
    composer.gotoScene("TelaLogin")
end

scene:addEventListener("create", scene)
scene:addEventListener("show", scene)


return scene