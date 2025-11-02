import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
@Table(name = "sweets")


public class sweet {
    @ID
    private string id;
    private string name;
    private string type;
    private double price;
    private int quantity;
    
    
}
