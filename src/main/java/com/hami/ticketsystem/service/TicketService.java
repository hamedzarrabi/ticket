package com.hami.ticketsystem.service;

import com.hami.ticketsystem.dto.TicketDto;

import java.util.Optional;

public interface TicketService {
    TicketDto createTicket(TicketDto ticketDto);
    TicketDto findTicket(Long ticketId);
    void deleteTicket(Long ticketId);
    TicketDto updateTicket(TicketDto ticketDto, Long ticketId);
}
