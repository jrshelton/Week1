
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;



public class Main {

    public static void main(String[] args){

           Scanner reader = new Scanner(System.in);

           boolean done = false;
           while (!done) {
               System.out.println("to load new database in data.txt : press 1\n" +
                       "to find min price on a certain date : press 2\n" +
                       "to find max price on a certain date : press 3\n" +
                       "to find total volume on a certain date : press 4\n" +
                       "to find closing price press 5\n" +
                       "press 6 to quit");

               int n = reader.nextInt();
               switch (n){
                   case 1: newDatabase();
                            break;
                   case 2: minPrice();
                            break;
                   case 3: maxPrice();
                   case 4: totalVolume();
                            break;
                   case 5: closingPrice();
                            break;
                   case 6: done = true;
                            break;
                   default: System.out.println("Please input a number between 1 and 4");
               }
            }
            reader.close();

        }

        public static void closingPrice(){
            Scanner reader = new Scanner(System.in);

            System.out.println("Input year: ");
            int year = reader.nextInt();
            System.out.println("Input month(number): ");
            int month = reader.nextInt();
            System.out.println("Input day: ");
            int day = reader.nextInt();
            reader.nextLine();
            System.out.println("Input stock (MSFT, AAPL, GOOG, PVTL, AMZN) :");
            String stock = reader.nextLine().toUpperCase();

            Calendar cal = new GregorianCalendar();
            cal.set(year, month-1 ,day);
            StockManager.closingPrice(stock, new java.sql.Date(cal.getTime().getTime()));
            System.out.println();
        }


        public static void totalVolume(){
            Scanner reader = new Scanner(System.in);

            System.out.println("Input year: ");
            int year = reader.nextInt();
            System.out.println("Input month(number): ");
            int month = reader.nextInt();
            System.out.println("Input day: ");
            int day = reader.nextInt();
            reader.nextLine();
            System.out.println("Input stock (MSFT, AAPL, GOOG, PVTL, AMZN) :");
            String stock = reader.nextLine().toUpperCase();

            Calendar cal = new GregorianCalendar();
            cal.set(year, month-1 ,day);
            StockManager.findTotalVolume(stock, new java.sql.Date(cal.getTime().getTime()));
            System.out.println();

        }

        public static void minPrice(){
            Scanner reader = new Scanner(System.in);

            System.out.println("Input year: ");
            int year = reader.nextInt();
            System.out.println("Input month(number): ");
            int month = reader.nextInt();
            System.out.println("Input day: ");
            int day = reader.nextInt();
            reader.nextLine();
            System.out.println("Input stock (MSFT, AAPL, GOOG, PVTL, AMZN) :");
            String stock = reader.nextLine().toUpperCase();

            Calendar cal = new GregorianCalendar();
            cal.set(year, month-1 ,day);
            StockManager.FindMinPrice(stock, new java.sql.Date(cal.getTime().getTime()));
            System.out.println();

        }

        public static void maxPrice(){
            Scanner reader = new Scanner(System.in);

            System.out.println("Input year: ");
            int year = reader.nextInt();
            System.out.println("Input month(number): ");
            int month = reader.nextInt();
            System.out.println("Input day: ");
            int day = reader.nextInt();
            reader.nextLine();
            System.out.println("Input stock (MSFT, AAPL, GOOG, PVTL, AMZN):");
            String stock = reader.nextLine().toUpperCase();


            Calendar cal = new GregorianCalendar();
            cal.set(year, month-1 ,day);
            StockManager.FindMaxPrice(stock, new java.sql.Date(cal.getTime().getTime()));
            System.out.println();


        }

        public static void newDatabase(){
            Stock[] stock = Main.getData();
            if(stock != null) {
                StockManager.resetTable();
                StockManager.create();

                for (int i = 0; i < stock.length; i++) {
                    try {
                        StockManager.insert(stock[i]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public static Stock[] getData() {

            ObjectMapper mapper = new ObjectMapper();
            try {
                File data = new File("data.txt");
                Stock[] stock = mapper.readValue(data, Stock[].class);
                return stock;

             } catch (IOException e) {
                e.printStackTrace();
             }
            return null;
        }


        }

