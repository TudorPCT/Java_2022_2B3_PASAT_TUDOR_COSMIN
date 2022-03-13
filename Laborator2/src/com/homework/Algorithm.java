package com.homework;

import com.company.Event;

public class Algorithm {
    public Solution greedy(Problem pb)
    {
        pb.sortRooms();

        Solution sol = new Solution(pb.getNrEvents(), pb.getEvents());

        for(int i = 0; i < pb.getNrEvents(); i++){
            for(int j = 0; j < pb.getNrRooms(); j++){
                Event event = pb.getEvent(i);
                if(pb.getEventSize(i) <= pb.getRoomCapacity(j))
                {
                    int lastIndex = -1;
                    int newIndex;
                    while((newIndex = sol.indexOf(pb.getRoom(j),lastIndex+1)) != -1){
                        Event newEvent = pb.getEvent(newIndex);
                        if((event.getStartTime() >= newEvent.getStartTime() && event.getStartTime() < newEvent.getEndTime()) || (event.getEndTime() >= newEvent.getStartTime() && event.getEndTime() < newEvent.getEndTime())) {
                            lastIndex = newIndex;
                        }
                        else{
                            sol.setAssignment(pb.getRoom(j),i);
                            break;
                        }
                    }
                    if(lastIndex == -1) {
                        sol.setAssignment(pb.getRoom(j), i);
                        break;
                    }
                }
            }
        }
        return sol;
    }
}
