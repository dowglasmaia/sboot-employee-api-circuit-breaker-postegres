package com.dmaia.software.employeeapi.domain;

import com.dmaia.software.employeeapi.exeptions.UnprocessableEntityExeption;
import com.dmaia.software.employeeapi.utils.ValidationCPF;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;


@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee extends ValidationCPF {

    @Id
    private String id;
    private String name;
    private String enroll;
    private String cpf;
    private String email;
    private OffsetDateTime dateCrated;
    private OffsetDateTime dateUpdate;

    private String addressZipCode;

    public Employee(String id,
                    String name,
                    String enroll,
                    String cpf,
                    String email,
                    OffsetDateTime dateCrated,
                    OffsetDateTime dateUpdate,
                    String addressZipCode){
        this.id = id;
        this.name = name;
        this.enroll = enroll;
        this.cpf = this.setCpf(cpf);
        this.email = email;
        this.dateCrated = dateCrated;
        this.dateUpdate = dateUpdate;
        this.addressZipCode = addressZipCode;
    }

    public String setCpf(String cpf){
        if (isCpf(cpf)) {
            return cpf;
        }
        throw new UnprocessableEntityExeption("CPF invalid", HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
