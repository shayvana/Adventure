package com.example;

public class Layout {
    String startingRoom;
    String endingRoom;
    Room[] rooms;

    class Room {
        String name;
        String description;
        Direction[] directions;

        class Direction {
            String directionName;
            String room;
        }
    }

    public Room getStartingRoom() {
        return getRoomFromString(startingRoom);
    }

    public Room getEndingRoom() {
        return getRoomFromString(endingRoom);
    }

    public Room getRoomFromString(String roomName) {
        if (roomName == null) {
            return null;
        }

        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].name.equals(roomName)) {
                return rooms[i];
            }
        }

        return null;
    }


}
