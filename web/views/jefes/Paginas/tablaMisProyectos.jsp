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
            <col span="1" style="width: 20%;">
            <col span="1" style="width: 20%;">
            <col span="1" style="width: 20%;">
            <col span="1" style="width: 20%;">
            <col span="1" style="width: 20%;">
        </colgroup>
        <thead>
            <tr class="info">
                <th class="bordeTd">Folio</th>
                <th class="bordeTd">Titulo</th>
                <th class="bordeTd">Progreso</th>
                <th class="bordeTd">Estado</th>
                <th class="bordeTd">Aprobacion</th>
            </tr>    
        </thead>
        <tbody>
            <c:set var="i" value="${0}"></c:set>
            <c:forEach items="${proyectos}" var="item">
                <tr
                    data-id="${i}"
                    id="${item.noFolio}"
                    nombre-proyecto="${item.nombre}"
                    progreso-proyecto="${item.id_duenio}"
                    estado-proyecto="${item.estado}"
                    aprobacion-proyecto="${item.id_departamento_tab}"
                    ">
                    <td class="bordeTd justify">${item.noFolio}</td>
                    <td class="bordeTd justify">${item.nombre}</td>
                    <td class="bordeTd justify">${item.id_duenio}</td>
                    <td class="bordeTd justify">${item.estado}</td>
                    <td class="bordeTd justify">${item.id_departamento_tab}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<div class="row" align="right" style="margin: 1rem">
    <button id="btnRegistroAvances"  class="btn btn-editar btn-sm" title="Registrar Avances"> <i class="fa fa-refresh"></i></button>
    <button id="btnRegistroColaboradores"  class="btn btn-agregar btn-sm" title="Registrar Colaboradores"> <i class="fa fa-handshake"></i></button>
    <button id="btnIrBuscaProyecto"  class="btn btn-info btn-sm" title="Consultar Proyecto"> <i class="fa fa-search"></i></button>
    <button id="btnBorrarProyecto"    class="btn btn-borrar btn-sm" title="Borrar Proyecto"> <i class="fa fa-trash"></i></button>
</div>