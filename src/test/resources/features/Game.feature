@tag
Feature: Prueba Despliegue de informacion de juego

	@tag1
	Scenario: Validar que el sistema entregue la informacion del juego por el nombre
	
		Given Necesito obtener mas informacion de un juego
		
		When Tengo como dato el nombre
		#Lista de datos
				| Datos  								| 
		    |	God of War	      		| #Nombre
					
		Then Consulto por la información del juego
		
		And Valida Respuesta
		
	@tag2
	Scenario: Validar que el sistema entregue la informacion del juego por la categoria
	
		Given Quiero obtener mas informacion de un juego2
		
		When Tengo como dato la categoria
		#Datatable
					#Lista de datos
				| Datos  					| 
		    |	zz     	| #Categoria
					
		Then Consulto información del juego2
		
		And Valida Respuesta2