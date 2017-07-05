local json = require("json")

local webService = {}

local composer = require("composer")

local function eventoConsultarPlaca(event)
    if not event.isError then
        local response = json.decode(event.response)
        
        if event.status == 200 then
            print(event.response)
            local cartaoJson = json.decode(event.response)
            cartaoConsultado = cartaoJson

            composer.gotoScene("TelaGuarda")

        end
        print(event.response)
        print(event.status)
    else
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

	network.request("http://localhost:8084/TesteZonaAzul/rest/cartaozonaazul/buscar", "GET", eventoConsultarPlaca, params)

end

return webService