package com.hami.ticketsystem.controller;

import com.hami.ticketsystem.dto.TicketDto;
import com.hami.ticketsystem.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ticket")
@Api(value = "CRUD Rest APIs for Ticket resource")
public class TicketController {

    @Autowired private TicketService ticketService;

    @ApiOperation(value = "Create Ticket REST API")
    @PostMapping("/save-ticket")
    public ResponseEntity<TicketDto> createTicket(@RequestBody @Valid TicketDto ticketDto) {
        TicketDto ticket = ticketService.createTicket(ticketDto);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }
}
