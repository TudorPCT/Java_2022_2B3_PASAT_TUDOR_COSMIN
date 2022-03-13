package com.homework;
import com.company.Event;

import java.util.Arrays;

public class Problem {
    private Event[] events;
    private Room[] rooms;

    public Problem(){
        events = new Event[0];
        rooms = new Room[0];
    }

    public Event[] getEvents(){
        return events;
    }

    public boolean addEvent(Event newEvent){
        boolean found = false;
        for (Event event : events)
            if (event.equals(newEvent)) {
                found = true;
                break;
            }
        if(!found)
        {
            Event[] temp = new Event[events.length+1];
            System.arraycopy(events,0,temp,0,events.length);
            temp[events.length] = newEvent;
            events = temp;
            return true;
        }
        return false;
    }

    public boolean addRoom(Room newRoom){
        boolean found = false;
        for (Room room : rooms)
            if (room.equals(newRoom)) {
                found = true;
                break;
            }
        if(!found)
        {
            Room[] temp = new Room[rooms.length+1];
            System.arraycopy(rooms,0,temp,0,rooms.length);
            temp[rooms.length] = newRoom;
            rooms = temp;
            return true;
        }
        return false;
    }

    public int getNrEvents(){
        return events.length;
    }

    public int getNrRooms(){
        return rooms.length;
    }

    public int getEventSize(int index){
        return events[index].getSize();
    }

    public int getRoomCapacity(int index){
        return rooms[index].getCapacity();
    }

    public Room getRoom(int index){
        return rooms[index];
    }

    public void sortRooms(){
        for(int i = 0; i < rooms.length; i++)
            for(int j = i+1; j < rooms.length; j++)
                if(rooms[i].compareTo(rooms[j]) == 1)
                {
                    Room aux = rooms[i];
                    rooms[i] = rooms[j];
                    rooms[j] = aux;
                }
    }

    public Event getEvent(int index) { return events[index]; }

    @Override
    public String toString() {
        return "Problem{" + '\n' +
                "events=" + Arrays.toString(events) + '\n' +
                "rooms=" + Arrays.toString(rooms) +
                "\n}";
    }
}
