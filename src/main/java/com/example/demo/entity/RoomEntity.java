package com.example.demo.entity;


import com.example.demo.model.Equipment;
import com.example.demo.model.Participant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="meetingroom")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Null Field : name" )
    @Column(name = "name")
    private String name;

    @NotNull(message = "Null Field : date" )
    @Column(name = "date")
    private LocalDate date;

    @NotNull(message = "Null Field : starHour" )
    @Column(name = "startHour", nullable = false)
    private LocalTime startHour;

    @NotNull(message = "Null Field : endHour" )
    @Column(name = "endHour", nullable = false)
    private LocalTime endHour;

    @ManyToMany
    @JoinTable(name="room_Participants", joinColumns = { @JoinColumn(name="meetingroom_id") }, inverseJoinColumns = { @JoinColumn(name="participants_id")})
    private List<ParticipantEntity> participants;

    @ManyToOne
    @JoinColumn(name = "equipmentId")
    private EquipmentEntity equipment;

    public RoomEntity(Long id, String name, LocalDate date, LocalTime startHour, LocalTime endHour, Equipment equipment, List<Participant> participants) {
    }
}
