package jsonjava;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
//import com.mysql.cj.jdbc.Driver;

public class SqlToMultipleJsonFiles {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        //Dynamically load class
//        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection conn = null;
        conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "amritansh", "diwakar#7");
        Statement st = conn.createStatement();
        // fetching records from database
        ResultSet rs = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");

        ArrayList<CustomerDetailsPOJO> listCustomer = new ArrayList<>();

        //Storing them in an arraylist of POJO Objects
        while (rs.next()) {// rs.next => setting the pointer to next row
            CustomerDetailsPOJO customer = new CustomerDetailsPOJO();
            customer.setCourseName(rs.getString(1));
            customer.setPurchaseDate(rs.getString(2));
            customer.setAmount(rs.getInt(3));
            customer.setLocation(rs.getString(4));
            listCustomer.add(customer);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        for (int i=0; i < listCustomer.size(); i++){
            objectMapper.writeValue(new File("src\\main\\resources\\customerInfo_"+ i + ".json"), listCustomer.get(i));
        }
        conn.close();
    }
}
