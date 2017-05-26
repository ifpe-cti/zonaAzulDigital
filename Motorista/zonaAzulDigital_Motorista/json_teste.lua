local json = require("json")

local mime = require("mime")

local function handleResponse(event)
    
    motorista = event.params.motorista

    if not event.isError then
        local response = json.decode(event.response)
        print(event.response)
    else
        print("Erro")
    end
    return
end

local headers = {}
headers["motorista"] = "motorista.nome:motorista.senha"