    package com.ges_abs.mobile.dto.response;

    import com.ges_abs.data.models.enumeration.Etat;
    import com.ges_abs.data.models.enumeration.Type;
    import lombok.Builder;
    import lombok.Data;

    import java.time.LocalDate;
    import java.time.LocalTime;
    @Builder
    @Data
    public class AbsenceResponseDto {
        private String id;
        private LocalDate dateDebut;
        private LocalTime heureDebut;
        private LocalTime heureFin;
        private String justification;
        //    private String justificatifImage;
        private Etat etat;
        private Type type;
        private EtudiantSimpleResponseDto etudiant;
    //    private SessionWebResponseDto session;
    }
