package entregable_3.Modelo; 
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject; // Necesita agregar la librería org.json para trabajar con JSON
public class ConsultarPrecioCripto {
   private static final String URL_API = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum,usd-coin,tether,dogecoin&vs_currencies=usd";
   public static void main(String[] args) {
       HttpClient cliente = HttpClient.newHttpClient();
       HttpRequest solicitud = HttpRequest.newBuilder()
    		   .uri(URI.create(URL_API))
               .GET()
               .build();
       try {
           HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
           if (respuesta.statusCode() == 200) {
               parsearYMostrarPrecios(respuesta.body());
           } else {
               System.out.println("Error: " + respuesta.statusCode());
           }
       } catch (IOException | InterruptedException e) {
           e.printStackTrace();
       }
   }
   private static void parsearYMostrarPrecios(String cuerpoRespuesta) {
       JSONObject json = new JSONObject(cuerpoRespuesta);
       System.out.println("Precios de Criptomonedas (en USD):");
       double precioBTC = json.getJSONObject("bitcoin").getDouble("usd");
       System.out.println("BTC: $" + precioBTC);
       double precioETH = json.getJSONObject("ethereum").getDouble("usd");
       System.out.println("ETH: $" + precioETH);
       double precioUSDC = json.getJSONObject("usd-coin").getDouble("usd");
       System.out.println("USDC: $" + precioUSDC);
       double precioUSDT = json.getJSONObject("tether").getDouble("usd");
       System.out.println("USDT: $" + precioUSDT);
       double precioDOGE = json.getJSONObject("dogecoin").getDouble("usd");
       System.out.println("DOGE: $" + precioDOGE);
   }
   
   /**
    * Método estático para obtener los datos de precios de las criptomonedas desde la API.
    * 
    * @return String con el cuerpo de la respuesta JSON, o null si ocurre un error.
    */
   public static String obtenerDatosCripto() {
       HttpClient cliente = HttpClient.newHttpClient();
       HttpRequest solicitud = HttpRequest.newBuilder()
               .uri(URI.create(URL_API))
               .GET()
               .build();
       try {
           HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
           if (respuesta.statusCode() == 200) {
               return respuesta.body(); // Devuelve el JSON como String
           } else {
               System.out.println("Error al obtener datos: " + respuesta.statusCode());
               return null;
           }
       } catch (IOException | InterruptedException e) {
           e.printStackTrace();
           return null;
       }
   }
}
   