package com.jgj.byl_process.domain.member.entity;

import com.jgj.byl_process.domain.utility.encrypt.EncryptionUtil;
import com.jgj.byl_process.domain.utility.password.PasswordHashConverter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@DiscriminatorValue(Authentication.BASIC_AUTH)
public class BasicAuthentication extends Authentication {

    @Setter
    @Column(nullable = false)
    @Convert(converter = PasswordHashConverter.class)
    private String password;

    public BasicAuthentication (Member member, String authenticationType, String password) {
        super(member, authenticationType);
        this.password = password;
    }

    public boolean isRightPassword(String plainToCheck) {
        return EncryptionUtil.checkValidation(plainToCheck, password);
    }
}