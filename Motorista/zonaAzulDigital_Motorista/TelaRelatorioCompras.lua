local composer = require("composer")

local scene = composer.newScene()

local widget = require("widget")

local webServiceComunication = require("WebServiceComunication")

local funcaoDate = require("funcaoDate")

local sceneGroup 

local caixaInfo

local textoNaoPossuiCompras

local tabelaCaixasCompras = {}



function scene:create(  )
	sceneGroup = self.view
	local textoCartoes = display.newText({text = "Todas as Compras:",x = display.contentWidth/2,y = display.contentHeight/8*1.3,fontSize = 20})
	--local cadastro = widget.newButton({label = "Hello World",labelColor = { default={ 0.2, 0.2, 1, 1 }, over={0, 0, 0} },fontSize = 18 ,  textOnly = true, x = display.contentWidth/2, y = display.contentHeight/ 2.6 * 2, width = display.contentWidth/1.5, height = display.contentHeight/12, shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 0 }, over={ 0.8, 0.8, 1 } } })
	--cadastro:addEventListener("touch", relatorio)
	sceneGroup:insert(textoCartoes)
end

function scene:show(event)

	if event.phase == "will" then
		webServiceComunication:consultarCompraTodosCartoes(motoristaLogado)
		mostraCompraCartoes()

	end
end

function relatorio(event) 
	
	if event.phase == "began" then
		webServiceComunication:consultarCompraTodosCartoes(motoristaLogado)

	end

end



function mostraCompraCartoes()
     local posicaoY = display.contentHeight/8
        local posicaoX = display.contentWidth/2
        
        if #todosCartoes > 0  then
            if #todosCartoes>=8 then
                local proximo = widget.newButton({label = "Próximo", labelColor = { default={ 1, 1, 1 }, over={0, 0, 0} }, fontSize = 14 , x = display.contentWidth/2, y = display.contentHeight/10 * 9.79, width = display.contentWidth/2, height = display.contentHeight/17, shape = "roundedRect", fillColor = { default={ 0.2, 0.2, 1, 1 }, over={ 0.8, 0.8, 1} } })
            end

            id = 1
            for i = 1 , #todosCartoes do
                
                if i%2 ~= 0 then
                    
                    local caixa = display.newRoundedRect(  posicaoX,  posicaoY* 2, display.contentWidth, display.contentHeight/12, 9 )
                    caixa.id = id
                    caixa.cartao = todosCartoes[i]
                    
                    caixa:setFillColor( 0.2, 0.2, 1, 1 )
                    caixa:addEventListener("touch",onTouchCompras)
                    
                    local placaText = display.newText({text = todosCartoes[i].placa.letras .. "-"..todosCartoes[i].placa.numeros , x = display.contentWidth/7 *1.2 , y = posicaoY*2 , fontSize = 15, align = "left" })
                    local dataText = display.newText({text = "Data:" , x = display.contentWidth/7 * 3.5  , y = posicaoY*2  , fontSize = 15, align = "left" })
                    local hora,data = funcaoDate:formataData(todosCartoes[i].dataInicio)
                    local dataCompra = display.newText({text =data .. "\n"..hora  , x = display.contentWidth/7 *5.5 , y = posicaoY*2 , fontSize = 15, align = "left" })
                    
                    sceneGroup:insert(caixa)
                    sceneGroup:insert(placaText)
                    sceneGroup:insert(dataText)
                    sceneGroup:insert(dataCompra)

                    table.insert(tabelaCaixasCompras,caixa)
                    print(tabelaCaixasCompras[i].cartao.numero)
                else
                    local caixa = display.newRoundedRect(  posicaoX,  posicaoY* 2, display.contentWidth, display.contentHeight/12, 9 )
                    caixa.id = id
                    caixa.cartao = todosCartoes[i]
                    caixa:setFillColor( 0.8, 0.8, 1 )
                    caixa:addEventListener("touch",onTouchCompras)


                    local placaText = display.newText({text = todosCartoes[i].placa.letras .. "-"..todosCartoes[i].placa.numeros , x = display.contentWidth/7 *1.2 , y = posicaoY*2 , fontSize = 15, align = "left" })
                    local dataText = display.newText({text = "Data:" , x = display.contentWidth/7 * 3.5  , y = posicaoY*2 , fontSize = 15, align = "left" })
                    local hora,data = funcaoDate:formataData(todosCartoes[i].dataInicio)
                    local dataCompra = display.newText({text =data .. "\n"..hora  , x = display.contentWidth/7 *5.5 , y = posicaoY*2 , fontSize = 15, align = "left" })

                    
                    placaText:setFillColor( 0, 0, 0 )
                    dataText:setFillColor(0,0,0)
                    dataCompra:setFillColor(0,0,0)
                    
                    
                    sceneGroup:insert(caixa)
                    sceneGroup:insert(placaText)
                    sceneGroup:insert(dataText)
                    sceneGroup:insert(dataCompra)

                    table.insert(tabelaCaixasCompras,caixa)
                    
                end
                id = id + 1
                posicaoY = posicaoY + 22  
            end
        else
            caixaInfo = display.newRoundedRect(  display.contentWidth/2,  display.contentHeight/ 2, display.contentWidth, display.contentHeight/7, 9 )
            caixaInfo:setFillColor( 0.2, 0.2, 1, 1 )
            textoNaoPossuiCompras = display.newText({text = "Você não possui nenhuma compra!" , x = display.contentWidth/2  , y = display.contentHeight/2 , fontSize = 15, align = "center" })
            sceneGroup:insert(caixaInfo)
            sceneGroup:insert(textoNaoPossuiCompras)
        end

end


function onTouchCompras(event)
    if event.phase == "began" then
        for i = 1 , #tabelaCaixasCompras do
            
            if tabelaCaixasCompras[i].id == event.target.id then
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
            display.remove(tabelaCaixasCompras[i])
            table.remove(tabelaCaixasCompras,i)
        end

	end

end


scene:addEventListener("create", scene)
scene:addEventListener("show", scene)
scene:addEventListener("hide", scene)
return scene