

--[[
    
    TODO:

        ** Colocar acesso com ícone' para abrir e fechar o menu no TOP container (5)
        ** Permitir que o usuário personalize o top container (5)
        ** Colocar um Header no left container
    DONE:

        ** Menu slides from right to left
        ** Permite que o usuário informe os dados da listagem no menu (5)
]]

local widget = require("widget")
local composer = require("composer")

menu = {
    
    
}

function menu:hide()
    transition.moveTo(self.menuLeftGroup, {time=300, x=-self.menuLeftGroup.width, 
    onComplete=function(event )
        
        if self.menuLeftGroup.isVisible == true then
            self.menuLeftGroup.isVisible = false
        else
            self.menuLeftGroup.isVisible = true
        end
    end})
    self.menuLeftGroup:toBack()
end


function menu:show()

    self.menuLeftGroup:toFront()
    self.menuLeftGroup.isVisible = true
    transition.moveTo(self.menuLeftGroup, {time=300, x=0})
    
end

function menu:handleVisualization()
    if self.menuLeftGroup.isVisible == false or self.menuLeftGroup.isFocus == true then
        menu:show()
    else   
        menu:hide()
    end
end

-- Used to move menu according to user's touch 
function menu:move(newPosition)

    self.menuLeftGroup.x = self.menuLeftGroup.x-(self.menuLeftGroup.x0-newPosition)
         
    -- if menu is almost closed, then close it automatically
    if self.menuLeftGroup.x <= (-self.menuLeftContainer.width*0.8) then -- TODO change 100 to a proportional value based on container width
        
        self:hide()
    end
end

function menu:createContainers(options)
    self.leftContainerProperties = {
        width = display.contentWidth*0.7,
        height = display.contentHeight
    }

    self.containers = display.newGroup()
    self:createTopContainer(options.topContainerProperties)
    self:createLeftContainer()
    self.containers:insert(self.menuTopGroup)
    self.containers:insert(self.menuLeftGroup)

end

function menu:createTopContainer(options)

    self.menuTopGroup = display.newGroup()
    self.menuTopGroup.anchorX = 0.0
    self.menuTopGroup.anchorY = 0.0

    if options.fileName == nil then -- its not a image, so render a rect
        self.menuTopContainer = display.newRect(0,0, options.width, options.height) 
        
        self.menuTopContainer:setFillColor(unpack(options.bgColor))
        self.menuTopContainer.text = display.newText({text=options.text, x=self.menuTopContainer.width*0.5, y=self.menuTopContainer.height*0.5, font=native.systemFontBold, fontSize=20})
    else
        if options.imageHeight == nil or options.imageWidth == nil then
            error("If using image you must specify imageHeight and imageWidth")
        else
            self.menuTopContainer = display.newImageRect(options.fileName, options.imageWidth, options.imageHeight)
        end
    end

    self.menuTopContainer.anchorX = 0
    self.menuTopContainer.anchorY = 0
    self.menuTopGroup:insert(self.menuTopContainer)

    local xBase = display.contentWidth*0.05
    local yBase = 15
    local strokeWidth = 3
    local triggerWidth = 30
    
    local dashOne = display.newLine(xBase, yBase, xBase+triggerWidth, yBase) 
    local dashTwo = display.newLine(xBase, yBase+10, xBase+triggerWidth, yBase+10)
    local dashThree = display.newLine(xBase, yBase+20, xBase+triggerWidth, yBase+20)
    
    dashOne.strokeWidth = 3
    dashTwo.strokeWidth = 3
    dashThree.strokeWidth = 3

    dashOne:setStrokeColor(unpack(options.strokeColor))
    dashTwo:setStrokeColor(unpack(options.strokeColor))
    dashThree:setStrokeColor(unpack(options.strokeColor))
    
    self.menuTopContainer.trigger = display.newGroup()
    self.menuTopContainer.trigger:insert(dashOne)
    self.menuTopContainer.trigger:insert(dashTwo)
    self.menuTopContainer.trigger:insert(dashThree)

    self.menuTopGroup:insert(self.menuTopContainer.trigger)
    -- its just a transparent rect to wrap the dashs and receive touch events
    local triggerRect = display.newRect(xBase, yBase, triggerWidth, 25)
    triggerRect:setFillColor(0,0,0,0.01) -- cannot use alpha 0 because it will not get touch events Corona Bug?!
    triggerRect.anchorX = 0
    triggerRect.anchorY = 0

    self.menuTopContainer.trigger:insert(triggerRect)
    self.menuTopContainer.trigger.touch = function( self, event )
        if ( event.phase == "began" ) then
            event.target.menuRef:handleVisualization()
        end
    end
    
end


function menu:createLeftContainer()
    -- TODO: deve ter uma forma de ficar visível sobre todos os outros elementos da tela

    -- A group will contain the left container and the data listview
    self.menuLeftGroup = display.newGroup()
    
    -- Inicialmente estará escondida
    self.menuLeftGroup.isVisible = false

    -- Deve ter uma área retângular, da altura da tela e de largura de 70% da tela
    self.menuLeftContainer = display.newRect(0,0, self.leftContainerProperties.width, self.leftContainerProperties.height) 
    self.menuLeftContainer.anchorX = 0.0
    self.menuLeftContainer.anchorY = 0.0
    
    -- make it be touch enabled
    self.menuLeftGroup:addEventListener("touch", 
    
        function(event) 
            menu:handleMovement(event)

        end
    )
    
    -- Update the group position with left container position
    self.menuLeftGroup:insert(self.menuLeftContainer)
    self.menuLeftGroup.anchorX = 0.0
    self.menuLeftGroup.anchorY = 0.0
    self.menuLeftGroup.x = -self.menuLeftContainer.width -- to make it slide to right

