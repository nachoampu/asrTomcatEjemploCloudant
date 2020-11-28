package asr.proyectoFinal.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;

import asr.proyectoFinal.services.ReproduceAudio;



public class TextSpeech {

	public static void TexttoVoice( String palabra) {
		
		IamAuthenticator authenticator = new IamAuthenticator("JqD0aHwN49peFistPoYIJWoe7AHFwPBeJl0EEFZXkc_D");
		TextToSpeech textToSpeech = new TextToSpeech(authenticator);
		textToSpeech.setServiceUrl("https://api.eu-gb.text-to-speech.watson.cloud.ibm.com/instances/98f24db6-ffe4-4148-be52-334d534f1bf2");

		try {
		  SynthesizeOptions synthesizeOptions =
		    new SynthesizeOptions.Builder()
		      .text(palabra)
		      .accept("audio/wav")
		      .voice("en-US_AllisonV3Voice")
		      .build();

		  InputStream inputStream =
		    textToSpeech.synthesize(synthesizeOptions).execute().getResult();
		  InputStream in = WaveUtils.reWriteWaveHeader(inputStream);

		  ReproduceAudio.ReproduceArchivo(in);
		  
		  OutputStream out = new FileOutputStream("hello_world.wav");
		  byte[] buffer = new byte[1024];
		  int length;
		  while ((length = in.read(buffer)) > 0) {
		    out.write(buffer, 0, length);
		  }
		    
		  out.close();
		  in.close();
		  inputStream.close();
		} catch (IOException e) {
		  e.printStackTrace();
		}
				
	}
}
