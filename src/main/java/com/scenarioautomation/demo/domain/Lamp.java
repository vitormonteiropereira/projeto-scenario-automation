package com.scenarioautomation.demo.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_lamp")
public class Lamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Lamp() {}

    public Lamp(String name) {
        this.name = name;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
}
