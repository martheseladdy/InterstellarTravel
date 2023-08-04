package com.martheseladvier.interstellartravel;

public interface ITransferCost {

    public TransferInfo personalTransfer(TransferInfo personalTransfer);

    public TransferInfo htcTransfer(TransferInfo htcTransfer);

    public TransferInfo cheapestTransfer(int distance, Integer passengers, Integer parkingDays);

    public int numberOfTransfers(int passengers, String transportType);

}
