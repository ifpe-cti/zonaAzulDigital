local json = require("json")
local motorista



local function recuperarMotorista(event)
    
    if not event.isError then
        local response = json.decode(event.response)
        print(event.response)
    else
        print("Erro")
    end
    return

end

function cadastrar(motorista)
     self.motorista = motorista

local headers = {}
headers["motorista"] = json.encode(motorista)

local params = {}
params.headers = headers

network.request("https://reqres.in/api/users", "GET", recuperaMotorista, params)
end