package org.sid.datas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;

    private String address;
    private String phoneNumber;
    private String cni;


    private String username;

    public UserResponse(
        Long id, String firstName, String lastName,
        String address, String phoneNumber, String cni
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cni = cni;
    }

    public UserResponse(
        Long id, String firstName, String lastName, String address,
        String phoneNumber, String cni, String username
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cni = cni;
        this.username = username;
    }

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
