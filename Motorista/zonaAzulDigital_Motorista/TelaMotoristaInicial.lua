local composer = require ("composer")

local scene = composer.newScene()

local menu = require("Bibliotecas.menu_slider")



function scene:create()

	local sceneGroup = self.view

	local newMenu = menu:new({
    	data={
        	{text="Inicio", scene="TelaMotoristaInicial"},
        	{text="Status", scene="scene2.lua"},
        	{text="Sair", scene="scene3.lua"}
        }, 
    	containers={
        	topContainerProperties={
            	bgColor={1,1,1}, 
            	strokeColor={0.3, 0.4, 1}, 
            	text="Bem vindo!",
        }
    }
})







end


scene:addEventListener("create", scene)


return scene