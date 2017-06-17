local composer = require ("composer")

local scene = composer.newScene()

local menu = require("Bibliotecas.menu_slider")

local motorista = {}

local menuMotorista


function scene:create(event)

	local sceneGroup = self.view

    motorista = event.params.motorista

    local caixaSaldo = display.newRect(  display.contentWidth/2,  display.contentHeight/7 * 2, 170, 70 )
    caixaSaldo:setFillColor( 0.5,0.5,1 )
    local textSaldo = display.newText({text = "Seu saldo é de:", x = display.contentWidth/2,y = display.contentHeight/7  * 1.75, fontSize = 20})
    local saldo = display.newText({text = "R$ ".. motorista.credito, x = display.contentWidth/2,y = display.contentHeight/7  * 2.1, fontSize = 20})

    
	menuMotorista = menu:new({
    	data={
        	{text="Inicio", scene="TelaMotoristaInicial"},
        	{text="Status", scene="scene2.lua"},
            {text="Comprar cartão", scene="scene2.lua"},
            {text="Relátorio de Compras", scene="scene2.lua"},
        	{text="Sair", scene="telaLogin"}
        }, 
    	containers={
        	topContainerProperties={
            	bgColor={0.5,0.5,1}, 
            	strokeColor={1,1,0.8}, 
            	text="Bem vindo ".. motorista.nome.."!",
                }
        }
    })
    
    
    sceneGroup:insert(caixaSaldo)
    sceneGroup:insert(textSaldo)
    sceneGroup:insert(saldo)

end

function scene:hide(event)
     if event.phase == "will" then
        menuMotorista:removeSelf()
        motorista = nil
       
    end

end


scene:addEventListener("create", scene)
scene:addEventListener("hide",scene)


return scene