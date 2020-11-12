package br.com.location.indoor.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AREA")
public class Area extends BaseEntity {

    private String name;
    private Short pointsInsideTheRoom;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Connection> connections = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getPointsInsideTheRoom() {
        return pointsInsideTheRoom;
    }

    public void setPointsInsideTheRoom(Short pointsInsideTheRoom) {
        this.pointsInsideTheRoom = pointsInsideTheRoom;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "Area [name=" + name + "]";
    }

}
