package jsonjava;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class SqlToSingleJsonFile {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        //Dynamically load class
//        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = null;
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "amritansh", "diwakar#7");
        Statement st = conn.createStatement();
        // fetching records from database
        ResultSet rs = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");

        Gson gson = new Gson();
        JSONArray jsonArray = new JSONArray();
        String jsonStringSingleRecord = null;

        //Storing them in an jsonArray of JSON Objects
        while (rs.next()) {// rs.next => setting the pointer to next row
            CustomerDetailsPOJO customer = new CustomerDetailsPOJO();
            customer.setCourseName(rs.getString(1));
            customer.setPurchaseDate(rs.getString(2));
            customer.setAmount(rs.getInt(3));
            customer.setLocation(rs.getString(4));

            jsonStringSingleRecord = gson.toJson(customer);
            jsonArray.add(jsonStringSingleRecord);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",jsonArray);

        gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonStringAllRecords = gson.toJson(jsonObject);

        jsonStringAllRecords =  StringEscapeUtils.unescapeJava(jsonStringAllRecords);
        jsonStringAllRecords = jsonStringAllRecords.replace("\"{","{").replace("}\"","}");

        try (FileWriter file = new FileWriter("src\\main\\resources\\customerInfo.json")) {
            file.write(jsonStringAllRecords);
        }
        conn.close();
    }
}
