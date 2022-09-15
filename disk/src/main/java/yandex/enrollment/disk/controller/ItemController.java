package yandex.enrollment.disk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yandex.enrollment.disk.models.Item;
import yandex.enrollment.disk.service.ItemService;

import javax.ws.rs.QueryParam;
import java.util.Date;


@RestController
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @PostMapping(value = "/items")
    public ResponseEntity<?> create(@RequestBody ObjectHolder objectHolder){
        boolean created = itemService.create(objectHolder.getItems(), objectHolder.getUpdateDate());
        return created
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(new CustomMessage(400, "Validation Failed"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/items/{id}")
    public ResponseEntity<?> read(@PathVariable(name = "id") String id) {
        final Item got = itemService.get(id);

        return got != null
                ? new ResponseEntity<Item>(got, HttpStatus.OK)
                : new ResponseEntity<>(new CustomMessage(400, "Validation Failed"), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/items/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id, @QueryParam("date") Date date) {
        final boolean deleted = itemService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(new CustomMessage(400, "Validation Failed"), HttpStatus.BAD_REQUEST);
    }
}
