package com.martheseladvier.interstellartravel;
import java.net.URI;
import java.util.*;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

@Service
public class QueryDatabase implements IQueryDatabase, Serializable{
    DynamoDbClient dynamo;
    Map<String, String> dictionary = new HashMap<>();

    @Autowired
    public QueryDatabase()throws Exception{
        try {
            setUp();
            makeDictionary();
        }
        catch (Exception e){
            System.out.println(e.toString());
            throw e;
        }
    }

    public void setUp() throws Exception{
        try {

        dynamo = DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:8000"))
                .region(Region.EU_WEST_1)
                .credentialsProvider(() -> AwsBasicCredentials.create("dummy", "dummy"))
                .build();
        }
        catch (Exception e){
            System.out.println(e.toString());
            throw e;
        }
    }

    public void close(){
         dynamo.close();
    }
    private void makeDictionary() throws Exception{
        try{
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
        catch (Exception e){
        System.out.println(e.toString());
        throw e;
        }
    }

    public Accelerator getAccelerator(String id) throws Exception{
        try {
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
            if(accId == null || accName == null){
                throw new Exception("Database access error");
            }

            return accelerator;

        }
        catch (Exception e){
        System.out.println(e.toString());
        throw e;
         }
    }

    public List<Accelerator> getAllAccelerators() throws Exception{
        try {
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

            if(accId == null || accName == null){
                throw new Exception("Database access error");
            }

            allAccelerators.add(accelerator);

        }

        return allAccelerators;

        }
        catch (Exception e){
            System.out.println(e.toString());
            throw e;
        }
    }
}
