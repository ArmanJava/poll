package com.example.demo.service;

import com.example.demo.dto.QuestionsDto;
import com.example.demo.model.Polls;
import com.example.demo.model.QuestionType;
import com.example.demo.model.Questions;
import com.example.demo.repository.PollsRepository;
import com.example.demo.repository.QuestionsRepository;
import com.example.demo.repository.QuestionsTypeRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class QuestionService {
    private final QuestionsRepository questionsRepository;
    private final QuestionsTypeRepository questionsTypeRepository;
    private final PollsRepository pollsRepository;

    public QuestionService(QuestionsRepository questionsRepository, QuestionsTypeRepository questionsTypeRepository, PollsRepository pollsRepository) {
        this.questionsRepository = questionsRepository;
        this.questionsTypeRepository = questionsTypeRepository;
        this.pollsRepository = pollsRepository;
    }

    public boolean addQuestion(QuestionsDto questionDto) {
        Questions question = new Questions();
        question.setDescription(questionDto.getDescription());
        QuestionType questionType = questionsTypeRepository.findById(questionDto.getQuestionTypeId()).orElseThrow(() -> new ObjectNotFoundException(Polls.class, "Object not found"));
        // todo добавить AnswerOptions
        question.setQuestionType(questionType);
        question = questionsRepository.save(question);
        Polls poll = pollsRepository.findById(questionDto.getPollId()).orElseThrow(() -> new ObjectNotFoundException(Polls.class, "Object not found"));
        poll.getQuestions().add(question);
        pollsRepository.save(poll);
        return true;
    }

    public boolean editQuestion(QuestionsDto questionDto) {
        Questions questions = questionsRepository.findById(questionDto.getId()).orElseThrow(() -> new ObjectNotFoundException(Polls.class, "Object not found"));
        if (questionDto.getDescription() != null) {
            questions.setDescription(questionDto.getDescription());
        }
        if (questionDto.getQuestionTypeId() != null) {
            questions.setQuestionType(questionsTypeRepository.findById(questionDto.getQuestionTypeId()).orElseThrow(() -> new ObjectNotFoundException(Polls.class, "Object not found")));
        }
       return true;
    }

    public boolean deleteQuestion(Long id) {
        Questions questions = questionsRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Polls.class, "Object not found"));
        questionsRepository.delete(questions);
        return true;
    }
}
