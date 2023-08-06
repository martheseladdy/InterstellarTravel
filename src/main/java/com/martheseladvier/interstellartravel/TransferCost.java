package com.martheseladvier.interstellartravel;
import org.springframework.stereotype.Service;

@Service
public class TransferCost implements ITransferCost{

    public static final int PERSONAL_PASSENGER_LIMIT = 4;
    public static final double PERSONAL_FUEL_PER_AU = 0.30;
    public static final double PARKING_FEE_PER_DAY = 5.0;
    public static final int HTC_PASSENGER_LIMIT = 5;
    public static final double HTC_FUEL_PER_AU = 0.45;
    public TransferInfo personalTransfer(TransferInfo personalInfo) throws Exception{
        try {
            double totalCost = 0.0;
            Integer passengers = personalInfo.getPassengers();
            Integer parkingDays = personalInfo.getParkingDays();
            int distance = personalInfo.getDistance();

            totalCost += (double) distance * PERSONAL_FUEL_PER_AU;
            totalCost += (parkingDays != null) ? parkingDays * PARKING_FEE_PER_DAY : 0;

            if (passengers != null) {
                totalCost = totalCost * numberOfTransfers((int) passengers, "personal");
            }

            TransferInfo personal = new TransferInfo("personal", distance, passengers, parkingDays);
            personal.setCost(totalCost);

            return personal;
        }
        catch(Exception e){
            System.out.println(e.toString());
            throw e;
        }
    }

    public TransferInfo htcTransfer(TransferInfo htcInfo) throws Exception{
        try {
            double totalCost = 0.0;
            Integer passengers = htcInfo.getPassengers();
            int distance = htcInfo.getDistance();

            totalCost += (double) distance * HTC_FUEL_PER_AU;

            if (passengers != null) {
                totalCost = totalCost * numberOfTransfers((int) passengers, "htc");
            }

            TransferInfo htc = new TransferInfo("htc", distance, passengers);
            htc.setCost(totalCost);

            return htc;
        }
        catch (Exception e){
            System.out.println(e.toString());
            throw e;
        }
    }

    public TransferInfo cheapestTransfer(int distance, Integer passengers, Integer parkingDays) throws Exception{
        try {
            TransferInfo personal = new TransferInfo("personal", distance, passengers, parkingDays);
            personal = personalTransfer(personal);

            TransferInfo htc = new TransferInfo("htc", distance, passengers);
            htc = htcTransfer(htc);

            if (personal.getCost() < htc.getCost()) {
                return personal;
            } else {
                return htc;
            }
        }
        catch (Exception e) {
            System.out.println(e.toString());
            throw e;
        }

    }

    public int numberOfTransfers(int passengers, String transportType) throws Exception{
        try {
            int transfersNeeded = 0;

            if (transportType.equalsIgnoreCase("personal")) {
                transfersNeeded = (int) Math.ceil((double) passengers / PERSONAL_PASSENGER_LIMIT);

            } else if (transportType.equalsIgnoreCase("htc")) {
                transfersNeeded = (int) Math.ceil((double) passengers / HTC_PASSENGER_LIMIT);
            }

            return transfersNeeded;
        }
        catch (Exception e){
            System.out.println(e.toString());
           throw e;
        }
    }
}
