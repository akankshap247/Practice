package com.wissen.practice;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.json.JSONObject;

public class FetchExchangeRate {

    public static void main(String[] args) {
        String[] sources = {
            "https://api.exchangerate-api.com/Exchange1", 
            "https://api.exchangerate-api.com/Exchange2", 
            "https://api.exchangerate-api.com/Exchange3", 
            "https://api.exchangerate-api.com/Exchange4", 
            "https://api.exchangerate-api.com/Exchange5"  
        };

        HttpClient client = HttpClient.newHttpClient();
        CompletableFuture<JSONObject>[] futures = new CompletableFuture[sources.length];
        
        for (int i = 0; i < sources.length; i++) {
            final int index = i;
            futures[i] = CompletableFuture.supplyAsync(() -> {
                try {
                    HttpRequest request = HttpRequest.newBuilder()
                        .uri(java.net.URI.create(sources[index]))
                        .build();
             //       HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
         /*      String     jsonResponse  = "{"
                            + "\"base\": \"USD\","
                            + "\"date\": \"2024-10-30\","
                            + "\"rates\": {"
                            + "    \"EUR\": 0.85,"
                            + "    \"GBP\": 0.75,"
                            + "    \"INR\": 110.53,"
                            + "    \"AUD\": 1.35,"
                            + "    \"CAD\": 1.25"
                            + "}"
                            + "}";

                        */
                   JSONObject jsonResponse =    getDummyJsonResponse();
                    return  jsonResponse;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            });
        }
       CompletableFuture<Void> allOf = CompletableFuture.allOf(futures);
        
        try {
        	 allOf.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
 
        JSONObject aggregatedResults = new JSONObject();
        for (CompletableFuture<JSONObject> future : futures) {
        	JSONObject jsonResponse = future.join(); 
        	if (jsonResponse != null) {
            	  String sourceCurrency = jsonResponse.getJSONObject("Exchange").getString("base"); 
                double exchangeRate = jsonResponse.getJSONObject("Exchange").getJSONObject("rates").getDouble("CAD"); 
                aggregatedResults.put(sourceCurrency, exchangeRate);
            }
        }

        System.out.println("Aggregated Rates: " + aggregatedResults.toString());
    }
    
  public static JSONObject  getDummyJsonResponse(){
	  
      JSONObject output = new JSONObject();
	  
      for (int i =0; i<5;i++) {
          JSONObject jsonResponse = new JSONObject();
          jsonResponse.put("base", "USD"); 
          jsonResponse.put("date", "2023-10-30"); 
         
          JSONObject rates = new JSONObject();
          rates.put("CAD", getRandomExchangeRate());
          rates.put("EUR", getRandomExchangeRate());
          rates.put("INR", getRandomExchangeRate());
          rates.put("JPY", getRandomExchangeRate());
          rates.put("AUD", getRandomExchangeRate());
          
          jsonResponse.put("rates", rates);
          
          output.put("Exchange",jsonResponse);
            }
      return output;
  }
        
  private static double getRandomExchangeRate() {
    return 0.5 + Math.random() * 1.5; 
  }
}
