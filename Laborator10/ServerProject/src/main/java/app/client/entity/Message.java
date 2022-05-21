package app.client.entity;

import javax.persistence.*;
@NamedQueries({
        @NamedQuery(name = "Message.findById",
                query = "select e from Message e where e.id = ?1"),
        @NamedQuery(name = "Message.findByReceiver",
                query = "select e from Message e where e.receiver = ?1")
})
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(generator="my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="MY_SEQ", allocationSize=1)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "sender",nullable = false)
    private Client sender;

    @ManyToOne
    @JoinColumn(name = "receiver",nullable = false)
    private Client receiver;

    @Column(name = "text")
    protected String text;
    @Column(name = "seen")
    protected boolean seen;

    public Message(){}
    public Message(Client sender, Client receiver, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.seen = false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Client getSender() {
        return sender;
    }

    public void setSender(Client sender) {
        this.sender = sender;
    }

    public Client getReceiver() {
        return receiver;
    }

    public void setReceiver(Client receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", text='" + text + '\'' +
                ", seen=" + seen +
                '}';
    }
}
