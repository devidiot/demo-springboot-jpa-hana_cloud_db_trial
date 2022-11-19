package org.starj.boot.jpa.demo.model;

import java.lang.reflect.Field;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP", nullable = false)
    private Date updatedAt;

    @Column(name = "created_by", columnDefinition = "varchar(36)")
    private String createdBy;

    @Column(name = "updated_by", columnDefinition = "varchar(36)")
    private String updatedBy;

    public void apply(User source) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(source);
            if (value != null) {
                field.set(this, value);
            }
        }
    }

    public void update(User source) throws IllegalArgumentException, IllegalAccessException {
        Field[] fields = User.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(source);
            field.set(this, value);
        }
    }

}
