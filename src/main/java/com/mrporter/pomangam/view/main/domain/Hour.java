package com.mrporter.pomangam.view.main.domain;

import lombok.Data;

import java.util.List;

@Data
public class Hour {
    Integer hour;
    List<Integer> minutes;
}
