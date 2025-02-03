<%-- 
    Document   : botonesMisProyectos
    Created on : 3 feb 2025, 09:50:09
    Author     : romme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="row" align="right" style="margin: 1rem">
    <button id="btnRegistroAvances" class="btn btn-editar btn-sm" title="Registrar Avances"> <i class="fa fa-refresh"></i></button>
    <button id="btnRegistroColaboradores" class="btn btn-agregar btn-sm" title="Registrar Colaboradores"> <i class="fa fa-handshake"></i></button>
    <button id="btnIrBuscaProyecto" class="btn btn-agregar btn-sm" title="Consultar Proyecto"> <i class="fa fa-search"></i></button>
    <button id="btnVerDatosProyecto" class="btn btn-info btn-sm" title="Ver datos de un Proyecto"> <i class="fa fa-exclamation-circle" aria-hidden="true"></i></button>
    <button class="btn btn-info btn-sm" type="button" data-toggle="modal" data-target="#ver_datos" title="Ver Proyecto" id="modal_ver_datos"><i class="fa fa-eye" aria-hidden="true"></i></button>
    <button id="btnBorrarProyecto" class="btn btn-borrar btn-sm" title="Borrar Proyecto"> <i class="fa fa-trash"></i></button>
    <button id="btnCalificacionResidencia" class="btn btn-sm btn-success" type="button" title="Calificacion Proyecto de Residencia"><i class="fa fa-star" aria-hidden="true"></i></button>
</div>