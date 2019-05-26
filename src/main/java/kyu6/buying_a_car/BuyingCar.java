package kyu6.buying_a_car;

public class BuyingCar {
    public static int[] nbMonths(int startPriceOld, int startPriceNew, int savingperMonth, double percentLossByMonth) {
        printHeader();
        int month = 0;
        double startPriceOldDouble = startPriceOld;
        double startPriceNewDouble = startPriceNew;
        double carDiff = startPriceNewDouble - startPriceOldDouble;
        printData(startPriceOldDouble, startPriceNewDouble, savingperMonth * month, percentLossByMonth, carDiff);
        while (carDiff > 0) {
            month++;
            percentLossByMonth = decreaseIfOddMonth(percentLossByMonth, month);
            startPriceOldDouble *=  (100 - percentLossByMonth) / 100;
            startPriceNewDouble *=  (100 - percentLossByMonth) / 100;
            carDiff = (startPriceNewDouble - startPriceOldDouble - (savingperMonth * month));
            printData(startPriceOldDouble, startPriceNewDouble, savingperMonth * month, percentLossByMonth, carDiff);
        }
        return new int[] {month, (int) -Math.round(carDiff)};
    }

    private static double decreaseIfOddMonth(double percentLossByMonth, int month) {
        if (month % 2 == 0) {
            return percentLossByMonth + 0.5;
        }
        return percentLossByMonth;
    }

    private static void printHeader() {
        System.out.println(String.format("|%s|%s|%s|%s|%s|", "startPriceOld", "startPriceNew", "savingperMonth", "percentLossByMonth", "carDiff" ));
    }

    private static void printData(double startPriceOld, double startPriceNew, int savingperMonth, double percentLossByMonth, double carDiff) {
        System.out.println(String.format("|%4.3f|%4.3f|%d|%4.3f|%4.3f|", startPriceOld, startPriceNew, savingperMonth, percentLossByMonth, carDiff ));
    }
}
