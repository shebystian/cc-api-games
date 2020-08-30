@tag
Feature: Prueba Despliegue de Lista de juegos

	@tag1
	Scenario: Validar que el sistema entregue lista de juegos
	
		Given Quiero ver la lista juegos disponibles
		
		When Consulto la lista de juegos
		
		Then Valida Respuesta3
