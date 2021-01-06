package asr.proyectoFinal.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
public class Traductor
{
	public static String translate(String palabra, String sourceModel, String destModel, boolean conversational)
	{
		String model;
		if(sourceModel.equals("en") || sourceModel.equals("es") || destModel.equals("en") || destModel.equals("es"))
		{
			model=sourceModel+"-"+destModel;
			if(conversational)
			model+="-conversational";
		}
		else
			model="en-es";
		
		//Authenticator authenticator = new IamAuthenticator("sGBqIGkLecdsa4RdsA3imHp_lvb7MMlZNzdasq-PgkmCXdsf59P0");
		Authenticator authenticator = new IamAuthenticator("c0ANS_pKy7_4KUj52L5PyCa0glj5faJXSqaE59zW3fuV");
		LanguageTranslator languageTranslator = new LanguageTranslator("2020-11-11", authenticator);
		languageTranslator.setServiceUrl("https://api.us-south.language-translator.watson.cloud.ibm.com/instances/a52169e5-ce90-4722-a79a-967e0ba34898");
		
		TranslateOptions translateOptions = new TranslateOptions.Builder()
			.addText(palabra)
			.modelId(model)
			.build();
		
		TranslationResult translationResult = languageTranslator.translate(translateOptions).execute().getResult();
		
		System.out.println(translationResult);
		
		String traduccionJSON = translationResult.toString();
		JsonParser aux = new JsonParser();
		JsonObject rootObj = (JsonObject) aux.parse(traduccionJSON);
		JsonArray traducciones = rootObj.getAsJsonArray("translations");
		String traduccionPrimera = palabra;
		if(traducciones.size()>0)
			traduccionPrimera =	traducciones.get(0).getAsJsonObject().get("translation").getAsString();
		
		return traduccionPrimera;
	}
}

			