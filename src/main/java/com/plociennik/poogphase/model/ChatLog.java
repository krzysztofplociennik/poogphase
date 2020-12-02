package com.plociennik.poogphase.model;

import javax.persistence.*;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "chatLogs")
public class ChatLog {
    private long id;
    private String signature;
    private List<ChatMessage> log;

    public ChatLog(long id, String signature, List<ChatMessage> log) {
        this.id = id;
        this.signature = signature;
        this.log = log;
    }

    public ChatLog() {
        log = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = ChatMessage.class,
            mappedBy = "chatLog",
            cascade = CascadeType.ALL
    )
    public List<ChatMessage> getLog() {
        return log;
    }

    public void setLog(List<ChatMessage> log) {
        this.log = log;
    }
}
