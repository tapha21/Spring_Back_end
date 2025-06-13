package com.ges_abs.data.models.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Batiment {
    BAOBAB("Baobab", "https://www.google.com/maps/search/?api=1&query=Campus+ISM+Projet,+MGRR%2B6HW,+Av.+Cheikh+Anta+Diop,+Dakar"),
    MADIBA("Madiba", "https://www.google.com/maps/place/ISM+Madiba+Leadership+Institut/@14.6903712,-17.4596438,802m/data=!3m2!1e3!4b1!4m6!3m5!1s0xec173918ce3de55:0x99499db663841d6f!8m2!3d14.6903675!4d-17.4577954!16s%2Fg%2F11ff0sxf9y?entry=ttu&g_ep=EgoyMDI1MDYwOS4xIKXMDSoASAFQAw%3D%3D");

    private final String nom;
    private final String urlLocalisation;
}
