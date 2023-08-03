package com.martheseladvier.interstellartravel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class AcceleratorDatabaseTest {

    @Test
    public void testGetAccelerator(){
        Database database = Mockito.mock(Database.class); //create mock database

        List<Connection> expectedConnections = new ArrayList<Connection>(Arrays.asList(new Connection(2, "Proxima", 90),new Connection(7, "Ran", 100),new Connection(3, "Sirius", 100), new Connection(8, "Arcturus", 200),new Connection(12, "Aldermain", 250))); //populate this
        when(database.getAccelerator(1)).thenReturn(new Accelerator(1, "Sol", expectedConnections));
        Accelerator accelerator = database.getAccelerator(1);

        assertAll("getAccelerator returns accelerator ID, name, and connections",
                () -> assertEquals(accelerator.getId(), 1),
                () -> assertEquals(accelerator.getName(), "Sol"),
                () -> assertEquals(accelerator.getConnections(), expectedConnections),
                () -> assertThrows(Exception.class, () -> database.getAccelerator(0))
                );
        verify(database).getAccelerator(1);
    }

    @Test
    public void testGetAllAccelerators(){
        Database database = Mockito.mock(Database.class); //create mock database

        List<Connection> connectionList1 = new ArrayList<Connection>(Arrays.asList(new Connection(1, "Sol", 90), new Connection(3, "Sirius", 100), new Connection(4, "Altair", 150)));
        List<Connection> connectionList2 = new ArrayList<Connection>(Arrays.asList(new Connection(1, "Sol", 80), new Connection(2, "Proxima", 10), new Connection(4, "Castor", 200)));
        List<Connection> connectionList3 = new ArrayList<Connection>(Arrays.asList(new Connection(1, "Sol", 100)));
        Accelerator accelerator1 = new Accelerator(2, "Proxima", connectionList1);
        Accelerator accelerator2 = new Accelerator(3, "Sirius", connectionList2);
        Accelerator accelerator3 = new Accelerator(7, "Ran", connectionList3);
        List<Accelerator> accelerators = new ArrayList<Accelerator>(Arrays.asList(accelerator1, accelerator2, accelerator3));

        assertEquals(database.getAllAccelerators(), accelerators); //this will need editing when all the data has been entered to the official databse class


    }
}
