package com.example.AirlineManagementSystem.model;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "location")
@Data
@Builder
public class Location {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(notes = "latitude of the location ",name="latitude",required=true,value="22.3423",dataType = "Double")
    @NotNull
    private Double latitude;


    @ApiModelProperty(notes ="longitude of the location",name="longitude",required=true,value="23.4567",dataType = "Double")
    @NotNull
    private Double longitude;





}