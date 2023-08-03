package com.martheseladvier.interstellartravel;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
public class MockDatabase implements DatabaseInterface{
    @Override
    public Accelerator getAccelerator(int id) {
        List<Connection> connections = new ArrayList<Connection>(Arrays.asList(new Connection(2, "Proxima", 90),new Connection(7, "Ran", 100),new Connection(3, "Sirius", 100), new Connection(8, "Arcturus", 200),new Connection(12, "Aldermain", 250)));
        Accelerator accelerator = new Accelerator(1, "Sol", connections);
        return accelerator;
    }

    public List<Accelerator> getAllAccelerators() {
        List<Connection> connectionList1 = new ArrayList<Connection>(Arrays.asList(new Connection(1, "Sol", 90), new Connection(3, "Sirius", 100), new Connection(4, "Altair", 150)));
        List<Connection> connectionList2 = new ArrayList<Connection>(Arrays.asList(new Connection(1, "Sol", 80), new Connection(2, "Proxima", 10), new Connection(4, "Castor", 200)));
        List<Connection> connectionList3 = new ArrayList<Connection>(Arrays.asList(new Connection(1, "Sol", 100)));
        Accelerator accelerator1 = new Accelerator(2, "Proxima", connectionList1);
        Accelerator accelerator2 = new Accelerator(3, "Sirius", connectionList2);
        Accelerator accelerator3 = new Accelerator(7, "Ran", connectionList3);
        List<Accelerator> accelerators = new ArrayList<Accelerator>(Arrays.asList(accelerator1, accelerator2, accelerator3));
        return accelerators;
    }
}
