package com.example.demo.model;

import com.example.demo.entity.EquipmentEntity;
import com.example.demo.entity.ParticipantEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull(message = "Null Field : name" )
    @ApiModelProperty(example = "Room name")
    private String name;

    @NotNull(message = "Null Field : date" )
    @ApiModelProperty(example = "yyyy-mm-dd")
    private LocalDate date;

    @NotNull(message = "Null Field : starHour" )
    @ApiModelProperty(example = "hh:mm:ss")
    private LocalTime startHour;

    @NotNull(message = "Null Field : endHour" )
    @ApiModelProperty(example = "hh:mm:ss")
    private LocalTime endHour;

    @NotNull(message = "Null Field: equipment")
    private Equipment equipment;

    @NotNull(message = "Null Field: ListParticipants")
    private List<Participant> participants;


}
