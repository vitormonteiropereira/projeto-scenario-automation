package com.scenarioautomation.demo.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lamp> lamps = new ArrayList<>();

    public Room() {}

    public Room(String name) {
        this.name = name;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Lamp> getLamps() { return lamps; }

    public void addLamp(Lamp lamp) {
        lamps.add(lamp);
        lamp.setRoom(this);
    }

    public void removeLamp(Lamp lamp) {
        lamps.remove(lamp);
        lamp.setRoom(null);
    }
}
