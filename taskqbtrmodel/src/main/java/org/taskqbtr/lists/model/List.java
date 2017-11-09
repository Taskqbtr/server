package org.taskqbtr.lists.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_lists")
public class List  implements Serializable {

    private static final long serialVersionUID = 8002164891859242822L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    public List() {
    }

    public List(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        List list = (List) o;

        if (id != null ? !id.equals(list.id) : list.id != null) return false;
        return name != null ? name.equals(list.name) : list.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "List{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
