package com.homework;

import com.company.Event;

import java.util.Arrays;

public class Solution {
    private Room[] assignment;
    private Event[] events;
    public Solution(int length, Event[] newEvents){
        assignment = new Room[length];
        events = newEvents;
    }

    public void setAssignment(Room room, int index) {
        assignment[index] = room;
    }

    public int indexOf(Room room, int index){
        for(int i = index; i < assignment.length; i++)
            if(room.equals(assignment[i]))
                return i;
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Solution:\n");
        for(int i = 0; i < assignment.length; i++)
            if(assignment[i] != null)
                str.append(events[i].getName()).append(" -> ").append(assignment[i].getName()).append("\n");
        return str.toString();
    }
}
