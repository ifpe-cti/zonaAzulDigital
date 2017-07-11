 local composer = require("composer")

local scene = composer.newScene()

local formataDate = require("funcaoDate")

local cartao = {}

 

local sceneGroup

function scene:create(event)
	sceneGroup = self.view

	cartao = event.params.cartao

	local posicaoX  = display.contentWidth/7
	local posicaoY   = display.contentHeight/6

	local caixaRect = display.newRoundedRect(  display.contentWidth/2,  display.contentHeight/2.2, display.contentWidth/2*1.5, display.contentHeight/2*1.1, 9 )
	caixaRect:setFillColor( 0.2, 0.2, 1, 1 )
		
	local textoCartao = display.newText({text = "Cartão" , x = display.contentWidth/2  , y = display.contentHeight/7  , fontSize = 25, align = "center" })
	
	local textoNum = display.newText({text = "Número:" , x = posicaoX*1.9 , y = posicaoY*1.5  , fontSize = 20, align = "left" })
	numero = display.newText({text = cartao.numero, x = posicaoX*5.5 , y = posicaoY*1.5  , fontSize = 17, align = "left" })
	
	local textoPlaca = display.newText({text = "Placa: " , x = posicaoX*1.73  , y = posicaoY*2  , fontSize = 20, align = "left" })
	placa = display.newText({text =cartao.placa.letras.."-"..cartao.placa.numeros, x = posicaoX*5.2  , y = posicaoY*2  , fontSize = 17, align = "left" })
	
	local textoDataInicio = display.newText({text = "Compra: " , x = posicaoX*1.9 , y = posicaoY*2.5  , fontSize = 20, align = "left" })
	
	local hora,data = formataDate:formataData(cartao.dataInicio)
	dataText = display.newText({text = data , x = posicaoX*5 , y = posicaoY*2.4  , fontSize = 14, align = "left" })
	horaText = display.newText({text = hora , x = posicaoX*5 , y = posicaoY*2.6  , fontSize = 14, align = "left" })
	
	local textoValor = display.newText({text = "Valor: " , x = posicaoX *1.65 , y = posicaoY*3  , fontSize = 20, align = "left" })
	valor = display.newText({text = "R$: "..cartao.valor..",00" , x = posicaoX *5.3 , y = posicaoY*3  , fontSize = 17, align = "left" })

	local dataFimText = display.newText({text = "Validade Cartão: " , x = posicaoX *2.7 , y = posicaoY*3.4 , fontSize = 19, align = "left" })
	local horafim,datafim = formataDate:formataData(cartao.dataFim)
	dataFim = display.newText({text =  datafim.."\n".. horafim , x = posicaoX *5.3 , y = posicaoY*3.4 , fontSize = 12, align = "left" })
	
	sceneGroup:insert(caixaRect)
	sceneGroup:insert(textoCartao)
	sceneGroup:insert(textoNum)
	sceneGroup:insert(numero)
	sceneGroup:insert(textoPlaca)
	sceneGroup:insert(placa)
	sceneGroup:insert(textoDataInicio)
	sceneGroup:insert(textoValor)
	sceneGroup:insert(valor)
	sceneGroup:insert(dataText)
	sceneGroup:insert(horaText)
	sceneGroup:insert(dataFimText)
	sceneGroup:insert(dataFim)
	
	
end



function scene:show(event)

	if event.phase == "will" then
		cartao = event.params.cartao

		numero.text = cartao.numero
		placa.text = cartao.placa.letras.."-"..cartao.placa.numeros
		local hora,data = formataDate:formataData(cartao.dataInicio)
		dataText.text = data
		horaText.text = hora
		valor.text =  "R$: "..cartao.valor..",00"
		local horafim,datafim = formataDate:formataData(cartao.dataFim)
		dataFim.text =  datafim.."\n".. horafim
	end

end


scene:addEventListener("create",scene)
scene:addEventListener("show",scene)



return scene