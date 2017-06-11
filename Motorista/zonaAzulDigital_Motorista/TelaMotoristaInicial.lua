local composer = require ("composer")

local scene = composer.newScene()

local menu = require("Bibliotecas.menu_slider")



function scene:create()

	local sceneGroup = self.view

	local newMenu = menu:new({
    	data={
        	{text="Home", scene="TelaMotoristaInicial"},
        	{text="States", scene="scene2.lua"},
        	{text="Countries'", scene="scene3.lua"}
        }, 
    	containers={
        	topContainerProperties={
            	bgColor={0.5,0.5,1}, 
            	strokeColor={1,1,0.8}, 
            	text="Bem vindo!",
        }
    }
})







end


scene:addEventListener("create", scene)


return scene