end


function menu:handleMovement(event)
    print("touch")
    print(event.phase)
    if event.phase == "began" then
        
        self.menuLeftGroup.x0 = event.x
        self.menuLeftGroup.y0 = event.y
        self.menuLeftGroup.isFocus = true
        
    elseif self.menuLeftGroup.isFocus == true then
        print("movimentandooo")
        if event.phase == "moved" then
            print("movendo")
            if self.menuLeftGroup.x > 0 then
                self.menuLeftGroup.x = 0
            end
            -- se está aberto, não pode mover para a direita, apenas esquerda
            if self.menuLeftGroup.isVisible == true then
                if self.menuLeftGroup.x >= 0 then
                    if event.x <= self.menuLeftGroup.x0 then -- is moving to left
                        self:move(event.x)
                    end
                else -- can move to left or right
                    self:move(event.x)
                end
                --[[

                    OLD implementation. Can erase if everything goes ok
                if event.x >= self.menuLeftGroup.x0 then
                    print("não pode, está aberto'")
                else
                    print("ai pode hein")
                    self.menuLeftGroup.x = self.menuLeftGroup.x-(self.menuLeftGroup.x0-event.x)

                    if self.menuLeftGroup.x <= (-self.menuLeftContainer.width*0.8) then -- TODO change 100 to a proportional value based on container width
                        
                        self:hide()
                    end
                end

                ]]
            else
            end
            self.menuLeftGroup.x0 = event.x
            
        elseif event.phase == "ended" or event.phase == "canceled" then
            -- user ended with touch but the menu wasnt completely closed
            if self.menuLeftGroup.x <= (-self.menuLeftContainer.width*0.4) then -- TODO change 100 to a proportional value based on container width
                self:hide()
            else -- it was not enough to close, lets open again
                self:show()
            end
            self.menuLeftGroup.isFocus = false
        end
    end
end




function menu:createDataTable(data)

    self.data = data
    
    
    local standardHeight = 25 -- this number was based on: 20 is the default height number for words using native font, 5 is to create more space
    local numberOfElements = 0
    for k, v in pairs(data) do
        numberOfElements = numberOfElements + 1
    end

    local datatableHeight = standardHeight*numberOfElements

    self.dataTable = widget.newTableView {
       noLines = true,
       height=datatableHeight,
       left=0,
       top=10,
       width=self.menuLeftContainer.width,
        onRowRender = onRowRender,
        onRowTouch = onRowTouch
    }
   
    
    self:setData()
    self.menuLeftGroup:insert(self.dataTable)
end


function menu:setData()

    local totalHeight = 0
    local rowHeight = 30
    local rowWidth = 200
    
    

    for i = 1, #self.data do
        
        if self.data[i].text == nil or self.data[i].scene == nil then
            error("Data for list must be a table with text and scene attributes")
        end

        totalHeight = totalHeight + rowHeight

        self.dataTable:insertRow{
            rowHeight = rowHeight,
            rowWidth= rowWidth,
            rowColor = { 1, 1, 1 },
            lineColor = { 0.90, 0.90, 0.90 },
            params = { 
                text = self.data[i].text,
                scene = self.data[i].scene
            }
        }
    end

    
    self.dataTable.width = rowWidth
    self.dataTable.height = totalHeight 
end

function onRowRender( event )
    
    local row = event.row
    local id = row.index

    row.text = display.newText( row, row.params.text, 0, 0 )
    row.text:setFillColor(0.5)
    row.scene = row.params.scene
    row.text.anchorX = 0
    row.text.anchorY = 0
    --row.text:setFillColor( 0, 0, 1 )
    row.text.x = 30
    row.text.y = 0

    
end

function menu:new(options)

    options = options or {}
    options.data = options.data or {}
    options.containers = options.containers or {}
    options.containers.topContainerProperties = options.containers.topContainerProperties or {}
    options.containers.topContainerProperties.width = options.containers.topContainerProperties.width or display.contentWidth
    options.containers.topContainerProperties.height = options.containers.topContainerProperties.height or 50
    options.containers.topContainerProperties.bgColor = options.containers.topContainerProperties.bgColor or {}
    options.containers.topContainerProperties.fileName = options.containers.topContainerProperties.fileName or nil
    options.containers.topContainerProperties.imageWidth = options.containers.topContainerProperties.imageWidth or nil
    options.containers.topContainerProperties.imageHeight = options.containers.topContainerProperties.imageHeight or nil
    options.containers.topContainerProperties.strokeColor = options.containers.topContainerProperties.strokeColor or {}
    options.containers.topContainerProperties.text = options.containers.topContainerProperties.text or ""

    self:createContainers(options.containers)
    self:createDataTable(options.data)
    
    self.menuTopContainer.trigger.menuRef = self
    self.menuTopContainer.trigger:addEventListener("touch")
    
    
    
    return self
end

function menu:insertDataRow()

end



function onRowTouch( event )
    
    

    if event.row.scene ~= nil then
        composer.gotoScene(event.row.scene)
        menu:hide()
    else -- this touch was not made on a list item

    end
end




-- Deve ter uma área retangular, no topo da tela, que contém um botão que abre e fecha o menu
--menu.menuTopContainer = display.newRect()

return menu