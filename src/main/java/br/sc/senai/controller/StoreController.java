package br.sc.senai.controller;

import br.sc.senai.model.Store;
import br.sc.senai.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(path = "/sa")
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;

    @GetMapping(path = "/stores")
    public @ResponseBody ResponseEntity<Iterable<Store>> getAllStores() {

        try {
            Iterable<Store> stores = storeRepository.findAll();
            if (((Collection<?>) stores).size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(stores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/stores")
    public @ResponseBody ResponseEntity<Store> addNewStore(@RequestBody Store store) {
        try {
            Store newStore = storeRepository.save(store);
            return new ResponseEntity<>(newStore, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/stores/{id}")
    public @ResponseBody ResponseEntity<Store> updateStore(@PathVariable("id") Integer id, @RequestBody Store store) {

        Optional<Store> storeData = storeRepository.findById(id);

        if (storeData.isPresent()) {
            Store updatedStore = storeData.get();
            updatedStore.setName(store.getName());
            updatedStore.setEmail(store.getEmail());
            updatedStore.setPassword(store.getPassword());
            return new ResponseEntity<>(storeRepository.save(updatedStore), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/stores/{id}")
    public @ResponseBody ResponseEntity<HttpStatus> removeStore(@PathVariable("id") Integer id) {

        try {
            storeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
