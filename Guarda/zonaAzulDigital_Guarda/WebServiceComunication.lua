local json = require("json")

local webService = {}

local composer = require("composer")

local function eventoConsultarPlaca(event)
    if not event.isError then
        local response = json.decode(event.response)
        
        if event.status == 200 then

            local cartaoJson = json.decode(event.response)
            cartaoConsultado = cartaoJson

        end
        print(event.response)
    else
        print("Erro")
    end
    return
end

function webService:consultarPlaca(letras, numeros)
    
    local placa = {}

    placa.letras = letras
    placa.numeros = numeros

	local placaJson = json.encode(placa)

	local params = {}

	params.body = placaJson

	network.request("http://localhost:8084/TesteZonaAzul/rest/cartaozonaazul/consultar", "GET", eventoConsultarPlaca, params)

end

return webService