local composer = require("composer")

local scene = composer.newScene()

local formataDate = require("funcaoDate")

local cartaoAtivo = {}

 

local sceneGroup

function scene:create(event)
	sceneGroup = self.view

	cartaoAtivo = event.params.cartaoAtivo

	local posicaoX  = display.contentWidth/7
	local posicaoY   = display.contentHeight/6

	local caixaRect = display.newRoundedRect(  display.contentWidth/2,  display.contentHeight/2.2, display.contentWidth/2*1.5, display.contentHeight/2*1.1, 9 )
	caixaRect:setFillColor( 0.2, 0.2, 1, 1 )
		
	local textoTempoRestante = display.newText({text = "Tempo Restante: " , x = posicaoX *2.7 , y = posicaoY*3.4 , fontSize = 20, align = "left" })
	tempoRestante = display.newText({text =  cartaoAtivo.tempoRestante , x = posicaoX *5.3 , y = posicaoY*3.4 , fontSize = 15, align = "left" })
	
	
	
	local textoCartao = display.newText({text = "Cartão" , x = display.contentWidth/2  , y = display.contentHeight/7  , fontSize = 25, align = "center" })
	
	local textoNum = display.newText({text = "Número:" , x = posicaoX*1.9 , y = posicaoY*1.5  , fontSize = 20, align = "left" })
	numero = display.newText({text = cartaoAtivo.numero, x = posicaoX*5.5 , y = posicaoY*1.5  , fontSize = 17, align = "left" })
	
	local textoPlaca = display.newText({text = "Placa: " , x = posicaoX*1.73  , y = posicaoY*2  , fontSize = 20, align = "left" })
	placa = display.newText({text =cartaoAtivo.placa.letras.."-"..cartaoAtivo.placa.numeros, x = posicaoX*5.2  , y = posicaoY*2  , fontSize = 17, align = "left" })
	
	local textoDataInicio = display.newText({text = "Compra: " , x = posicaoX*1.9 , y = posicaoY*2.5  , fontSize = 20, align = "left" })
	
	local hora,data = formataDate:formataData(cartaoAtivo.dataInicio)
	dataText = display.newText({text = data , x = posicaoX*5 , y = posicaoY*2.4  , fontSize = 14, align = "left" })
	horaText = display.newText({text = hora , x = posicaoX*5 , y = posicaoY*2.6  , fontSize = 14, align = "left" })
	
	local textoValor = display.newText({text = "Valor: " , x = posicaoX *1.65 , y = posicaoY*3  , fontSize = 20, align = "left" })
	valor = display.newText({text = "R$: "..cartaoAtivo.valor..",00" , x = posicaoX *5.3 , y = posicaoY*3  , fontSize = 17, align = "left" })
	
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
	sceneGroup:insert(textoTempoRestante)
	sceneGroup:insert(tempoRestante)
	
end



function scene:show(event)

	if event.phase == "will" then
		cartaoAtivo = event.params.cartaoAtivo

		numero.text = cartaoAtivo.numero
		placa.text = cartaoAtivo.placa.letras.."-"..cartaoAtivo.placa.numeros
		local hora,data = formataDate:formataData(cartaoAtivo.dataInicio)
		dataText.text = data
		horaText.text = hora
		valor.text =  "R$: "..cartaoAtivo.valor..",00"
		tempoRestante.text = cartaoAtivo.tempoRestante
	end

end


scene:addEventListener("create",scene)
scene:addEventListener("show",scene)



return scene
