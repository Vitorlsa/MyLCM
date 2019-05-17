package com.example.mylcm;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    String ip = "";
    String jdbc = "net.sourceforge.jtds.jdbc.Driver";
    String db = "";
    String username = "";
    String password = "";

    public Connection CONN(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        String ConnURL = null;

        try{

            Class.forName(jdbc);
            ConnURL = "jdbc:jdts:sqlserver://" + ip + ";" + "databasename=" + db + ":user=" + username + ":password=" + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException se){
            Log.e("ERRO", se.getMessage());
        } catch (ClassNotFoundException e){
            Log.e("ERRO", e.getMessage());
        } catch (Exception e){
            Log.e("ERRO", e.getMessage());
        }
        return conn;
    }
}
