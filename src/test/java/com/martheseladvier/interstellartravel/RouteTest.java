package com.martheseladvier.interstellartravel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouteTest {
    @Mock
    private IQueryDatabase queryDatabase;

    @InjectMocks
    Route route;
    @Test
    public void testShortestRoute() throws Exception {

        List<Connection> connectionStart = new ArrayList<>(Arrays.asList(
                new Connection("ONE", "One", 1),
                new Connection("TWO", "Two", 100)
        ));
        Accelerator acceleratorStart = new Accelerator("STA", "Start", connectionStart);

        List<Connection> connectionOne = new ArrayList<>(Arrays.asList(
                new Connection("TWO", "Two", 1),
                new Connection("THR", "Three", 100)
        ));
        Accelerator acceleratorOne = new Accelerator("ONE", "One", connectionOne);

        List<Connection> connectionTwo = new ArrayList<>(Arrays.asList(
                new Connection("ONE", "One", 3)
        ));
        Accelerator acceleratorTwo = new Accelerator("TWO", "Two", connectionTwo);


        List<Connection> connectionThree = new ArrayList<>(Arrays.asList(
                new Connection("TWO", "Two", 2),
                new Connection("FIN", "Finish", 10)
        ));
        Accelerator acceleratorThree = new Accelerator("THR", "Three", connectionThree);


        List<Connection> connectionFinish = new ArrayList<>(Arrays.asList(
                new Connection("TWO", "Two", 350),
                new Connection("STA", "Start", 800)
        ));
        Accelerator acceleratorFinish = new Accelerator("FIN", "Finish", connectionFinish);

        List<Accelerator> allAccelerators = new ArrayList<>(Arrays.asList(acceleratorStart, acceleratorOne, acceleratorTwo, acceleratorThree, acceleratorFinish));

        when(queryDatabase.getAllAccelerators()).thenReturn(allAccelerators);
        when(queryDatabase.getAccelerator("ONE")).thenReturn(acceleratorOne);
        when(queryDatabase.getAccelerator("TWO")).thenReturn(acceleratorTwo);
        when(queryDatabase.getAccelerator("THR")).thenReturn(acceleratorThree);
        when(queryDatabase.getAccelerator("FIN")).thenReturn(acceleratorFinish);
        when(queryDatabase.getAccelerator("STA")).thenReturn(acceleratorFinish);


        List<String> expectedRoute = new ArrayList<>(Arrays.asList("STA", "ONE", "THR", "FIN"));
        assertAll("Transfer tests",
                () -> assertEquals(expectedRoute,route.getRoute(acceleratorStart, acceleratorFinish)),
                () -> assertEquals(11.1,route.getCost()),
                () -> assertThrows(Exception.class, () -> {
                    route.getRoute(null, null);
                    }),
                () -> assertThrows(Exception.class, () -> {
                    route.getCost();
                    })
        );
    }

}