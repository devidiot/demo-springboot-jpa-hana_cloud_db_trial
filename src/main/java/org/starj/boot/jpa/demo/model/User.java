package org.starj.boot.jpa.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "User")
public class User extends BaseModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    // @Column(columnDefinition = "BINARY(16)")
    @Column(columnDefinition = "varchar(36)")
    private String uid;

    @Column(name = "username", nullable = false, columnDefinition = "varchar(100)")
    private String username;

    @Column(columnDefinition = "varchar(100)")
    private String password;

    @Column(name = "first_name", nullable = false, columnDefinition = "varchar(100)")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "varchar(50)")
    private String lastName;

    @Column(name = "day_of_birth", columnDefinition = "varchar(8)")
    private String dayOfBirth;

    @Column(columnDefinition = "boolean default true")
    private Boolean use;

    @Builder
    public User(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
