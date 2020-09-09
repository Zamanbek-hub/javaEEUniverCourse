package db.managers;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import db.clasess.Ticket;

import java.util.ArrayList;

public class TicketManager {
    private static ArrayList<Ticket> tickets = new ArrayList<>();
    private static Long id = 10L;

    static {
        tickets.add(new Ticket(1L, "Aktobe", "Almaty", 30000, 27));
        tickets.add(new Ticket(2L, "Taraz", "Semey", 16000, 21));
        tickets.add(new Ticket(3L, "Almaty", "Kyzylorda", 8000, 17));

        tickets.add(new Ticket(4L, "Zhambyl", "Aktobe", 40000, 18));
        tickets.add(new Ticket(5L, "Taraz", "Aktau", 34000, 22));
        tickets.add(new Ticket(6L, "Astana", "Pavlodar", 12000, 5));

        tickets.add(new Ticket(7L, "Zhezkazgan", "Taldykorgan", 28000, 15));
        tickets.add(new Ticket(8L, "Karaganda", "Akmola", 19000, 19));
        tickets.add(new Ticket(9L, "Oral", "Semey", 47000, 72));

//        tickets.add(new Ticket(1L, "Zhezkazgan", "Taldykorgan", 28000, 15));
    }

    //this method adds new ticket into list
    public static void addTicket(Ticket ticket) {
        ticket.setId(id);
        tickets.add(ticket);
        id++;
    }

    //this method returns an object of ticket by id
    public static Ticket getTicket(Long id) {
        for(Ticket ticket: tickets){
            if(ticket.getId().equals(id)){
                return ticket;
            }
        }
        return new Ticket(); // null cannot be returned
    }

    //this method returns a list of all tickets
    public static ArrayList<Ticket> getAllTickets() {
        return tickets;
    }

    //this method returns a list of tickets from index with size
    public static ArrayList<Ticket> getTicketsFromRange(int page) {
        page = page - 1;
        // convert to fromIndex and toIndex to measure with id
        int fromIndex = page * 3;
        int toIndex = fromIndex + 3;
        if(toIndex > tickets.size())
            toIndex = tickets.size();


        // so that new tickets are first on the list
        ArrayList<Ticket> reverseList = new ArrayList<>();
        for(int i = tickets.size()-1; i >= 0 ; i--){
            reverseList.add(tickets.get(i));
        }

        return new ArrayList<Ticket>(reverseList.subList(fromIndex,toIndex));
    }

    //this method deletes ticket from list by id
    public static void deleteTicket(Long id) {
        for(int i = 0; i < tickets.size(); i++){
            if(tickets.get(i).getId().equals(id)){
                tickets.remove(tickets.get(i));
                break;
            }
        }
    }
}
