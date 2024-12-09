package entregable_3.Modelo;

import org.json.JSONObject;

public class CotizacionModelo {

    public static String obtenerCotizaciones() {
        // Obtiene los datos en formato String desde la API
        String respuesta = ConsultarPrecioCripto.obtenerDatosCripto();
        if (respuesta != null) {
            // Procesa la respuesta para extraer los precios
            return procesarDatosCripto(respuesta);
        } else {
            return null;
        }
    }

    private static String procesarDatosCripto(String cuerpoRespuesta) {
        // Parsear el JSON de la respuesta
        JSONObject json = new JSONObject(cuerpoRespuesta);
        
        StringBuilder sb = new StringBuilder();
        sb.append("Precios de Criptomonedas (en USD):\n");
        
        // Extraer y mostrar los precios de las criptomonedas
        sb.append("BTC: $").append(json.getJSONObject("bitcoin").getDouble("usd")).append("\n");
        sb.append("ETH: $").append(json.getJSONObject("ethereum").getDouble("usd")).append("\n");
        sb.append("USDC: $").append(json.getJSONObject("usd-coin").getDouble("usd")).append("\n");
        sb.append("USDT: $").append(json.getJSONObject("tether").getDouble("usd")).append("\n");
        sb.append("DOGE: $").append(json.getJSONObject("dogecoin").getDouble("usd")).append("\n");

        return sb.toString();
    }
}
