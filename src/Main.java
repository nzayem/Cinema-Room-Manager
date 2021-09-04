import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int ticketsTotal = 0;
        float percentage = 0;
        int currentIncome = 0;
        int totalIncome = 0;
        int ticketPrice = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsRow = scanner.nextInt();

        menu();

        String[][] seatMatrix = new String[rows + 1][seatsRow + 1];

        //Calculating total Income to be showed when no purchase is made

        int totalSeats = rows * seatsRow;

        int frontSeats = seatsRow * (rows / 2);

        int backSeats = totalSeats - frontSeats;

        if (totalSeats <= 60) {

            totalIncome = 10 * totalSeats;

        } else if (totalSeats > 60) {

            totalIncome = 8 * backSeats + 10 * frontSeats ;

        }

        
        //Setting up the seat arrangement

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seatsRow; j++) {

                seatMatrix[0][0] = " ";
                seatMatrix[0][j] = String.valueOf(j);
                seatMatrix[i][0] = String.valueOf(i);
                seatMatrix[i][j] = "S";

            }

        }

        while (scanner.hasNext()) {

            int userInput = scanner.nextInt();

            switch (userInput) {

                case 0 -> {
                    return;
                }

                case 1 -> {

                    printCinema(seatMatrix, rows, seatsRow);
                    menu();

                }

                case 2 -> {

                    System.out.println("Enter a row number:");
                    int rowNumber = scanner.nextInt();

                    System.out.println("Enter a seat number in that row:");
                    int seatNumber = scanner.nextInt();

                    while (true) {

                        try {

                            if (Objects.equals(seatMatrix[rowNumber][seatNumber], "S")) {

                                seatMatrix[rowNumber][seatNumber] = "B";

                                break;

                            } else {

                                System.out.println("That ticket has already been purchased!");

                                System.out.println("Enter a row number:");
                                rowNumber = scanner.nextInt();

                                System.out.println("Enter a seat number in that row:");
                                seatNumber = scanner.nextInt();

                            }

                        } catch (Exception e) {

                            System.out.println("Wrong input!");

                            System.out.println("Enter a row number:");
                            rowNumber = scanner.nextInt();

                            System.out.println("Enter a seat number in that row:");
                            seatNumber = scanner.nextInt();

                        }

                    }

                    ticketsTotal++;

                    percentage = 100 * (float) ticketsTotal / totalSeats;

                    if (totalSeats <= 60) {

                        System.out.println("Ticket price: $10");

                        ticketPrice = 10;

                    } else if (totalSeats > 60) {

                        if (rowNumber <= rows / 2) {

                            System.out.println("Ticket price: $10");

                            ticketPrice = 10;

                        } else {

                            System.out.println("Ticket price: $8");

                            ticketPrice = 8;

                        }

                    }

                    currentIncome += ticketPrice;


                    menu();

                }

                case 3 -> {

                    stats(ticketsTotal, percentage, currentIncome, totalIncome);

                    menu();

                }

            }

        }


    }


    public static void menu() {

        System.out.println("\n1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n");
    }


    public static void stats(int totalTickets, float percent, int currentIncome, int totalIncome) {

        System.out.println("Number of purchased tickets: " + totalTickets);
        System.out.printf("Percentage: %.2f%%", percent);
        System.out.println();
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);


    }


    public static void printCinema(String[][] array, int rows, int seatsRow) {

        //Printing the matrix of seats

        System.out.println("Cinema:");

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seatsRow; j++) {

                System.out.print(array[i][j] + " ");

            }

            System.out.println();
        }

    }

}
