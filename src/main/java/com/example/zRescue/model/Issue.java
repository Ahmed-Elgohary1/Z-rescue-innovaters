package com.example.zRescue.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Embeddable
public class Issue {

    private double latitude;
    private double longitude;

    private Integer timeStamp;
    @Lob
    @Column(name = "imagedata")
    private byte[] imageData;
}
