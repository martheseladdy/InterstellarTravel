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
    public void testGetAccelerator(){

        //returrn, change this either to integration test or implement embedded in-memory H2 database endpoint
        QueryDatabase database = Mockito.mock(QueryDatabase.class); //create mock database

        List<Connection> expectedConnections = new ArrayList<Connection>(Arrays.asList(new Connection("PRX", "Proxima", 90),new Connection("RAN", "Ran", 100),new Connection("SIR", "Sirius", 100), new Connection("ARC", "Arcturus", 200),new Connection("ALD", "Aldermain", 250))); //populate this
        when(database.getAccelerator("SOL")).thenReturn(new Accelerator("SOL", "Sol", expectedConnections));
        Accelerator accelerator = database.getAccelerator("SOL");

        assertAll("getAccelerator returns accelerator ID, name, and connections",
                () -> assertEquals(accelerator.id, "SOL"),
                () -> assertEquals(accelerator.name, "Sol"),
                () -> assertEquals(accelerator.connections, expectedConnections),
                () -> assertThrows(Exception.class, () -> database.getAccelerator("PROXIMA"))
                );
        verify(database).getAccelerator("SOL");
    }

    @Test
    public void testGetAllAccelerators(){
        QueryDatabase database = Mockito.mock(QueryDatabase.class); //create mock database

        List<Connection> connectionList1 = new ArrayList<Connection>(Arrays.asList(new Connection("SOL", "Sol", 90), new Connection("SIR", "Sirius", 100), new Connection("ALT", "Altair", 150)));
        List<Connection> connectionList2 = new ArrayList<Connection>(Arrays.asList(new Connection("SOL", "Sol", 80), new Connection("PRX", "Proxima", 10), new Connection("CAS", "Castor", 200)));
        List<Connection> connectionList3 = new ArrayList<Connection>(Arrays.asList(new Connection("SOL", "Sol", 100)));
        Accelerator accelerator1 = new Accelerator("PRX", "Proxima", connectionList1);
        Accelerator accelerator2 = new Accelerator("SIR", "Sirius", connectionList2);
        Accelerator accelerator3 = new Accelerator("RAN", "Ran", connectionList3);
        List<Accelerator> accelerators = new ArrayList<Accelerator>(Arrays.asList(accelerator1, accelerator2, accelerator3));

        assertEquals(database.getAllAccelerators(), accelerators); //this will need editing when all the data has been entered to the official databse class


    }
}
