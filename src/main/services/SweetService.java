
import com.sweetshop.model.Sweet;
import com.sweetshop.repository.SweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SweetService {
    @Autowired
    private SweetRepository repo;

    public Sweet addSweet(Sweet sweet) {
        return repo.save(sweet);
    }

    public List<Sweet> getAll() {
        return repo.findAll();
    }

    public List<Sweet> search(String name, String category, BigDecimal min, BigDecimal max) {
        if (name != null) return repo.findByNameContainingIgnoreCase(name);
        if (category != null) return repo.findByCategoryIgnoreCase(category);
        if (min != null && max != null) return repo.findByPriceBetween(min, max);
        return repo.findAll();
    }

    public Sweet updateSweet(String id, Sweet newData) {
        Sweet s = repo.findById(id).orElseThrow(() -> new RuntimeException("Sweet not found"));
        s.setName(newData.getName());
        s.setCategory(newData.getCategory());
        s.setPrice(newData.getPrice());
        s.setQuantity(newData.getQuantity());
        return repo.save(s);
    }

    public void deleteSweet(String id) {
        repo.deleteById(id);
    }

    public Sweet purchase(String id) {
        Sweet s = repo.findById(id).orElseThrow(() -> new RuntimeException("Sweet not found"));
        if (s.getQuantity() <= 0) throw new RuntimeException("Out of stock");
        s.setQuantity(s.getQuantity() - 1);
        return repo.save(s);
    }

    public Sweet restock(String id, int qty) {
        Sweet s = repo.findById(id).orElseThrow(() -> new RuntimeException("Sweet not found"));
        s.setQuantity(s.getQuantity() + qty);
        return repo.save(s);
    }
}
