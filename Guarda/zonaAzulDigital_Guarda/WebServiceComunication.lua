local json = require("json")

local webService = {}

local composer = require("composer")

function webService:consultarPlaca(placa)
    
   

	local placaJson = json.encode(placa)

	local params = {}

	params.body = placaJson

	network.request("http://localhost:8084/TesteZonaAzul/rest/guarda/buscar", "GET", printEvent,params)
    
end

return webService