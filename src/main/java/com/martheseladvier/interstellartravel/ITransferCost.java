package com.martheseladvier.interstellartravel;

public interface ITransferCost {

    public TransferInfo personalTransfer(TransferInfo personalTransfer) throws Exception;

    public TransferInfo htcTransfer(TransferInfo htcTransfer)throws Exception;

    public TransferInfo cheapestTransfer(int distance, Integer passengers, Integer parkingDays)throws Exception;

    public int numberOfTransfers(int passengers, String transportType)throws Exception;

}
