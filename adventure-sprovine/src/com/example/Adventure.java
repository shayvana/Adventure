package com.example;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;

import static java.lang.System.exit;

public class Adventure {

    private static Layout.Room currentRoom;
    private static Layout layout;

    public static void main(String[] args) throws Exception {
        System.out.println("Would you like to use your own url? Y/N: ");
        String userInput = getUserInput();
        if (userInput.equals("Y")) {
            System.out.println("Input url: ");
            String userUrl = getUserInput();
            layout = jsonToLayout(userUrl);


        } else {
            System.out.println("Oh okay");
            layout = jsonToLayout("https://courses.engr.illinois.edu/cs126/adventure/siebel.json");
        }

        currentRoom = layout.getStartingRoom();

        playGame();
    }

    public static Layout jsonToLayout(String urlString) throws Exception {
        URL url = new URL(urlString);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        Layout layout = new Gson().fromJson(reader, Layout.class);
        return layout;
    }

    private static void playGame() throws Exception {
        displayRoomInformation();
        checkUserInput(getUserInput());
    }

    private static void displayRoomInformation() {
        System.out.println(currentRoom.description);

        if (currentRoom == layout.getEndingRoom()) {
            System.out.println("You have reached your destination");
            exit(0);
        }

        Layout.Room.Direction[] directions = currentRoom.directions;

        String printString = "You can go: ";

        for (int i = 0; i < directions.length; i++) {
            if (i != directions.length - 1) {
                printString += directions[i].directionName + ", ";
            }

            else {
                printString += directions[i].directionName;
            }

        }

        System.out.println(printString);
    }

    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            return userInput;
        }

        return "";
    }

    private static void checkUserInput(String userInput) throws Exception {
        String lowerCaseinput = userInput.toLowerCase();

        if (lowerCaseinput.equals("quit") || lowerCaseinput.equals("exit")) {
            exit(0);
        }

        if (lowerCaseinput.length() > 2 && lowerCaseinput.substring(0, 2).equals("go")) {

            lowerCaseinput = lowerCaseinput.substring(3);

            boolean roomFound = false;

            for (int i = 0; i < currentRoom.directions.length; i++) {
                if (lowerCaseinput.equals(currentRoom.directions[i].directionName.toLowerCase())) {
                    currentRoom = layout.getRoomFromString(currentRoom.directions[i].room);
                    roomFound = true;
                }

            }

            if (!roomFound) {
                System.out.println("I can't " + userInput);
            }

        } else {
            System.out.println("I don't understand '" + userInput + "'");
        }

        playGame();
    }
}