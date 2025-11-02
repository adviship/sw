import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "users")
public class user {
    @ID
    private string id;
    private string name;
    private string email;
    private string password;
    private <roles> roles;
    
}
    
