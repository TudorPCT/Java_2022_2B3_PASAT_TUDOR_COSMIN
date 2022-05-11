package app.entity;

import javax.persistence.*;
import java.io.Serializable;
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    protected long id;
    @Column(name = "name")
    protected String name;

}
