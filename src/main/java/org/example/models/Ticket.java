package org.example.models;

public class Ticket {
    public String ticketHolder;
    public String ticketNumber;

    public Ticket(String ticketHolder, String ticketNumber) {
        this.ticketHolder = ticketHolder;
        this.ticketNumber = ticketNumber;
    }

    public String getTicketHolder() {
        return ticketHolder;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketHolder(String ticketHolder) {
        this.ticketHolder = ticketHolder;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
