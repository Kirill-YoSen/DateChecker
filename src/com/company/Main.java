package com.company;

import javax.management.MBeanRegistration;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int[] dateInfo = new int[3]; //dateInfo[0: day; 1: month; 2: year]
        //1) Is Date Valid
        Scanner s = new Scanner(System.in);
        String date;
        System.out.print("Enter date: ");
        date = s.nextLine();

        //Has user entered the date?
        try {
            if (date.isEmpty()) {
                throw new NullPointerException("User has not entered the date");
            }
        } catch (NullPointerException e) {
            System.err.println(e);
             return;
        }

        //Has user entered  the date correctly?
        String dateType;

        try {
            if (date.contains("-")) {
                dateType = "American";
            } else if(date.contains("/")) {
                dateType = "European";
            } else {
                throw new InvalidPropertiesFormatException("Invalid enter");
            }
        } catch (InvalidPropertiesFormatException e) {
            System.err.println(e);
            return;
        }

        String[] di;
        switch (dateType) {
            case "American":
                di = date.split("-");
                dateInfo[0] = Integer.parseInt(di[1]);
                dateInfo[1] = Integer.parseInt(di[0]);
                dateInfo[2] = Integer.parseInt(di[2]);
                break;
            case "European":
                di = date.split("/");
                dateInfo[0] = Integer.parseInt(di[0]);
                dateInfo[1] = Integer.parseInt(di[1]);
                dateInfo[2] = Integer.parseInt(di[2]);
                break;
        }

        checkDate(dateInfo);
        Month(dateInfo[1]);
    }

    public static void checkDate(int[] dt) {
        try {
            if (dt[2] < 0) {
                throw new InvalidPropertiesFormatException("year con not be smaller than 0");
            }
            if (dt[1] > 0 && dt[1] < 13) {
                switch (dt[1]) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        if (dt[0] < 0 && dt[0] > 31) {
                            throw new InvalidPropertiesFormatException(dt[1] + " month does not contain " + dt[0] +"days" );
                        }
                        break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        if (dt[0] < 0 && dt[0] > 30) {
                            throw new InvalidPropertiesFormatException(dt[1] + " month does not contain " + dt[0] +"days" );
                        }
                        break;
                    case 2:
                        boolean visokosnij;
                        if (dt[2] % 4 != 0) {
                            visokosnij = false;
                        } else {
                            if (dt[2] % 100 == 0 && dt[2] % 400 != 0) {
                                visokosnij = false;
                            } else {
                                visokosnij = true;
                            }
                        }

                        if (visokosnij && (dt[0] < 0 || dt[0] > 29)) {
                            throw new InvalidPropertiesFormatException(dt[1] + " month does not contain " + dt[0] +"days" );
                        } else if (!visokosnij && (dt[0] < 0 || dt[0] > 28)){
                            throw new InvalidPropertiesFormatException(dt[1] +" month does not contain " + dt[0] +"days" );
                        }
                        break;
                }
            } else {
                throw new InvalidPropertiesFormatException(dt[1] + " does not exists");
            }
        } catch (InvalidPropertiesFormatException e) {
            System.err.println(e);
            return;
        }
        System.out.println("Your date is fine!");

    }

    public static void Month(int m) {
        switch (m) {
            case 1:
            case 2:
            case 12:
                System.out.println("winter");
                break;
            case 3:
            case 4:
            case 5:
                System.out.println("spring");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("IT'S SUMMER TIME!");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("autumn");
                break;
        }
    }

}

