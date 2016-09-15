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
		
		//API ���� ��� ������ � ����� ������
		String api_key="";
		//����� �������	
		//String invoicenumber="59000197201343";
		String resultStatus="";
		    		
	//�������� � JSON

	    	//�������� ������ JSON
	    	JSONObject resultJson = new JSONObject();
	    	//��������������� ������ JSON ��� ������� ������ ��������� �������
	    	JSONObject resultJson2 = new JSONObject();
	    	JSONObject resultJson3 = new JSONObject();
	    	//������ Documents
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
	    	//System.out.print("�������������� ������: "+resultJson.toString());  	
	 	    	
	 // ��������� HTTP-������
	    	
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
	                  //	 System.out.println("��� ������ : "
	                   //      + response.getStatusLine().getStatusCode());	            	
	            	 //System.out.println("");
	            	 String resultJson21 = EntityUtils.toString(entity);            
	            	 
	                //���������, ���� ����� ������� 429, �� ������������, ����� ���� ������ (������ ���� 200)
	            	 
	            	 if (response.getStatusLine().getStatusCode()==429){
	                  resultStatus="������ �� ��������. ����������� �����";
	            	 }
	                 else{
	                	  
	               	//������ ���������� ��������� 	                	  
	                try{	             
	            	JSONObject outPutJsonObject = (JSONObject) JSONValue.parseWithException(resultJson21);	                
	                //����������� ������ "data" �� ����������� ����������  
	                JSONArray outPutJsonArray = (JSONArray)outPutJsonObject.get("data");
	                JSONObject outPutJsonData = (JSONObject) outPutJsonArray.get(0);
	                resultStatus="������ �������: " + outPutJsonData.get("Status")+" "+outPutJsonData.get("RecipientDateTime");

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