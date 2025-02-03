<%-- 
    Document   : Ver_datos_proyecto_modal_proyecto_colab
    Created on : 2 feb 2025, 21:57:10
    Author     : romme
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<div class="row" >
    <div class="col-md-4">
        <div class="form-group">
            <label class="small">Folio Proyecto</label>
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                <input type="text" 
                       class="form-control input-sm" 
                       id="noFolio" 
                       name="noFolio" 
                       autocomplete="off"
                       readonly
                       value="${residencia_Mb.noFolio}">
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="form-group">
            <label class="small">Nombre proyecto</label>
            <div class="input-group">
                <span class="input-group-addon"> <span class="fa fa-file-text"></span> </span>
                <input type="text"
                       id="nombre_proyecto" 
                       name="nombre_proyecto" 
                       class="form-control input-sm" 
                       autocomplete="off"
                       readonly
                       value="${residencia_Mb.nombreProyecto}"> 
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="form-group">
            <label class="small">Nombre del colaborador</label>
            <div class="input-group">
                <span class="input-group-addon"> <span class="fa fa-user"></span> </span>
                <input type="text" 
                       id="nombre_colaborador" 
                       name="nombre_colaborador" 
                       class="form-control input-sm" 
                       autocomplete="off"
                       readonly
                       value="${residencia_Mb.nombreColaborador}"> 
            </div>
        </div>
    </div>
</div>
<div class="row" >
    <div class="col-md-4">
        <div class="form-group">
            <label class="small">Correo Colaborador</label>
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-at"></i></span>
                <input type="text" 
                       class="form-control input-sm" 
                       id="correoColaborador" 
                       name="correoColaborador" 
                       autocomplete="off"
                       readonly
                       value="${residencia_Mb.correoColaborador}">
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="form-group">
            <label class="small">Fecha de inicio</label>
            <div class="input-group">
                <span class="input-group-addon"> <span class="fa fa-calendar"></span> </span>
                <input type="text"
                       id="fecha_inicio" 
                       name="fecha_inicio" 
                       class="form-control input-sm" 
                       autocomplete="off"
                       readonly
                       value="${residencia_Mb.fecha_inicio}"> 
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="form-group">
            <label class="small">Primer Seguimiento</label>
            <div class="input-group">
                <span class="input-group-addon"> <span class="fa fa-calendar"></span> </span>
                <input type="text" 
                       id="fecha_primer_seguimiento" 
                       name="fecha_primer_seguimiento" 
                       class="form-control input-sm" 
                       autocomplete="off"
                       readonly
                       value="${residencia_Mb.primer_seguimiento}"> 
            </div>
        </div>
    </div>
</div>

<div class="row" >
    <div class="col-md-4">
        <div class="form-group">
            <label class="small">Segundo Seguimiento</label>
            <div class="input-group">
                <span class="input-group-addon"> <span class="fa fa-calendar"></span> </span>
                <input type="text" 
                       id="fecha_segundo_seguimiento" 
                       name="fecha_segundo_seguimiento" 
                       class="form-control input-sm" 
                       autocomplete="off"
                       readonly
                       value="${residencia_Mb.segundo_seguimiento}"> 
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="form-group">
            <label class="small">Fecha Final</label>
            <div class="input-group">
                <span class="input-group-addon"> <span class="fa fa-calendar"></span> </span>
                <input type="text" 
                       id="fecha_final" 
                       name="fecha_final" 
                       class="form-control input-sm" 
                       autocomplete="off"
                       readonly
                       value="${residencia_Mb.fecha_fin}"> 
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="form-group">
            <label class="small">Calificación</label>
            <div class="input-group">
                <input type="hidden" id="idNoFolio" name="idNoFolio" value="${requestScope.noFolio}"/>
                <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                <input type="text" 
                       class="form-control input-sm" 
                       id="calificacion" 
                       name="calificacion" 
                       autocomplete="off"
                       readonly
                       value="${residencia_Mb.calificacion}">
            </div>
        </div>                                  
    </div>
</div>                       