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
                () -> assertEquals(3.0, transferCost.personalTransfer(10,myNull, myNull)),
                () -> assertEquals(3.0, transferCost.personalTransfer(10,3, myNull)) ,
                () -> assertEquals(18.0, transferCost.personalTransfer(10,myNull, 3)),
                () -> assertEquals(23.0, transferCost.personalTransfer(10,3, 4)),
                () -> assertEquals(6.0, transferCost.personalTransfer(10,5, myNull)),
                () -> assertEquals(26.0, transferCost.personalTransfer(10,5, 4) ),
                () ->  assertThrows(Exception.class, () -> {
                    transferCost.personalTransfer(myNull,5, 4);
                }),
                () ->  assertThrows(Exception.class, () -> {
                            transferCost.personalTransfer(myNull,myNull, myNull);
                        }),
                () ->  assertThrows(Exception.class, () -> {
                            transferCost.personalTransfer(0,5, 4);
                        })
                         );
    }
    @Test
    public void testHtcTransfer(int distance, int passengers){
        assertAll( "HTC Transfer tests",
                () -> assertEquals(4.5, transferCost.htcTransfer(10,myNull)),
                () -> assertEquals(4.5, transferCost.htcTransfer(10,3)) ,
                () -> assertEquals(9.0, transferCost.htcTransfer(10,6)),
                () ->  assertThrows(Exception.class, () -> {
                    transferCost.htcTransfer(myNull,5);
                }),
                () ->  assertThrows(Exception.class, () -> {
                    transferCost.htcTransfer(myNull,myNull);
                }),
                () ->  assertThrows(Exception.class, () -> {
                    transferCost.htcTransfer(0,6);
                })
        );
    }

    @Test
    public void testCheapestTransfer(TransferInfo personal, TransferInfo htc){
        TransferInfo personalTransfer = new TransferInfo("personal", 45.67);
        TransferInfo htcTransfer = new TransferInfo("htc", 32.10);

        assertEquals(htcTransfer, transferCost.cheapestTransfer(personalTransfer, htcTransfer));

        htcTransfer.setCost(789.00);

        assertEquals(personalTransfer, transferCost.cheapestTransfer(personalTransfer, htcTransfer));

        htcTransfer.setCost(00.00);

        assertThrows(Exception.class, () -> transferCost.cheapestTransfer(personalTransfer, htcTransfer));

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
