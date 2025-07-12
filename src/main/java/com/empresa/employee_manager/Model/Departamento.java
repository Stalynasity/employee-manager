@Entity
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    
    @Enumerated(EnumType.STRING)
    private Estado estado;

    // Getters y Setters
    // ...
}

public enum Estado {
    ACTIVO, INACTIVO
}
