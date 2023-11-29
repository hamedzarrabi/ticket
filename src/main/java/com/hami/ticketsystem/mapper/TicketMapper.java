package com.hami.ticketsystem.mapper;

import com.hami.ticketsystem.dto.TicketDto;
import com.hami.ticketsystem.entity.Ticket;

public class TicketMapper {

    // Map ticket to ticketDto
    public static TicketDto mapToTicketDto(Ticket ticket) {

        return new TicketDto(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus()
        );
    }

    public static Ticket mapToTicket(TicketDto ticketDto) {
        return new Ticket(
                ticketDto.getId(),
                ticketDto.getTitle(),
                ticketDto.getDescription(),
                ticketDto.getStatus()
        );
    }

}
