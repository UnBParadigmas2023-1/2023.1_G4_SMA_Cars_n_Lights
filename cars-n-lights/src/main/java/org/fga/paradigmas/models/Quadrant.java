package org.fga.paradigmas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"identification"})
public class Quadrant {

    private QuadrantIdentification identification;
    private Integer x;
    private Integer y;

}
