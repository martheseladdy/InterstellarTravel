package com.martheseladvier.interstellartravel;
import java.net.URI;
import java.util.*;
import java.io.Serializable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

@Service
public class QueryDatabase implements IQueryDatabase, Serializable{
    DynamoDbClient dynamo;
    Map<String, String> dictionary = new HashMap<>();

    public QueryDatabase(){
        setUp();
        makeDictionary();
       // dynamo.close();
    }

    public void setUp(){

        try {

            String scriptPath = "/create-local-db.sh";
            Process process = Runtime.getRuntime().exec("bash " + scriptPath);

            int exitCode = process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }


        DynamoDbClient dynamo = DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:8000"))
                .build();
        makeDictionary();

    }

    public void close(){
         dynamo.close();
    }
    private void makeDictionary(){
        //iterate through all records and add the id with the corresponding name as a key value pair into the dictionary
        ScanRequest req = ScanRequest.builder()
                .tableName("accelerator")
                .build();


        ScanResponse resp = dynamo.scan(req);

        String id = "";
        String name = "";
        for (Map<String, AttributeValue> item : resp.items()) {
            id = item.get("id").s();
            name = item.get("name").s();
            dictionary.put(id, name);

        }

    }

    public Accelerator getAccelerator(String id){
        String accName = "";
        String accId = "";
        List<Connection> connections;


        GetItemRequest req = GetItemRequest.builder()
                .tableName("accelerator")
                .key(Collections.singletonMap("id", AttributeValue.builder().s(id).build()))
                .build();
        GetItemResponse resp = dynamo.getItem(req);

        Map<String, AttributeValue> itemMap = resp.item();
        System.out.println(resp.item()); //to remove

        accId = itemMap.get("id").s();

        accName = itemMap.get("name").s();

        List<AttributeValue> connList = itemMap.get("connections").l();

        List<Connection> connectionsList = new ArrayList<>();


        for (AttributeValue conn : connList) {

            Map<String, AttributeValue> connectionMap = conn.m();

            AttributeValue connectionIdValue = connectionMap.get("id");
            String connectionId = connectionIdValue.s();

            AttributeValue huValue = connectionMap.get("hu");
            int distance = Integer.valueOf(huValue.n());

            Connection connection = new Connection(connectionId, dictionary.get(connectionId.toUpperCase()), distance);
            connectionsList.add(connection);
        }


        Accelerator accelerator = new Accelerator(accId, accName, connectionsList);

        return accelerator;
    }

    public List<Accelerator> getAllAccelerators(){
        ScanRequest req = ScanRequest.builder()
                .tableName("accelerator")
                .build();


        ScanResponse resp = dynamo.scan(req);

        List<Accelerator> allAccelerators = new ArrayList<>();

        for (Map<String, AttributeValue> item : resp.items()) {
            List<Connection> connectionsList = new ArrayList<Connection>();
            String accId = "";
            String accName = "";

            accId = item.get("id").s();
            accName = item.get("name").s();
            List<AttributeValue> connections = item.get("connections").l();

            for (AttributeValue conn : connections) {

                Map<String, AttributeValue> connectionMap = conn.m();

                String connectionId = connectionMap.get("id").s();

                AttributeValue huValue = connectionMap.get("hu");
                int distance = Integer.valueOf(huValue.n());

                Connection connection = new Connection(connectionId, dictionary.get(connectionId.toUpperCase()), distance);
                connectionsList.add(connection);
            }

            Accelerator accelerator = new Accelerator(accId, accName, connectionsList);
            allAccelerators.add(accelerator);

        }


        return allAccelerators;
    }
}