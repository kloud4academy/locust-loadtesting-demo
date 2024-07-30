package loadtesingdemo.locustdemo;
import static io.restassured.RestAssured.*;
import com.github.myzhan.locust4j.AbstractTask;
import com.github.myzhan.locust4j.Locust;

import io.restassured.response.ValidatableResponse;

public class OpenApplicationTask extends AbstractTask {

	private int weight;

    @Override
    public int getWeight() {
        return weight;
    }


    @Override
    public String getName() {
        return "Open application task";
    }

    public OpenApplicationTask(int weight){
        this.weight = weight;
    }

    @Override
    public void execute() {
        try {
        	ValidatableResponse response = given().param("store","0123").when().
           // get("http://localhost:8081/home").then().statusCode(200);
        	get("http://localhost:8082/nt-ms/fetchProductsUsingCategory/Featured").then().statusCode(200);
            Locust.getInstance().recordSuccess("http", getName(), response.using().extract().time(), 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
