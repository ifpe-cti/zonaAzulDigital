local composer = require ("composer")

local scene = composer.newScene()

local menu = require("bibliotecas.menu_slider")

local widget = require("widget")

local webServiceComunication = require("WebServiceComunication")

local menuMotorista
local sceneGroup

local credito

local tabelaCaixas = {}

local id

function scene:create(event)

	sceneGroup = self.view

    menuMotorista = menu:new({
        data={
            {text="Cartões Ativos", scene="TelaMotoristaInicial"},
            {text="Comprar cartão", scene="TelaCompraMotorista"},
            {text="Histórico de Compras",scene = "TelaRelatorioCompras"},
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

    local botaoAtualizar = widget.newButton(
        {
            width = 50,
            height = 40,
            x = display.contentWidth/7 * 6.4 ,
            y = display.contentHeight/7 ,
            defaultFile = "imagens/atualizarApp.png",
            onEvent = atualizaDados
        }
    )
    local rectSaldo = display.newRoundedRect(  display.contentWidth/7*1.8, display.contentHeight/7,display.contentWidth/3*1.3,  display.contentHeight/15, 9 )
    rectSaldo:setFillColor( 0.2, 0.2, 1, 1 )
    webServiceComunication:consultarCartoesAtivos(motoristaLogado)

    local textoCartoes = display.newText({text = "Cartões Ativos:",x = display.contentWidth/2,y = display.contentHeight/7* 1.5,fontSize = 20})
    local textoSaldo = display.newText({text = "Saldo:",x = display.contentWidth/7 * 1.1 ,y = display.contentHeight/7,fontSize = 20})
    
    credito= display.newText({text ="R$: ".. motoristaLogado.credito..",00",x = display.contentWidth/7 * 2.5 ,y = display.contentHeight/7+1,fontSize = 15})
    
    sceneGroup:insert(textoCartoes)
    sceneGroup:insert(botaoAtualizar)
    sceneGroup:insert(rectSaldo)
    sceneGroup:insert(textoSaldo)
    sceneGroup:insert(credito)
    
end

function scene:show( event )
    if event.phase == "will" then
        credito.text ="R$: ".. motoristaLogado.credito..",00"
        webServiceComunication:consultarCartoesAtivos(motoristaLogado)
        mostraCartoesAtivos()
    end
end


function mostraCartoesAtivos()
        local posicaoY = display.contentHeight/6.7
        local posicaoX = display.contentWidth/2
        
        if #cartoesAtivos > 0  then
            id = 1
            for i = 1 , #cartoesAtivos do
                
                if i%2 ~= 0 then
                    
                    local caixaRect = display.newRoundedRect(  posicaoX,  posicaoY* 2, display.contentWidth, display.contentHeight/12, 9 )
                    caixaRect.id = id
                    caixaRect.cartaoAtivo = cartoesAtivos[i]
                    caixaRect:setFillColor( 0.2, 0.2, 1, 1 )
                    caixaRect:addEventListener("touch",onTouch)
                    
                    local placa = display.newText({text = cartoesAtivos[i].placa.letras .. "-"..cartoesAtivos[i].placa.numeros , x = display.contentWidth/7 *1.2 , y = posicaoY*2 , fontSize = 15, align = "left" })
                    local textTempoRestante = display.newText({text = "Restante:" , x = display.contentWidth/7 * 3.5  , y = posicaoY*2  , fontSize = 15, align = "left" })
                    local tempoRestante = display.newText({text ="hr: ".. cartoesAtivos[i].tempoRestante  , x = display.contentWidth/7 *5.5 , y = posicaoY*2 , fontSize = 15, align = "left" })
                    
                    caixaRect.placa = placa
                    caixaRect.textTempoRestante = textTempoRestante
                    caixaRect.tempoRestante = tempoRestante

                    sceneGroup:insert(caixaRect)
                    sceneGroup:insert(placa)
                    sceneGroup:insert(textTempoRestante)
                    sceneGroup:insert(tempoRestante)

                    table.insert(tabelaCaixas,caixaRect)
                    
                else
                    local caixaRect = display.newRoundedRect(  posicaoX,  posicaoY* 2, display.contentWidth, display.contentHeight/12, 9 )
                    caixaRect.id = id
                    caixaRect.cartaoAtivo = cartoesAtivos[i]
                    caixaRect:setFillColor( 0.8, 0.8, 1 )
                    caixaRect:addEventListener("touch",onTouch)


                    local placa = display.newText({text = cartoesAtivos[i].placa.letras .. "-"..cartoesAtivos[i].placa.numeros , x = display.contentWidth/7 *1.2 , y = posicaoY*2 , fontSize = 15, align = "left" })
                    local textTempoRestante = display.newText({text = "Restante:" , x = display.contentWidth/7 * 3.5  , y = posicaoY*2 , fontSize = 15, align = "left" })
                    local tempoRestante = display.newText({text ="hr: ".. cartoesAtivos[i].tempoRestante  , x = display.contentWidth/7 *5.5 , y = posicaoY*2 , fontSize = 15, align = "left" })

                    caixaRect.placa = placa
                    caixaRect.textTempoRestante = textTempoRestante
                    caixaRect.tempoRestante = tempoRestante

                    placa:setFillColor( 0, 0, 0 )
                    textTempoRestante:setFillColor(0,0,0)
                    tempoRestante:setFillColor(0,0,0)
                    
                    
                    sceneGroup:insert(caixaRect)
                    sceneGroup:insert(placa)
                    sceneGroup:insert(textTempoRestante)
                    sceneGroup:insert(tempoRestante)

                    table.insert(tabelaCaixas,caixaRect)
                    
                end
                id = id + 1
                posicaoY = posicaoY + 22  
            end
        else
            caixaRectInfo = display.newRoundedRect(  display.contentWidth/2,  display.contentHeight/ 2, display.contentWidth, display.contentHeight/7, 9 )
            caixaRectInfo:setFillColor( 0.2, 0.2, 1, 1 )
            textoNaoPossuiCartoes = display.newText({text = "Você não possui cartões ativos!\n Aperte no botão atualizar!" , x = display.contentWidth/2  , y = display.contentHeight/2 , fontSize = 15, align = "center" })
            sceneGroup:insert(caixaRectInfo)
            sceneGroup:insert(textoNaoPossuiCartoes)
        end

end


function scene:hide(event)
    if event.phase == "will" then
        display.remove(textoNaoPossuiCartoes)
        display.remove(caixaRectInfo)

        for i = #tabelaCaixas , 1, -1 do
            display.remove(tabelaCaixas[i])
            display.remove(tabelaCaixas[i].placa)
            display.remove(tabelaCaixas[i].textTempoRestante)
            display.remove(tabelaCaixas[i].tempoRestante)
            table.remove(tabelaCaixas,i)
        end


    end
end

function atualizaDados(event)
    if event.phase == "began" then
        webServiceComunication:atualizarMotorista(motoristaLogado)
        webServiceComunication:consultarCartoesAtivos(motoristaLogado)
        webServiceComunication:consultarCompraTodosCartoes(motoristaLogado)
        composer.gotoScene("TelaMotoristaInicial")
    end
end

 function onTouch(event)
    if event.phase == "began" then
        for i = 1 , #tabelaCaixas do
            if tabelaCaixas[i].id == event.target.id then
                local options = { params =  {cartaoAtivo = tabelaCaixas[i].cartaoAtivo}}

                composer.gotoScene("TelaDetalhesCartao",options)
                break
            end
        end                       
    end
end


function destroyMenu(event)
    menuMotorista:destroy()
    motoristaLogado = nil
    composer.removeScene("TelaMotoristaInicial")
    composer.gotoScene("TelaLogin")
end

scene:addEventListener("create", scene)
scene:addEventListener("show", scene)
scene:addEventListener("hide", scene)

return scene