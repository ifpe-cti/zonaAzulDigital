local funcaoDate = {}

function funcaoDate:formataData(data)
	tabelaMeses = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec" }

	--Mês
	--==================================================================
	local indexInicial = data.find(data, " ",4)
	
	local mes = data.sub( data, 1 , indexInicial-1 )
	--==================================================================
	
	--Dia
	--==================================================================
	local restoString = data.sub( data, indexInicial+1)

	local indexDia = data.find(restoString, ", ")
	
	local dia = data.sub(restoString,1,indexDia-1 )
	--==================================================================
	
	--Ano
	--==================================================================
	local restoStringAno = data.sub( restoString, indexDia+2)
	
	local indexAno = data.find(restoStringAno, " ")

	local ano = data.sub(restoStringAno,1,indexAno-1 )
	--==================================================================

	--Horas
	--==================================================================	
	local restoStringHoras = data.sub( restoString, indexAno+4)
	
	local hora = restoStringHoras

	--==================================================================

	
	for i = 1, #tabelaMeses do
		if tabelaMeses[i] == mes  then			
			mes = i
			break
		end
	end

	local novaData = dia.."/"..mes.."/"..ano

	local novaHora = hora

	return novaHora,novaData

end


return funcaoDate