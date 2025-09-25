package com.scenarioautomation.demo.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_lamp")
public class Lamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Lamp() {}


    public Integer getId() { return id; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }
}
