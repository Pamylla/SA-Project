package br.sc.senai.controller;

import br.sc.senai.model.Queue;
import br.sc.senai.repository.QueueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/queue")
public class QueueController {

    @Autowired
    private QueueRepository queueRepository;

    @GetMapping("/all")
    public  @ResponseBody
    Iterable<Queue> getAllQueues() {
        return queueRepository.findAll();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewQueue(@RequestParam String qrCode, @RequestParam int length,
                       @RequestParam String begin, @RequestParam String end) {
        Queue q = new Queue();
        q.setQrCode(qrCode);
        q.setLength(length);
        q.setBegin(begin);
        q.setEnd(end);
        queueRepository.save(q);
        return "Fila cadastrada com sucesso no banco de dados";
    }

    @PostMapping(path = "/update")
    public  @ResponseBody
    String updateQueue(@RequestParam Integer id ,@RequestParam String qrCode) {
        Queue q = queueRepository.findById(id).get();
        q.setQrCode(qrCode);
        return "qrCode da fila de id " + q.getId() + " atualizado no banco de dados";
    }

    @PostMapping(path = "/remove")
    public @ResponseBody
    String removeQueue(@RequestParam Integer id) {
        queueRepository.deleteById(id);
        return "Loja excluída do banco de dados";
    }
}
