package com.sweetshop.controller;

import com.sweetshop.model.Sweet;
import com.sweetshop.service.SweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/sweets")
public class SweetController {

    @Autowired
    private SweetService sweetService;

    @PostMapping
    public ResponseEntity<Sweet> addSweet(@RequestBody Sweet sweet) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sweetService.addSweet(sweet));
    }

    @GetMapping
    public ResponseEntity<List<Sweet>> getAll() {
        return ResponseEntity.ok(sweetService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Sweet>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal min,
            @RequestParam(required = false) BigDecimal max
    ) {
        return ResponseEntity.ok(sweetService.search(name, category, min, max));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sweet> update(@PathVariable String id, @RequestBody Sweet sweet) {
        return ResponseEntity.ok(sweetService.updateSweet(id, sweet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        sweetService.deleteSweet(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/purchase")
    public ResponseEntity<Sweet> purchase(@PathVariable String id) {
        return ResponseEntity.ok(sweetService.purchase(id));
    }

    @PostMapping("/{id}/restock")
    public ResponseEntity<Sweet> restock(@PathVariable String id, @RequestParam int qty) {
        return ResponseEntity.ok(sweetService.restock(id, qty));
    }
}
