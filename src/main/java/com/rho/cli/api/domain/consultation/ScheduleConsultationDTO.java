package com.rho.cli.api.domain.consultation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rho.cli.api.domain.doctor.Specialization;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ScheduleConsultationDTO(
        @NotNull
        Long patientId,
        Long doctorId,
        @NotNull
        @Future
//        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
//                "date": "2023-12-10T10:30"
        LocalDateTime date,
        Specialization specialization
) {

}

//public record DatosCompra(
//        @JsonAlias({"producto_id", "id_producto"}) Long idProducto,
//        @JsonAlias({"fecha_compra", "fecha"}) LocalDate fechaCompra
//){}
