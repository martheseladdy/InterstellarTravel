package com.martheseladvier.interstellartravel;

public interface ITransferCost {

    public double personalTransfer(int distance, int passengers, int parkingDays);

    public double htcTransfer(int distance, int passengers);

    public TransferInfo cheapestTransfer(TransferInfo personal, TransferInfo htc);

    public int numberOfTransfers(int passengers, String transportType);

}
