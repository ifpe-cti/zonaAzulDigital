local json = require("json")

local webService = {}

local toast = require("plugin.toast")

local composer = require("composer")

local endereco = "localhost"
local porta = "8084"

local function eventoConsultarPlaca(event)
    if not event.isError then
        local response = json.decode(event.response)
        
        if event.status == 200 then
            print(event.response)
                    
            cartaoConsultado =json.decode(event.response)

            composer.gotoScene("TelaGuarda")

        elseif event.status == 403 then

            toast.show("Placa Invalida", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.7}})  

        elseif event.status == 417 then

            toast.show("Placa não cadastrada no sistema!", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.7}})              

        else
            print(event.response)
            print(event.status)

        end
    else
        toast.show("Você não está conectado a internet", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.7}})              
        print("Erro")
    end
    return
end

function webService:consultarPlaca(letras, numeros)
    local headers = {}

    headers["Content-Type"] = "application/json"

    local placa = {}

    placa.letras = letras
    placa.numeros = numeros

	local placaJson = json.encode(placa)

	local params = {}
    params.headers = headers
	params.body = placaJson

	network.request("http://"..endereco..":"..porta.."/TesteZonaAzul/rest/cartaozonaazul/buscar", "GET", eventoConsultarPlaca, params)

end

return webService