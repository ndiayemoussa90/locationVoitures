package org.sid.datas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationRequest {

    private Long id;

    private String firstName;
    private String lastName;

    private String address;
    private String phoneNumber;
    private String cni;


    private String username;
    private String password;
    private String passwordConfirmed;

    private String roles;

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id + "," +
                "\"firstName\":\"" + firstName + "\"," +
                "\"lastName\":\"" + lastName + "\"," +
                "\"address\":\"" + address + "\"," +
                "\"phoneNumber\":\"" + phoneNumber + "\"," +
                "\"cni\":\"" + cni + "\"," +
                "\"username\":\""+ username + "\"" +
                "}";
    }

}
