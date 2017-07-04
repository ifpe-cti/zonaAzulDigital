local class = require("bibliotecas.30log")

local motorista = class(
    "motorista",
    nome,
    cpf, 
    senha
)

function motorista:init(nome, cpf, senha)
    self.nome = nome
    self.cpf = cpf
    self.senha = senha
end


return motorista





