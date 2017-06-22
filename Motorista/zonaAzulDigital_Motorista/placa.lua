local class = require("Bibliotecas.30log")

local placa = class(
    "placa",
    letras,
    numeros 
)


function placa:init(letras, numeros)
    self.letras = letras
    self.numeros = numeros
end


return placa
