package com.martheseladvier.interstellartravel;

public interface ITransferCost {

    TransferInfo personalTransfer(TransferInfo personalTransfer) throws Exception;

    TransferInfo htcTransfer(TransferInfo htcTransfer)throws Exception;

    TransferInfo cheapestTransfer(int distance, Integer passengers, Integer parkingDays)throws Exception;

    int numberOfTransfers(int passengers, String transportType)throws Exception;

}
