package com.company;

public class Main {

    public static void main(String[] args) {
        Main lab2 = new Main();
        lab2.compulsory();
    }

    public void compulsory(){
        Event[] events = new Event[5];
        events[0] = new Event("C1", 100,8,10);
        events[1] = new Event("C2", 100,10,12);
        events[2] = new Event("L1", 30,8,10);
        events[3] = new Event("L2", 30,8,10);
        events[4] = new Event("L3", 30,10,12);
        Room[] rooms = new Room[4];
        rooms[0] = new Room("401", Types.COMPUTER_LAB, 30);
        rooms[1] = new Room("403", Types.COMPUTER_LAB, 30);
        rooms[2] = new Room("405", Types.COMPUTER_LAB, 30);
        rooms[3] = new Room("309", Types.LECTURE_HALL, 100);

        for (Event event : events) System.out.println(event);
        for (Room room : rooms) System.out.println(room);
    }
}
