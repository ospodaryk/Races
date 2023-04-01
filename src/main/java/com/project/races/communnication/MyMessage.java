package com.project.races.communnication;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyMessage {

    private String id;
    private String message;

    @Override
    public String toString() {
        return "MyMessage{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

//
//@Data
//@Entity(name = "MyCustomObject")
//@Table(name = "myCustomObject")
//@AllArgsConstructor
//@NoArgsConstructor
//public class MyCustomObject {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id = 0L;
//
//    @NotBlank
//    @Column(name = "message")
//    private String message;
//
//    @NotBlank
//    @Column(name = "number")
//    private Long number;
//}