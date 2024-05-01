package com.awin.cod.challenge.imperative.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "clicks")
@AllArgsConstructor
@Data
public class Click {
    @Id
    private String id;
    @Indexed
    private String userId;
    @Indexed
    private String publisherId;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;
    private String description;

    public Click() {

    }
}
