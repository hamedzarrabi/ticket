package com.hami.ticketsystem.service.impl;

import com.hami.ticketsystem.dto.TicketDto;
import com.hami.ticketsystem.entity.Ticket;
import com.hami.ticketsystem.exception.ResourceNotFoundException;
import com.hami.ticketsystem.mapper.TicketMapper;
import com.hami.ticketsystem.repository.TicketRepository;
import com.hami.ticketsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.hami.ticketsystem.mapper.TicketMapper.mapToTicket;
import static com.hami.ticketsystem.mapper.TicketMapper.mapToTicketDto;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {

        if (!(ticketDto == null)) {

            Ticket ticket = mapToTicket(ticketDto);
            Ticket saveTicket = ticketRepository.save(ticket);

            return mapToTicketDto(saveTicket);
        }
        return null;
    }

    @Override
    public TicketDto findTicket(Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException("Ticket", "id", ticketId)
        );

        return mapToTicketDto(ticket);
    }

    @Override
    public void deleteTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException("Ticket", "id", ticketId)
        );
        ticketRepository.delete(ticket);
    }

    @Override
    public TicketDto updateTicket(TicketDto ticketDto, Long ticketId) {

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException("Ticket", "id", ticketId)
        );

        ticket.setTitle(ticket.getTitle());
        ticket.setStatus(ticketDto.getStatus());
        ticket.setDescription(ticket.getDescription());

        Ticket updateTicket = ticketRepository.save(ticket);

        return mapToTicketDto(updateTicket);
    }
}
