package com.rho.cli.api.domain.location;

import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private String address;
    private String district;
    private String city;
    private String number;
    private String complement;
    public Location(@Valid LocationDTO location) {
        this.address = location.address();
        this.district = location.district();
        this.city = location.city();
        this.number = location.number();
        this.complement = location.complement();
    }

    public Location updateData(Location location) {
        this.address = location.getAddress();
        this.district = location.getDistrict();
        this.city = location.getCity();
        this.number = location.getNumber();
        if(location.getComplement() != null){
            this.complement = location.getComplement();
        }
        return this;
    }
}
