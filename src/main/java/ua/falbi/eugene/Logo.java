package ua.falbi.eugene;

import javax.persistence.*;


@Entity
@Table(name="Logos")
public class Logo
{
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @Lob
    @Column(length = 2000000000)
    private byte[] body;

    public Logo() {}

    public Logo(String name, byte[] body)
    {
        this.name = name;
        this.body = body;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBody() {
        return body;
    }
    public void setBody(byte[] body) {
        this.body = body;
    }
}
