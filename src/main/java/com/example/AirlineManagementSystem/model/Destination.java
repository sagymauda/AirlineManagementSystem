package com.example.AirlineManagementSystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="destination")
@Data
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Destination {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(notes = "Name Of The Destination",name="name",required=true,value="hawaii",dataType = "String")
    @NotBlank
    private String name;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    @ApiModelProperty(notes = "Location Of The Destination",name="location",required=true,value="Bardichevski",dataType = "location")
    @NotNull
    private Location location;

}
