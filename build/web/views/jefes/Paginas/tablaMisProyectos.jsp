<%-- 
    Document   : tablaMisProyectos
    Created on : 28 ene 2025, 13:18:33
    Author     : romme
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="table" style="/*font-size: 12px;*/" id="divGrpPreparacion">
    <table id="tablaProyectos" class="table table-striped table-bordered tablesorter table-responsive no-footer dtr-inline dataTable table_margen" 
           style="width: 100%; /*font-size: 14px*/">
        <colgroup>
            <col span="1" style="width: 17%;">
            <col span="1" style="width: 17%;">
            <col span="1" style="width: 17%;">
            <col span="1" style="width: 17%;">
            <col span="1" style="width: 16%;">
        </colgroup>
        <thead>
            <tr class="info">
                <th class="bordeTd">Folio</th>
                <th class="bordeTd">Titulo</th>
                <th class="bordeTd">Estado</th>
                <th class="bordeTd">Tipo de Proyecto</th>
                <th class="bordeTd">Situación</th>
                <th class="bordeTd">Progreso</th>
            </tr>    
        </thead>
        <tbody>
            <c:set var="i" value="${0}"></c:set>
            <c:forEach items="${proyectos}" var="item">
                <tr
                    data-id="${i}"
                    id="${item.noFolio}"
                    nombre-proyecto="${item.nombre}"
                    estado-proyecto="${item.estado}"
                    tipo_proyecto="${item.tipo_proyecto}"
                    situacion-proyecto="${item.estado_aprobacion}"
                    progreso-proyecto="${item.porcentaje_avance}"
                    ">
                    <td class="bordeTd justify">${item.noFolio}</td>
                    <td class="bordeTd justify">${item.nombre}</td>
                    <td class="bordeTd justify">${item.estado}</td>
                    <td class="bordeTd justify">${item.tipo_proyecto}</td>
                    <td class="bordeTd justify">${item.estado_aprobacion}</td>
                    <td class="bordeTd justify">% ${item.porcentaje_avance}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
