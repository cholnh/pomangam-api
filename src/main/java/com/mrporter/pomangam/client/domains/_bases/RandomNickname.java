package com.mrporter.pomangam.client.domains._bases;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Table(name = "random_nickname_tbl")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@DynamicUpdate
public class RandomNickname implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    /**
     * 첫번째 글자
     */
    @NotBlank
    private String first;

    /**
     * 두번째 글자
     */
    @NotBlank
    private String second;

    @Builder
    public RandomNickname(@NotBlank String first, @NotBlank String second) {
        this.first = first;
        this.second = second;
    }
}
