package com.mrporter.pomangam.client.domains._bases;

import com.mrporter.pomangam._bases.annotation.BooleanToYNConverter;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@EntityListeners(value = AuditingEntityListener.class)
@MappedSuperclass
@Data
public abstract class EntityAuditing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    /**
     * 활성화 여부 (Y/N)
     * default: true(Y)
     * 대문자 필수
     */
    @Column(name = "is_active", nullable = false, length = 1)
    @Convert(converter = BooleanToYNConverter.class)
    private Boolean isActive;

    /**
     * 등록 날짜
     */
    @Column(name = "register_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime registerDate;

    /**
     * 수정 날짜
     */
    @Column(name = "modify_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime modifyDate;
}
