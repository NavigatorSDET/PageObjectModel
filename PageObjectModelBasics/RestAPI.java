package appmanager;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static appmanager.ExtentCucumberFormatter.testStepException;

/*import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;*/
//import com.jayway.jsonpath.Configuration;
//import com.jayway.jsonpath.DocumentContext;
//import com.jayway.jsonpath.JsonPath;
//import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
//import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;


public class RestAPI {

    RestAPI() {
    }


    public static HashMap<String, String> Fetechedvalue = new HashMap<String, String>();

    private RequestSpecification httpRequest;
    private JSONObject requestParams;
    private String jsonBody;

    public RestAPI(String baseUri, String strUserName, String strPassWord) {
        try {
            RestAssured.baseURI = baseUri;
            httpRequest = RestAssured.given().auth().basic(strUserName, strPassWord);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public RestAPI(String baseUri, String accessToken) {
        try {
            RestAssured.baseURI = baseUri;
            httpRequest = RestAssured.given().baseUri(baseUri);
            httpRequest.header("Content-Type", "application/json");
            httpRequest.header("PHX-Authorization", "Bearer "+accessToken);
            //.formParam("PHX-Authorization", "Bearer "+accessToken);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public String getResponseString(String get) {
        Response response = httpRequest.get(get);
        String str = response.body().asString();
        return str;
    }

    public String getResponseString() {
        Response response = httpRequest.get();
        String str = response.body().asString();
        return str;
    }


    public Response getResponse() {
        Response response = httpRequest.get();
        return response;
    }

    public Response getResponse(String get) {
        Response response = httpRequest.get(get);
        return response;
    }

    public void subtractDays(int days) throws ParseException {
        String date = "06-08-2019";
        GregorianCalendar cal = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(date));


    }

    public Response PostRequest(String post) {
        try {
            //String json = "{\"customerId\": \"AAAAAAAAAA\",\"accounts\": [{\"accountNumber\": \"111111111111111\",\"accountName\": \"\"}],\"baiCodes\": [{\"baiCode\": \"\",\"baiDesc\": \"\"}],\"fromDate\":\"2019-09-13\",\"toDate\": \"2019-09-13\"}";
            if(requestParams!=null) {
                httpRequest.body(requestParams.toString());
            }
            Response response = httpRequest.post(post);
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    public Response PostRequestTransactionSearch(String post) {
        try {
            httpRequest.body(jsonBody);
            Response response = httpRequest.post(post);
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    public Response PostRequestSpecialReport(String post) {
        try {
            Response response = httpRequest.post(post);
            return response;
        } catch (Exception e) {
            return null;
        }
    }


    public static int getStatusCode(Response response) {
        try {
            return response.getStatusCode();
        } catch (Exception e) {
            return -1;
        }
    }


    public static String getResponseHeader(Response response) {
        try {
            return response.getHeaders().toString();
        } catch (Exception e) {
            return null;
        }
    }


    public static String getResponseBody(Response response) {
        try {
            return response.getBody().asString();
        } catch (Exception e) {
            return null;
        }
    }


    public static String getResponseValue(Response response, String jsonpath) {
        try {
            return response.jsonPath().getString(jsonpath);
        } catch (Exception e) {
            return null;
        }
    }


    public void createRequestBody(String custId, String balanceType, String toDate, String days) {
        try{
            requestParams = new JSONObject();
            requestParams.put("customerId", custId);
            requestParams.put("balanceType", balanceType);
            requestParams.put("toDate", toDate);
            requestParams.put("days", days);
        }catch(Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }
    }


    public void createRequestBodyTransactionSearch(String customerId, String accountNumber, String accountName, String baiCode, String baiDesc, String fromDate, String toDate)  {
        try{
            jsonBody = "{\"customerId\": \""+customerId+"\",\"accounts\": [{\"accountNumber\": \""+accountNumber+"\",\"accountName\": \""+accountName+"\"}],\"baiCodes\": [{\"baiCode\": \""+baiCode+"\",\"baiDesc\": \""+baiDesc+"\"}],\"fromDate\":\""+fromDate+"\",\"toDate\": \""+toDate+"\"}";
        } catch (Exception e) {
            testStepException(new Exception().getStackTrace()[0].getMethodName(), e);
        }

    }

}
