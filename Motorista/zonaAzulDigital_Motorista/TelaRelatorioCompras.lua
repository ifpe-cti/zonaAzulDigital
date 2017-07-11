local composer = require("composer")

local scene = composer.newScene()

local widget = require("widget")

local webServiceComunication = require("WebServiceComunication")

local funcaoDate = require("funcaoDate")

local sceneGroup 

local caixaInfo

local textoNaoPossuiCompras

local tabelaCaixasCompras = {}

local iFor = 1

local jFor = 8

function scene:create(  )
	sceneGroup = self.view

	local textoCartoes = display.newText({text = "Todas as Compras:",x = display.contentWidth/2,y = display.contentHeight/8*1.3,fontSize = 20})
	sceneGroup:insert(textoCartoes)
end

function scene:show(event)

	if event.phase == "will" then
		webServiceComunication:consultarCompraTodosCartoes(motoristaLogado)
		mostraCompraCartoes(iFor,jFor)

	end
end

function relatorio(event) 
	
	if event.phase == "began" then
		webServiceComunication:consultarCompraTodosCartoes(motoristaLogado)

	end

end



function mostraCompraCartoes(iFor,jFor)
     local posicaoY = display.contentHeight/8
        local posicaoX = display.contentWidth/2
        
        if #todosCartoes > 0  then
            if #todosCartoes>8 then
                local proximo = widget.newButton({label = "Próximo",onRelease = onTouchProximo, labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} }, fontSize = 14 , x = display.contentWidth/2, y = display.contentHeight/10 * 9.79, width = display.contentWidth/2, height = display.contentHeight/17, shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 1 }, over={ 0.8, 0.8, 1} } })
                sceneGroup:insert(proximo)
            end

            
            for i = iFor, jFor do
            if todosCartoes[i] ~=nil then         
                if i%2 ~= 0 then
                    
                    local caixa = display.newRoundedRect(  posicaoX,  posicaoY* 2, display.contentWidth, display.contentHeight/12, 9 )
                    
                    caixa.cartao = todosCartoes[i]
                    
                    caixa:setFillColor( 0.2, 0.2, 1, 1 )
                    caixa:addEventListener("touch",onTouchCompras)
                    
                    local placaText = display.newText({text = todosCartoes[i].placa.letras .. "-"..todosCartoes[i].placa.numeros , x = display.contentWidth/7 *1.2 , y = posicaoY*2 , fontSize = 15, align = "left" })
                    local dataText = display.newText({text = "Data:" , x = display.contentWidth/7 * 3.5  , y = posicaoY*2  , fontSize = 15, align = "left" })
                    local hora,data = funcaoDate:formataData(todosCartoes[i].dataInicio)
                    local dataCompra = display.newText({text =data .. "\n"..hora  , x = display.contentWidth/7 *5.5 , y = posicaoY*2 , fontSize = 15, align = "left" })
                    
                    caixa.placaText = placaText
                    caixa.dataText = dataText
                    caixa.dataCompra = dataCompra

                    sceneGroup:insert(caixa)
                    sceneGroup:insert(placaText)
                    sceneGroup:insert(dataText)
                    sceneGroup:insert(dataCompra)

                    table.insert(tabelaCaixasCompras,caixa)
                    
                else
                    local caixa = display.newRoundedRect(  posicaoX,  posicaoY* 2, display.contentWidth, display.contentHeight/12, 9 )
                    
                    caixa.cartao = todosCartoes[i]
                    caixa:setFillColor( 0.8, 0.8, 1 )
                    caixa:addEventListener("touch",onTouchCompras)


                    local placaText = display.newText({text = todosCartoes[i].placa.letras .. "-"..todosCartoes[i].placa.numeros , x = display.contentWidth/7 *1.2 , y = posicaoY*2 , fontSize = 15, align = "left" })
                    local dataText = display.newText({text = "Data:" , x = display.contentWidth/7 * 3.5  , y = posicaoY*2 , fontSize = 15, align = "left" })
                    local hora,data = funcaoDate:formataData(todosCartoes[i].dataInicio)
                    local dataCompra = display.newText({text =data .. "\n"..hora  , x = display.contentWidth/7 *5.5 , y = posicaoY*2 , fontSize = 15, align = "left" })

                    caixa.placaText = placaText
                    caixa.dataText = dataText
                    caixa.dataCompra = dataCompra

                    placaText:setFillColor( 0, 0, 0 )
                    dataText:setFillColor(0,0,0)
                    dataCompra:setFillColor(0,0,0)
                    
                    
                    sceneGroup:insert(caixa)
                    sceneGroup:insert(placaText)
                    sceneGroup:insert(dataText)
                    sceneGroup:insert(dataCompra)

                    table.insert(tabelaCaixasCompras,caixa)
                    
                end
                
                posicaoY = posicaoY + 22  
            end
            end
        else
            caixaInfo = display.newRoundedRect(  display.contentWidth/2,  display.contentHeight/ 2, display.contentWidth, display.contentHeight/7, 9 )
            caixaInfo:setFillColor( 0.2, 0.2, 1, 1 )
            textoNaoPossuiCompras = display.newText({text = "Você não possui nenhuma compra!" , x = display.contentWidth/2  , y = display.contentHeight/2 , fontSize = 15, align = "center" })
            sceneGroup:insert(caixaInfo)
            sceneGroup:insert(textoNaoPossuiCompras)
        end

end

function onTouchProximo()
    for i = 8 , 1, -1 do
        if tabelaCaixasCompras[i]~=nil then
            display.remove(tabelaCaixasCompras[i])
            display.remove(tabelaCaixasCompras[i].placaText)
            display.remove(tabelaCaixasCompras[i].dataText)
            display.remove(tabelaCaixasCompras[i].dataCompra)
            table.remove(tabelaCaixasCompras,i)
        end
    end
        print(#tabelaCaixasCompras)
    if #tabelaCaixasCompras == 0 then
        iFor = iFor+8
        jFor = jFor+8
    
        mostraCompraCartoes(iFor,jFor)    

    end


end

function onTouchCompras(event)
    if event.phase == "began" then
        for i = 1 , #tabelaCaixasCompras do
            
            if tabelaCaixasCompras[i].cartao.numero == event.target.cartao.numero then
                local options = { params =  {cartao = tabelaCaixasCompras[i].cartao}}
                
                composer.gotoScene("TelaDetalhesCartaoCompras",options)
                break
            end
        end                       
    end
end

function scene:hide(event)
	if event.phase == "will" then
		
		display.remove(caixaInfo)
		display.remove(textoNaoPossuiCompras)
		
        for i = #tabelaCaixasCompras , 1, -1 do
            display.remove(tabelaCaixasCompras[i].placaText)
            display.remove(tabelaCaixasCompras[i].dataText)
            display.remove(tabelaCaixasCompras[i].dataCompra)
            display.remove(tabelaCaixasCompras[i])
            table.remove(tabelaCaixasCompras,i)
        end
        iFor = 1
        jFor = 8
	end

end


scene:addEventListener("create", scene)
scene:addEventListener("show", scene)
scene:addEventListener("hide", scene)
return scene