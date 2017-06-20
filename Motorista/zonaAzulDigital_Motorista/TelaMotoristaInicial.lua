local composer = require ("composer")

local scene = composer.newScene()

local menu = require("Bibliotecas.menu_slider")

local widget = require("widget")



local menuMotorista


function scene:create(event)

	local sceneGroup = self.view


    local caixaSaldo = display.newRoundedRect(  display.contentWidth/2,  display.contentHeight/7 * 2, 170, 70, 12 )
    caixaSaldo:setFillColor( 0.2, 0.2, 1, 1 )
    local textSaldo = display.newText({text = "Seu saldo é de:", x = display.contentWidth/2,y = display.contentHeight/7  * 1.75, fontSize = 20})
    local saldo = display.newText({text = "R$ ".. motoristaLogado.credito, x = display.contentWidth/2,y = display.contentHeight/7  * 2.1, fontSize = 20})

    
	menuMotorista = menu:new({
    	data={
        	{text="Inicio", scene="TelaMotoristaInicial"},
        	{text="Status", scene="scene2.lua"},
            {text="Comprar cartão", scene="TelaCompraMotorista"},
            {text="Relátorio de Compras", scene="scene2.lua"},
        	{text="Sair", scene="telaLogin"}
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



function compraCartao(event)

    composer.gotoScene("TelaCompraMotorista",{ params = { motorista = motorista }})

end


scene:addEventListener("create", scene)



return scene