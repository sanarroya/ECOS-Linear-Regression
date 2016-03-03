import Controller.Controller;
import Model.CalculationResult;
import Model.LoadData;
import Model.ValuePair;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import java.net.URI;
import java.net.URISyntaxException;

import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;

import com.heroku.sdk.jdbc.DatabaseUrl;
import java.util.LinkedList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    
    port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

    get("/calculateValues", (req, res) -> {
        
        final String FILE_NAME_1 = "dataset1.txt";
        final String FILE_NAME_2 = "dataset2.txt";
        final String FILE_NAME_3 = "dataset3.txt";
        final String FILE_NAME_4 = "dataset4.txt";
        final String[] FILE_NAMES = {FILE_NAME_1, FILE_NAME_2, FILE_NAME_3, FILE_NAME_4};
        List<ValuePair> data;
        Controller controller = new Controller();
        CalculationResult result = new CalculationResult();
        String dataString = "<p><br>";
        int count = 1;
        for(String fileName : FILE_NAMES) {
            data = controller.loadDataList(fileName);
            result = controller.calculateValues(data);
            dataString += String.format("Test %d<br>", count);
            dataString += String.format("<p>B0: %.5g%n<br>B1: %.5g%n<br>r: %.4g%n<br>r^2: %.4g%n<br>Yk: %.4g%n<br></p>", result.getRegressionB0(), result.getRegressionB1(), result.getCorrelationR(), result.getCorrelationSquareR(), result.getYK());
            count++;
        }
        return dataString;
    });

    get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

    get("/db", (req, res) -> {
      Connection connection = null;
      Map<String, Object> attributes = new HashMap<>();
      try {
        connection = DatabaseUrl.extract().getConnection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
          output.add( "Read from DB: " + rs.getTimestamp("tick"));
        }

        attributes.put("results", output);
        return new ModelAndView(attributes, "db.ftl");
      } catch (Exception e) {
        attributes.put("message", "There was an error: " + e);
        return new ModelAndView(attributes, "error.ftl");
      } finally {
        if (connection != null) try{connection.close();} catch(SQLException e){}
      }
    }, new FreeMarkerEngine());

  }

}
