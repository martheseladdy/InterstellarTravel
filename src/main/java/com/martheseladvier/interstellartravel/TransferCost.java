package com.martheseladvier.interstellartravel;
import org.springframework.stereotype.Service;
@Service
public class TransferCost implements ITransferCost{
    public double personalTransfer(int distance, int passengers, int parkingDays){
        return 0.0;
    }

    public double htcTransfer(int distance, int passengers){
        return 0.0;
    }

    public TransferInfo cheapestTransfer(TransferInfo personal, TransferInfo htc){
        return new TransferInfo();
    }

    public int numberOfTransfers(int passengers, String transportType){
        return 0;
    }
}
