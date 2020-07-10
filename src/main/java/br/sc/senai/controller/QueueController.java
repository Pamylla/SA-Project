package br.sc.senai.controller;

import br.sc.senai.model.Queue;
import br.sc.senai.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "/sa")
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;

    @GetMapping(path = "/queues")
    public @ResponseBody
    ResponseEntity<Iterable<Queue>> getAllQueues() {

        try {
            Iterable<Queue> queues = queueRepository.findAll();
            if (((Collection<?>) queues).size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(queues, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/queues")
    public @ResponseBody ResponseEntity<Queue> addNewQueue(@RequestBody Queue queue) {
        try {
            Queue newQueue = queueRepository.save(queue);
            return new ResponseEntity<>(newQueue, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/queues/{id}")
    public @ResponseBody ResponseEntity<Queue> updateQueue(@PathVariable("id") Integer id, @RequestBody Queue queue) {

        Optional<Queue> queueData = queueRepository.findById(id);

        if (queueData.isPresent()) {
            Queue updatedQueue = queueData.get();
            updatedQueue.setQrCode(queue.getQrCode());
            updatedQueue.setLength(queue.getLength());
            updatedQueue.setStatus(queue.isStatus());
            updatedQueue.setBegin(queue.getBegin());
            updatedQueue.setEnd(queue.getEnd());
            return new ResponseEntity<>(queueRepository.save(updatedQueue), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/queues/{id}")
    public @ResponseBody ResponseEntity<HttpStatus> removeQueue(@PathVariable("id") Integer id) {

        try {
            queueRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
