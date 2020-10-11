package test.java.utils;

import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class HttpUtil {
    /*
    Parse json tutorial https://rest-assured.io/

     */
    static ReadProperty readProperty= ReadProperty.getInstance();

    public static Response readExcel(String query, String sheetName){
        return given()
                .multiPart(new File("/Users/bunty/Downloads/Automation/src/test/resources/testData/test.xlsx"))
                .formParam("query", query)
                .formParam("sheetname", sheetName)
                .when()
                .post(String.valueOf(readProperty.get("excelApi")))
                .then()
                .log()
                .all()
                .extract().response();
    }



}
