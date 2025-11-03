package com.example.sweet.sweet;
import com.example.sweet.Sweet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.*;
import com.example.sweet.SweetService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

interface SweetRepository {
    Sweet save(Sweet s);
    List<Sweet> findAll();
    Optional<Sweet> findById(String id);
}

class SweetServiceTest {

    @Mock
    private SweetRepository repo;

    @InjectMocks
    private SweetService service;

    private Sweet sampleSweet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleSweet = Sweet.builder()
                .id("1")
                .name("Ladoo")
                .category("Indian")
                .price(BigDecimal.valueOf(50))
                .quantity(10)
                .build();
    }

    @Test
    void testAddSweet() {
        when(repo.save(any(Sweet.class))).thenReturn(sampleSweet);

        Sweet saved = service.addSweet(sampleSweet);

        assertEquals("Ladoo", saved.getName());
        verify(repo, times(1)).save(sampleSweet);
    }

    @Test
    void testGetAll() {
        when(repo.findAll()).thenReturn(List.of(sampleSweet));
        List<Sweet> list = service.getAll();

        assertEquals(1, list.size());
        assertEquals("Ladoo", list.get(0).getName());
    }

    @Test
    void testPurchaseSuccess() {
        when(repo.findById("1")).thenReturn(Optional.of(sampleSweet));
        when(repo.save(any(Sweet.class))).thenReturn(sampleSweet);

        Sweet updated = service.purchase("1");

        assertEquals(9, updated.getQuantity());
    }

    @Test
    void testPurchaseOutOfStock() {
        sampleSweet.setQuantity(0);
        when(repo.findById("1")).thenReturn(Optional.of(sampleSweet));

        assertThrows(RuntimeException.class, () -> service.purchase("1"));
    }

    @Test
    void testRestock() {
        when(repo.findById("1")).thenReturn(Optional.of(sampleSweet));
        when(repo.save(any(Sweet.class))).thenReturn(sampleSweet);

        Sweet updated = service.restock("1", 5);

        assertEquals(15, updated.getQuantity());
    }

    @Test
    void testUpdateSweet() {
        when(repo.findById("1")).thenReturn(Optional.of(sampleSweet));
        when(repo.save(any(Sweet.class))).thenReturn(sampleSweet);

        Sweet newSweet = Sweet.builder()
                .name("Barfi")
                .category("Milk")
                .price(BigDecimal.valueOf(70))
                .quantity(20)
                .build();

        Sweet updated = service.updateSweet("1", newSweet);

        assertEquals("Barfi", updated.getName());
    }
}
