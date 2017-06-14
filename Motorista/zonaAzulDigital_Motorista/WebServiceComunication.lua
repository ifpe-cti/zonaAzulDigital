local json = require("json")

local webService = {}

local composer = require("composer")

local toast = require("plugin.toast")


local function eventoCadastrarMotorista(event)
    
    if not event.isError then
        local response = json.decode(event.response)
        print(event.response)
        print(event.status)

		if event.status == 200 then
			composer.gotoScene("TelaLogin")
			toast.show("Cadastro realizado com sucesso!!", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.8}})  
		end

    else
        print("Erro") 
    end
    return

end

local function eventoLogarMotorista(event)
	if not event.isError then
		
		if event.status == 200 then
			
			composer.gotoScene("TelaMotoristaInicial")
			
		elseif event.status == 401 then
			
			toast.show("Não foi possivel fazer login, CPF ou senha inválidos!", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.8}})  

		end
		
	else
		toast.show("Se conecte a internet para fazer login!", {duration = 'short', gravity = 'TopCenter', offset = {0, display.contentHeight/10 *9.8}})  
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

function webService:logarMotorista(cpf,senha)

	local login = {}

	login.cpf = cpf
	login.senha = senha

	local headers = {}

	local headers = {}

	headers["Content-Type"] = "application/json"

	local motoristaLogin = json.encode(login)

	local params = {}
	
	params.headers = headers

	params.body = motoristaLogin

	network.request("http://localhost:8084/TesteZonaAzul/rest/motorista/login", "POST", eventoLogarMotorista, params)

end

return webService