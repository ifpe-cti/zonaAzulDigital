local composer = require ("composer")

local scene = composer.newScene()

local menu = require("bibliotecas.menu_slider")

local widget = require("widget")

local webServiceComunication = require("WebServiceComunication")

local menuMotorista
local sceneGroup

function scene:create(event)

	sceneGroup = self.view
    

    menuMotorista = menu:new({
        data={
            {text="Cartões Ativos", scene="TelaMotoristaInicial"},
            {text="Comprar cartão", scene="TelaCompraMotorista"},
            {text="Histórico de Compras"},
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



    local textoCartoes = display.newText({text = "Cartões Ativos:",x = display.contentWidth/7 * 2,y = display.contentHeight/7,fontSize = 20})
    
    sceneGroup:insert(textoCartoes)
    sceneGroup:insert(botaoAtualizar)
    
end

function scene:show( event )
    if event.phase == "will" then

        local posicaoY = display.contentHeight/7
        local posicaoX = display.contentWidth/2
        
        if #cartoesAtivos > 0  then

            for i = 1 , #cartoesAtivos do
                if i%2 ~= 0 then
                    local caixaRect = display.newRoundedRect(  posicaoX,  posicaoY* 2, display.contentWidth, display.contentHeight/7, 9 )

                    local textPlaca = display.newText({text = "Placa:" , x = display.contentWidth/7  , y = posicaoY*2 - 10 , fontSize = 15, align = "left" })
                    local placa = display.newText({text = cartoesAtivos[i].placa.letras .. "-"..cartoesAtivos[i].placa.numeros , x = display.contentWidth/7 *1 , y = posicaoY*2 + 10 , fontSize = 15, align = "left" })
                    local textTempoRestante = display.newText({text = "Tempo Restante:" , x = display.contentWidth/7 * 3.5  , y = posicaoY*2 - 10 , fontSize = 15, align = "left" })
                    local tempoRestante = display.newText({text ="hr: ".. cartoesAtivos[i].tempoRestante  , x = display.contentWidth/7 *3.5 , y = posicaoY*2 + 10 , fontSize = 15, align = "left" })


                    caixaRect:setFillColor( 0.2, 0.2, 1, 1 )
                    sceneGroup:insert(caixaRect)
                    sceneGroup:insert(textPlaca)
                    sceneGroup:insert(placa)
                    sceneGroup:insert(textTempoRestante)
                    sceneGroup:insert(tempoRestante)
                else
                    local caixaRect = display.newRoundedRect(  posicaoX,  posicaoY* 2, display.contentWidth, display.contentHeight/7, 9 )

                    local textPlaca = display.newText({text = "Placa:" , x = display.contentWidth/7  , y = posicaoY*2 - 10 , fontSize = 15, align = "left" })
                    local placa = display.newText({text = cartoesAtivos[i].placa.letras .. "-"..cartoesAtivos[i].placa.numeros , x = display.contentWidth/7 *1 , y = posicaoY*2 + 10 , fontSize = 15, align = "left" })
                    local textTempoRestante = display.newText({text = "Tempo Restante:" , x = display.contentWidth/7 * 3.5  , y = posicaoY*2 - 10 , fontSize = 15, align = "left" })
                    local tempoRestante = display.newText({text ="hr: ".. cartoesAtivos[i].tempoRestante  , x = display.contentWidth/7 *3.5 , y = posicaoY*2 + 10 , fontSize = 15, align = "left" })

                    textPlaca:setFillColor( 0, 0, 0 )
                    placa:setFillColor( 0, 0, 0 )
                    textTempoRestante:setFillColor(0,0,0)
                    tempoRestante:setFillColor(0,0,0)
                    caixaRect:setFillColor( 0.8, 0.8, 1 )
                    
                    sceneGroup:insert(caixaRect)
                    sceneGroup:insert(textPlaca)
                    sceneGroup:insert(placa)
                    sceneGroup:insert(textTempoRestante)
                    sceneGroup:insert(tempoRestante)

                end
                posicaoY = posicaoY + 40 
            end
        end

    end
end


function atualizaDados(event)
    if event.phase == "began" then

        webServiceComunication:atualizarMotorista(motoristaLogado)
        webServiceComunication:consultarCartoesAtivos(motoristaLogado)

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

return scene