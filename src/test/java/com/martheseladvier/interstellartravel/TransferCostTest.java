package com.martheseladvier.interstellartravel;
import org.junit.jupiter.api.Test;
import org.springframework.lang.Nullable;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferCostTest {
    TransferCost transferCost = new TransferCost();
    @Nullable Integer myNull = null;
    @Nullable String myNullString = null;
    @Test
    public void testPersonalTransfer(){

        assertAll( "Personal Transfer tests",
                () -> assertEquals(3.0, transferCost.personalTransfer(new TransferInfo("personal", 10,myNull, myNull)).getCost()),
                () -> assertEquals(3.0, transferCost.personalTransfer(new TransferInfo("personal",10,3, myNull)).getCost()) ,
                () -> assertEquals(18.0, transferCost.personalTransfer(new TransferInfo("personal",10,myNull, 3)).getCost()),
                () -> assertEquals(23.0, transferCost.personalTransfer(new TransferInfo("personal",10,3, 4)).getCost()),
                () -> assertEquals(6.0, transferCost.personalTransfer(new TransferInfo("personal",10,5, myNull)).getCost()),
                () -> assertEquals(46.0, transferCost.personalTransfer(new TransferInfo("personal",10,5, 4)).getCost()),
                () ->  assertThrows(Exception.class, () -> {
                    transferCost.personalTransfer(new TransferInfo("personal",myNull,5, 4));
                })//,
//scope determinant if considered an error or if happy to return a zero
               // () ->  assertThrows(Exception.class, () -> {
                 //           transferCost.personalTransfer(new TransferInfo("personal",0,5, 4));
                   //     })
                         );
    }
    @Test
    public void testHtcTransfer(){
        assertAll( "HTC Transfer tests",
                () -> assertEquals(4.5, transferCost.htcTransfer(new TransferInfo("htc",10,myNull)).getCost()),
                () -> assertEquals(4.5, transferCost.htcTransfer(new TransferInfo("htc",10,3)).getCost()) ,
                () -> assertEquals(9.0, transferCost.htcTransfer(new TransferInfo("htc",10,6)).getCost()),
                () ->  assertThrows(Exception.class, () -> {
                    transferCost.htcTransfer(new TransferInfo("htc",myNull,5));
                })
        );
    }

    @Test
    public void testCheapestTransfer(){


        assertAll("Cheapest Transfer tests",
                () -> assertEquals("personal", transferCost.cheapestTransfer(100, 1, null).getType()),
                () ->  assertEquals(30, transferCost.cheapestTransfer(100, 1, null).getCost()),
                () ->  assertEquals("htc", transferCost.cheapestTransfer(100, 5, 3).getType()),
                () ->  assertEquals(45, transferCost.cheapestTransfer(100, 5, 3).getCost())
                );







    }

    @Test
    public void testNumberOfTransfers(){

        assertAll("Test calculation of transfer vehicles needed",
                () -> assertEquals(1, transferCost.numberOfTransfers(3, "personal")),
                () -> assertEquals(1, transferCost.numberOfTransfers(4, "personal")),
                () -> assertEquals(2, transferCost.numberOfTransfers(5, "personal")),
                () -> assertEquals(1, transferCost.numberOfTransfers(4, "htc")),
                () -> assertEquals(1, transferCost.numberOfTransfers(5, "htc")),
                () -> assertEquals(2, transferCost.numberOfTransfers(6, "htc")),
                () -> assertEquals(2000, transferCost.numberOfTransfers(9999, "htc")),
                () -> assertEquals(2500, transferCost.numberOfTransfers(9999, "personal")),
                () -> assertThrows(Exception.class, () -> {
                    transferCost.numberOfTransfers(myNull, "personal");
                }),
                () -> assertThrows(Exception.class, () -> {
                    transferCost.numberOfTransfers(4, myNullString);
                })
                );
    }
}
