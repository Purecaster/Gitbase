import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class tracking {

	@SuppressWarnings("unchecked")
	public static String trackpackage(String invoicenumber){
		
		//API ключ для работы с Новой Почтой
		String api_key="";
		//Номер посылки	
		//String invoicenumber="59000197201343";
		String resultStatus="";
		    		
	//Работаем с JSON

	    	//Основной объект JSON
	    	JSONObject resultJson = new JSONObject();
	    	//Вспомогательный объект JSON для массива внутри основного объекта
	    	JSONObject resultJson2 = new JSONObject();
	    	JSONObject resultJson3 = new JSONObject();
	    	//Массив Documents
	    	JSONArray docs = new JSONArray();
	    	 
	    	resultJson.put("apiKey",api_key);
	    	resultJson.put("modelName","TrackingDocument");
	    	resultJson.put("calledMethod","getStatusDocuments");
	    	resultJson.put("Language","UA");    	
	    	resultJson3.put("Documents","UA");    	
	    	resultJson2.put("DocumentNumber",invoicenumber);
	    	resultJson2.put("Phone","3800000");
	    	docs.add(resultJson2);    	
	    	resultJson3.put("Documents",docs);
	    	resultJson.put("methodProperties", resultJson3);    	    	
	    	//System.out.print("Сформированный запрос: "+resultJson.toString());  	
	 	    	
	 // Формируем HTTP-запрос
	    	
	       	 HttpClient httpclient = HttpClients.createDefault();

	         try
	         {
	        	 URIBuilder builder = new URIBuilder("http://testapi.novaposhta.ua/v2.0/en/documentsTracking/json/");
	             URI uri = builder.build();
	             HttpPost request = new HttpPost(uri);
	             request.setHeader("Content-Type", "application/json");
	             // Request body
	             StringEntity reqEntity = new StringEntity(resultJson.toString());
	             request.setEntity(reqEntity);
	             HttpResponse response = httpclient.execute(request);
	             HttpEntity entity = response.getEntity();
	             if (entity != null) 
	             {         
	                  //	 System.out.println("Код ответа : "
	                   //      + response.getStatusLine().getStatusCode());	            	
	            	 //System.out.println("");
	            	 String resultJson21 = EntityUtils.toString(entity);            
	            	 
	                //Проверяем, если ответ сервера 429, то вываливаемся, иначе идем дальше (должен быть 200)
	            	 
	            	 if (response.getStatusLine().getStatusCode()==429){
	                  resultStatus="Сервер не отвечает. Попытайтесь позже";
	            	 }
	                 else{
	                	  
	               	//Парсим полученный результат 	                	  
	                try{	             
	            	JSONObject outPutJsonObject = (JSONObject) JSONValue.parseWithException(resultJson21);	                
	                //Вытаскиваем массив "data" из полученного результата  
	                JSONArray outPutJsonArray = (JSONArray)outPutJsonObject.get("data");
	                JSONObject outPutJsonData = (JSONObject) outPutJsonArray.get(0);
	                resultStatus="Статус посылки: " + outPutJsonData.get("Status")+" "+outPutJsonData.get("RecipientDateTime");

	                }
	                catch (org.json.simple.parser.ParseException e) {
	                    e.printStackTrace();	  
	                		}
	             }
	             
	             }
	         }
	         
	            	 catch (Exception e){
	             System.out.println(e.getMessage());	             
	             resultStatus="Fail";
	             }
             return resultStatus;
	     }

		
	}