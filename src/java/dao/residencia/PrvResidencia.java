/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dao.residencia;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author mauro
 */
public class PrvResidencia
{

    /**
     * @param args the command line arguments
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        String fechaInicio = "31/01/2025";
        String[] fechas = obtener3Fechas(fechaInicio);

        System.out.println("Primera fecha: " + fechas[0]);
        System.out.println("Segunda fecha: " + fechas[1]);
        System.out.println("Fecha final: " + fechas[2]);
    }

    private static String[] obtener3Fechas(String fechaInicio) {
        String[] fechas = new String[3];
        LocalDate fecha = LocalDate.parse(fechaInicio, FORMATTER);

        for (int i = 0; i < 3; i++) {
            fecha = sumar30Dias(fecha);
            fechas[i] = fecha.format(FORMATTER);
        }

        return fechas;
    }

    private static LocalDate sumar30Dias(LocalDate fecha) {
        do {
            fecha = fecha.plusDays(30);

            // Si la fecha cae en julio, avanzamos al 1 de agosto
            if (fecha.getMonthValue() == 7) {
                fecha = LocalDate.of(fecha.getYear(), 8, 1);
            }

            // Si la fecha cae en los últimos 15 días de diciembre, avanzamos al 1 de enero del siguiente año
            if (fecha.getMonthValue() == 12 && fecha.getDayOfMonth() > 16) {
                fecha = LocalDate.of(fecha.getYear() + 1, 1, 1);
            }
        } while (fecha.getMonthValue() == 7 || (fecha.getMonthValue() == 12 && fecha.getDayOfMonth() > 16));

        return fecha;
    }

}
