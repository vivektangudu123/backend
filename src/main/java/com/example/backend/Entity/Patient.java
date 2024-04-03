package com.example.backend.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "patients")
public class Patient {

        @Id
        private String patientId;

        private String patientName;

        @UniqueElements
        private String email;

        private String gender;

        private String bloodGroup;

        private String dateOfBirth;

        @Pattern(regexp="[0-9]{10}")
        @Indexed(unique = true)
        private String phoneNumber;
        private String status;


        // Setters and Getters
        public String getstatus() {
                return status;
        }
        public void setstatus(String  status) {
                this.status=status;
        }
        public String getPatientId() {
                return patientId;
        }

        public void setPatientId(String patientId) {
                this.patientId = patientId;
        }

        public String getPatientName() {
                return patientName;
        }

        public void setPatientName(String patientName) {
                this.patientName = patientName;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getGender() {
                return gender;
        }

        public void setGender(String gender) {
                this.gender = gender;
        }

        public String getBloodGroup() {
                return bloodGroup;
        }

        public void setBloodGroup(String bloodGroup) {
                this.bloodGroup = bloodGroup;
        }

        public String getDateOfBirth() {
                return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
                this.dateOfBirth = dateOfBirth;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }
}
