package com.example.demo.service;

import com.example.demo.dto.PollsDto;
import com.example.demo.dto.PollsResponseDto;
import com.example.demo.model.Polls;
import com.example.demo.repository.PollsRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PollsService {
    private final PollsRepository pollsRepository;

    public PollsService(PollsRepository pollsRepository) {
        this.pollsRepository = pollsRepository;
    }

    public boolean addPoll(PollsDto pollsDto) {
        Polls poll = new Polls();
        poll.setName(pollsDto.getName());
        poll.setEndDate(pollsDto.getEndDate());
        poll.setActive(pollsDto.getActive());
        poll.setDescription(pollsDto.getDescription());
        poll.setStartDate(pollsDto.getStartDate());
        pollsRepository.save(poll);
        return true;
    }

    public boolean editPoll(PollsDto pollsDto) {
        Polls poll = pollsRepository.findById(pollsDto.getId()).orElseThrow(() -> new ObjectNotFoundException(Polls.class, "Object not found"));
        if (pollsDto.getName() != null) {
            poll.setName(pollsDto.getName());
        }
        if (pollsDto.getEndDate() != null) {
            poll.setEndDate(pollsDto.getEndDate());
        }
        //todo
        if(pollsDto.getActive()!= null){
            poll.setActive(pollsDto.getActive());
        }

        poll.setDescription(pollsDto.getDescription());
        pollsRepository.save(poll);
        return true;
    }

    public boolean deletePoll(Long id) {
        Polls poll = pollsRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Polls.class, "Object not found"));
        pollsRepository.delete(poll);
        return true;
    }

    public List<PollsResponseDto> getAllActivePolls() {
       List<Polls>polls =  pollsRepository.findByActiveTrue();
       return polls.stream().map(PollsResponseDto::create).collect(Collectors.toList());
    }
}
