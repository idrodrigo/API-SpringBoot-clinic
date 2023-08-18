package com.rho.cli.api.location;

public record LocationDTO(
        String address,
        String district,
        String city,
        String number,
        String complement
) {
}
