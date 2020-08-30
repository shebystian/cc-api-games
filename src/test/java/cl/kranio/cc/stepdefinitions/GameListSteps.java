package cl.kranio.cc.stepdefinitions;

import static io.restassured.RestAssured.given;

import java.lang.reflect.Type;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cl.kranio.cc.config.ApplicationProperties;
import cl.kranio.cc.model.GameResponse;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.model.TestFailureException;

public class GameListSteps {
	
	Logger logger = LoggerFactory.getLogger(GameListSteps.class);
	
	ApplicationProperties properties = new ApplicationProperties();

	private static Response response;
	private RequestSpecification request;
	private ObjectMapper mapper;
	private GameResponse gameResponse;
	
	
	@Given("^Quiero ver la lista juegos disponibles$")
	public void Quiero_ver_la_lista_juegos_disponibles() throws Throwable {
		logger.info("Comienza la prueba automatizada$");
	}
	
	@When("^Consulto la lista de juegos$")
	public void consulto_la_lista_de_juegos() throws Throwable {
		mapper = new ObjectMapper();
		try {
			
			String urlBase = properties.getProperties().getProperty("api.urlbase");
			String endpoint = "/game";
			
			request = given().header("Content-Type", "application/json"); // indico el tipo de contenido
			response = request
					.get(urlBase + endpoint);
			
			logger.info("Api responde a prueba correctamente.");
		}
		catch (Exception ex) {
			Assert.fail("Exception: " + ex.getMessage());
			ex.printStackTrace();
			logger.error(ex.toString());
		}
	}
	
	@Then("^Valida Respuesta3$")
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
			
			logger.info("Clase Retornada OfertaResponse: " + mapper.writeValueAsString(gameResponse));
		}
		catch (TestFailureException ex) {
			Assert.fail("Exception: " + ex.getMessage());
			ex.printStackTrace();
			logger.error(ex.toString());
		}

		logger.info("Valida Respuesta");
	}
}
