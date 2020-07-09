package br.sc.senai.controller;

import br.sc.senai.model.Store;
import br.sc.senai.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/stores")
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/all")
    public  @ResponseBody
    Iterable<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @PostMapping(path = "/allbyname")
    public @ResponseBody
    Iterable<Store> findByName(@RequestParam String name) {
        return storeRepository.findAllByName(name);
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewStore(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        Store s = new Store();
        s.setName(name);
        s.setEmail(email);
        s.setPassword(password);
        storeRepository.save(s);
        return "Loja cadastrada com sucesso no banco de dados";
    }

    @PostMapping(path = "/update")
    public  @ResponseBody
    String updateStore(@RequestParam Integer id,
                       @RequestParam String name, @RequestParam String email, @RequestParam String password) {
        Store s = storeRepository.findById(id).get();
        s.setName(name);
        s.setEmail(email);
        s.setPassword(password);
        return "Loja de id " + s.getId() + " atualizada no banco de dados";
    }

    @PostMapping(path = "/remove")
    public @ResponseBody
    String removeStore(@RequestParam Integer id) {
        storeRepository.deleteById(id);
        return "Loja excluída do banco de dados";
    }

}
