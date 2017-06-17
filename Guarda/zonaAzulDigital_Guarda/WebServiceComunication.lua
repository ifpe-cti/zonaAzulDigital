local json = require("json")

local webService = {}

local composer = require("composer")

local function eventoConsultarPlaca(event)
    if not event.isError then
        local response = json.decode(event.response)
        print(event.response)
    else
        print("Erro")
    end
    return
end

function webService:consultarPlaca(placa)
    
   

	local placaJson = json.encode(placa)

	local params = {}

	params.body = placaJson

	network.request("http://localhost:8084/TesteZonaAzul/rest/guarda/buscar", "GET", eventoConsultarPlaca, params)

end

return webService