package cl.kranio.cc.stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.List;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cl.kranio.cc.config.ApplicationProperties;
import cl.kranio.cc.model.GameRequest;
import cl.kranio.cc.model.GameResponse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.model.TestFailureException;

public class GameSteps {
	
	Logger logger = LoggerFactory.getLogger(GameListSteps.class);
	
	ApplicationProperties properties = new ApplicationProperties();
	
	private static Response response;
	private RequestSpecification request;
	private ObjectMapper mapper;
	GameRequest game = new GameRequest();
	GameResponse gameResponse = new GameResponse();
	
	
	@Given("^Necesito obtener mas informacion de un juego$")
	public void Necesito_obtener_mas_informacion_de_un_juego() throws Throwable {
		logger.info("Comienza la prueba automatizada$");
	}
	
	@When("^Tengo como dato el nombre$")
	public void tengo_como_dato_el_nombre(List<String> datos) throws Throwable {
		logger.info("Cuento con la lista de caracteristicas de juegos");
		try {
			game.setName(datos.get(1));
			
			logger.info("Objeto game:" + game);
		} catch (Exception ex) {
			Assert.fail("Exception: " + ex.getMessage());
			ex.printStackTrace();
			logger.error(ex.toString());
		}
	}
	
	@Then("^Consulto por la información del juego$")
	public void consulto_por_la_información_del_juego() throws Throwable {
		logger.info("Consulto información del juego");
		mapper = new ObjectMapper();
		try {
			
			String urlBase = properties.getProperties().getProperty("api.urlbase");
			String endpoint = "/game/"+game.getName();
			//String gameJSON= mapper.writeValueAsString(game); // transformo el objeto a json, para enviarlo
			
			request = given().header("Content-Type", "application/json"); // indico el tipo de contenido
			response = request.when()
					.get(urlBase + endpoint);
			
			logger.info("Api responde a prueba correctamente.");
		}
		catch (Exception ex) {
			Assert.fail("Exception: " + ex.getMessage());
			ex.printStackTrace();
			logger.error(ex.toString());
		}
	}
	
	@And("^Valida Respuesta$")
	public void valida_respuesta() throws Throwable {
		mapper = new ObjectMapper();
		Gson gson = new Gson();
		
		try {
			Type listType = new TypeToken<GameResponse>() {}.getType();
			@SuppressWarnings("rawtypes")
			ResponseBody body = response.getBody();
			gameResponse =  gson.fromJson(body.asString(), listType); // transformo respuesta a objeto para realizar las validaciones
			
			logger.info("StatusCode: " + response.getStatusCode());
			
			if(response.getStatusCode()!=200)
				throw new TestFailureException("StatusCode: " + response.getStatusCode() + " Not Found");
			
			logger.info("Clase Retornada Game: " + mapper.writeValueAsString(gameResponse));
		}
		catch (Exception ex) {
			Assert.fail("Exception: " + ex.getMessage());
			ex.printStackTrace();
			logger.error(ex.toString());
		}

		logger.info("Valida Respuesta");
	}
	
	//------------------------------------prueba 2 -----------------------------------
	
	@Given("^Quiero obtener mas informacion de un juego2$")
	public void Quiero_ver_la_lista_juegos_disponibles() throws Throwable {
		logger.info("Comienza la prueba automatizada$");
	}
	
	@When("^Tengo como dato la categoria$")
	public void cuento_con_la_lista_de_caracteristicas_de_juegos(List<String> datos) throws Throwable {
		logger.info("Cuento con la lista de caracteristicas de juegos");
		try {
			game.setCategory(datos.get(1));
			
			logger.info("Objeto game:" + game);
		} catch (Exception ex) {
			Assert.fail("Exception: " + ex.getMessage());
			ex.printStackTrace();
			logger.error(ex.toString());
		}
		}
	
	@Then("^Consulto información del juego2$")
	public void consulto_la_lista_de_juegos() throws Throwable {
		logger.info("Consulto información del juego");
		mapper = new ObjectMapper();
		try {
			request = null;
			response = null;
			String urlBase = properties.getProperties().getProperty("api.urlbase");
			String endpoint = "/game/category/"+game.getCategory();
			//String gameJSON= mapper.writeValueAsString(game); // transformo el objeto a json, para enviarlo
			
			request = given().header("Content-Type", "application/json"); // indico el tipo de contenido
			response = request.when()
					.get(urlBase + endpoint);
			
			logger.info("Api responde a prueba correctamente.");
		}
		catch (Exception ex) {
			Assert.fail("Exception: " + ex.getMessage());
			ex.printStackTrace();
			logger.error(ex.toString());
		}
	}
	
	@And("^Valida Respuesta2$")
	public void valida_respuesta2() throws Throwable {
		mapper = new ObjectMapper();
		Gson gson = new Gson();
		
		try {
			Type listType = new TypeToken<GameResponse>() {}.getType();
			@SuppressWarnings("rawtypes")
			ResponseBody body = response.getBody();
			gameResponse =  gson.fromJson(body.asString(), listType); // transformo respuesta a objeto para realizar las validaciones
			
			logger.info("StatusCode: " + response.getStatusCode());
			if(response.getStatusCode()!=200)
				throw new TestFailureException("StatusCode: " + response.getStatusCode() + " Not Found");
			
			logger.info("Clase Retornada Game: " + mapper.writeValueAsString(gameResponse));
		}
		catch (Exception ex) {
			Assert.fail("Exception: " + ex.getMessage());
			ex.printStackTrace();
			logger.error(ex.toString());
		}

		logger.info("Valida Respuesta");
	}
}
