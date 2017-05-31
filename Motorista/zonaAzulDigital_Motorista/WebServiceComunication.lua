local json = require("json")

local webService = {}



local function eventoCadastrarMotorista(event)
    
    if not event.isError then
        local response = json.decode(event.response)
        print(event.response)
        print(event.status)

    else
        print("Erro") 
    end
    return

end

function webService:cadastrarMotorista(motorista)

	local headers = {}

	headers["Content-Type"] = "application/json"

	local motoristaJson = json.encode(motorista)

	local params = {}

	params.headers = headers

	params.body = motoristaJson

	network.request("http://localhost:8084/TesteZonaAzul/rest/motorista/salvar", "POST", eventoCadastrarMotorista, params)

end


function webService:recuperarMotorista()


	local motoristaJson = json.encode(motorista)

	local params = {}

	params.body = motoristaJson

	network.request("http://localhost:8084/TesteZonaAzul/rest/motorista/recuperar", "GET", printEvent,params)

end







return webService