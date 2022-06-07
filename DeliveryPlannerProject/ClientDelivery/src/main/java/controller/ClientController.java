package controller;

import model.Node;
import model.Warehouse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientController {
    public static List<String> getTowns() {

        List<String> towns=new ArrayList<>();
        HttpURLConnection conn = null;
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try{
            URL url = new URL("http://localhost:8082/warehouse");
            conn = (HttpURLConnection) url.openConnection();

            // Request setup
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
            conn.setReadTimeout(5000);

            // Test if the response from the server is successful
            int status = conn.getResponseCode();

            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                JSONArray objects = new JSONArray(responseContent.toString());
                for (int i = 0 ; i < objects.length(); i++) {
                    JSONObject object = objects.getJSONObject(i);
                    String town = object.getString("town");
                    towns.add(town);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return towns;
    }

    public static Map<Long, List<Node>> getSolution(Warehouse warehouse) {
        Map<Long, List<Node>> vehiclesRoutes = new HashMap<>();
        HttpURLConnection conn = null;
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try {
            URL url = new URL("http://localhost:8082/solution/" + warehouse.getId());
            conn = (HttpURLConnection) url.openConnection();

            // Request setup
            conn.setRequestMethod("GET");

            // Test if the response from the server is successful
            int status = conn.getResponseCode();

            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                JSONObject object = new JSONObject(responseContent.toString());

                for (long vehicleId = 0; vehicleId < object.length(); vehicleId++){
                    List<Node> vehicleRoute = new ArrayList<>();
                    JSONArray nodes = object.getJSONArray(String.valueOf(vehicleId));
                    for (int nodeIndex = 0 ; nodeIndex < nodes.length(); nodeIndex++) {
                        JSONObject jsonNode = nodes.getJSONObject(nodeIndex);
                        Node node = new Node(jsonNode.getLong("id"),
                                jsonNode.getDouble("latitude"),
                                jsonNode.getDouble("longitude"),
                                jsonNode.getInt("capacity")
                        );
                        vehicleRoute.add(node);
                    }
                    vehiclesRoutes.put(vehicleId, vehicleRoute);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return vehiclesRoutes;
    }

    public static Warehouse getWarehouse(String townName) {

        Warehouse warehouse = null;
        HttpURLConnection conn = null;
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try{
            URL url = new URL("http://localhost:8082/warehouse/" + townName);
            conn = (HttpURLConnection) url.openConnection();

            // Request setup
            conn.setRequestMethod("GET");

            // Test if the response from the server is successful
            int status = conn.getResponseCode();

            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                JSONObject object = new JSONObject(responseContent.toString());
                warehouse = new Warehouse(object.getLong("id"),
                        object.getString("town"),
                        object.getDouble("latitude"),
                        object.getDouble("longitude"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return warehouse;
    }

    public static List<Double> getLimits(Warehouse warehouse) {

        List<Double> limits = new ArrayList<>();
        HttpURLConnection conn = null;
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();
        try{
            URL url = new URL("http://localhost:8082/solution/limits/" + warehouse.getId());
            conn = (HttpURLConnection) url.openConnection();

            // Request setup
            conn.setRequestMethod("GET");


            // Test if the response from the server is successful
            int status = conn.getResponseCode();

            if (status >= 300) {
                reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            else {
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                JSONObject jsonResponse = new JSONObject(responseContent.toString());
                JSONArray object = jsonResponse.getJSONArray("result");
                limits.add(object.getDouble(0));
                limits.add(object.getDouble(1));
                limits.add(object.getDouble(2));
                limits.add(object.getDouble(3));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return limits;
    }
}
