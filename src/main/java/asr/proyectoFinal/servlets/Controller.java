package asr.proyectoFinal.servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asr.proyectoFinal.dao.CloudantPalabraStore;
import asr.proyectoFinal.dominio.Palabra;
import asr.proyectoFinal.services.Traductor;
import asr.proyectoFinal.services.ReproduceAudio;
import asr.proyectoFinal.services.TextSpeech;



/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/listar", "/insertar", "/hablar","/reproduce"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset=\"UTF-8\"></head><body>");
		
		CloudantPalabraStore store = new CloudantPalabraStore();
		System.out.println(request.getServletPath());
		switch(request.getServletPath())
		{
			case "/listar":
				if(store.getDB() == null)
					  out.println("No hay DB");
				else
					out.println("Palabras en la BD Cloudant:<br />" + store.getAll());
				
				out.println("<br>");
				out.println("<A href=\"/asrTomcatEjemploCloudant/index.jsp\">Volver atras...</A>");

				break;
				
				
			case "/hablar":
				String parametro1 = request.getParameter("palabra");
				String traduccion=Traductor.translate(parametro1, "es", "en", false);
				//String checkbox = request.getParameter("pr");
				if(request.getParameter("pr")!=null) {
					TextSpeech.TexttoVoice(traduccion);
				}
				out.println("Traduccion :" + traduccion  );
				out.println("<A href=\"/asrTomcatEjemploCloudant/index.jsp\">Volver atras...</A>");
				break;
				
			case "/reproduce":
				ReproduceAudio.Reproduce();
				out.println("Reproduce bien");
				out.println("<br>");
				out.println("<A href=\"/asrTomcatEjemploCloudant/index.jsp\">Volver atras...</A>");
				break;
				
				
			case "/insertar":
				Palabra palabra = new Palabra();
				String parametro = request.getParameter("palabra");

				if(parametro==null)
				{
					out.println("usage: /insertar?palabra=palabra_a_traducir");
				}
				else
				{
					if(store.getDB() == null) 
					{
						out.println(String.format("Palabra: %s", palabra));
					}
					else
					{
						palabra.setName(Traductor.translate(parametro, "es", "en", false));
						store.persist(palabra);
					    out.println(String.format("Almacenada la palabra: %s", palabra.getName()));			    	  
					}
				}
				break;
		}
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
