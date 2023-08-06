package com.martheseladvier.interstellartravel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class QueryDatabaseTest {

    @Test
    public void testGetAccelerator() throws Exception {

        //returrn, change this either to integration test or implement embedded in-memory H2 database endpoint
        QueryDatabase database = Mockito.mock(QueryDatabase.class); //create mock database

        List<Connection> expectedConnections = new ArrayList<Connection>(Arrays.asList(new Connection("PRX", "Proxima", 90),new Connection("RAN", "Ran", 100),new Connection("SIR", "Sirius", 100), new Connection("ARC", "Arcturus", 200),new Connection("ALD", "Aldermain", 250))); //populate this
        when(database.getAccelerator("SOL")).thenReturn(new Accelerator("SOL", "Sol", expectedConnections));
        Accelerator accelerator = database.getAccelerator("SOL");

        assertAll("getAccelerator returns accelerator ID, name, and connections",
                () -> assertEquals(accelerator.id, "SOL"),
                () -> assertEquals(accelerator.name, "Sol"),
                () -> assertEquals(accelerator.connections, expectedConnections)
                );
        verify(database).getAccelerator("SOL");
    }

    @Test
    public void testGetAllAccelerators() throws Exception {
        QueryDatabase database = Mockito.mock(QueryDatabase.class); //create mock database

        List<Connection> connectionSol = new ArrayList<>(Arrays.asList(
                new Connection("RAN", "Ran", 100),
                new Connection("PRX", "Proxima", 90),
                new Connection("SIR", "Sirius", 100),
                new Connection("ARC", "Arrcturus", 200),
                new Connection("ALD", "Aldermain", 250)
        ));

        Accelerator acceleratorSol = new Accelerator("SOL", "Sol", connectionSol);


        List<Connection> connectionAls = new ArrayList<>(Arrays.asList(
                new Connection("ALT", "Altair", 1),
                new Connection("ALD", "Aldermain", 1)
        ));

        Accelerator acceleratorAls = new Accelerator("ALS", "Alshain", connectionAls);

        List<Accelerator> allAccelerators =  database.getAllAccelerators();
        for(Accelerator accelerator : allAccelerators){
            if (accelerator.getId().equalsIgnoreCase("SOL")){
                assertEquals(accelerator, acceleratorSol);
            }
            else if (accelerator.getId().equalsIgnoreCase("ALS")){
                assertEquals(accelerator, acceleratorAls);
            }
        }

    }
}
