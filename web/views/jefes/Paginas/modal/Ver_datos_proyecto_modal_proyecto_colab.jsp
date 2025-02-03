<%-- 
    Document   : Ver_datos_proyecto_modal_proyecto_colab
    Created on : 2 feb 2025, 21:57:10
    Author     : romme
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-md-6">
        <div class="form-group">
            <label class="small">Nombre del dueño</label>
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                <input type="text" 
                       class="form-control input-sm" 
                       id="nombreduenio" 
                       name="nombreduenio" 
                       autocomplete="off"
                       readonly
                       value="${proyecto_colab_Mb.nombreE}">
            </div>
        </div>
    </div>
    <div class="col-md-6">
        <div class="form-group">
            <label class="small">Correo Colaborador</label>
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-at"></i></span>
                <input type="text" 
                       class="form-control input-sm" 
                       id="correoDuenio_proyecto_colab" 
                       name="correoDuenio_proyecto_colab" 
                       autocomplete="off"
                       readonly
                       value="${proyecto_colab_Mb.correoDuenio}">
            </div>
        </div>
    </div>
</div>
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
                   value="${proyecto_colab_Mb.noFolio}">
        </div>
    </div>
</div>
<div class="col-md-4">
    <div class="form-group">
        <label class="small">Nombre proyecto</label>
        <div class="input-group">
            <span class="input-group-addon"> <span class="fa fa-file-text"></span> </span>
            <input type="text"
                   id="nombre_proyecto_proyecto_colab" 
                   name="nombre_proyecto_proyecto_colab" 
                   class="form-control input-sm" 
                   autocomplete="off"
                   readonly
                   value="${proyecto_colab_Mb.nombreProyecto}"> 
        </div>
    </div>
</div>
<div class="col-md-4">
    <div class="form-group">
        <label class="small">Nombre del colaborador</label>
        <div class="input-group">
            <span class="input-group-addon"> <span class="fa fa-user"></span> </span>
            <select id="nombre_colaborador" name="nombre_colaborador" class="form-control input-sm">
                <c:forEach var="colaborador" items="${colaboradores}">
                    <option value="${colaborador.nombre}" data-correo="${colaborador.correo}">
                        ${colaborador.nombre}
                    </option>
                </c:forEach>
                <c:if test="${empty colaboradores}">
                    <option value="">Aún no registrado</option>
                </c:if>
            </select>

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
                       id="correoColaborador_proyecto_colab" 
                       name="correoColaborador_proyecto_colab" 
                       autocomplete="off"
                       readonly
                       value="${proyecto_colab_Mb.correoColaborador}">
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="form-group">
            <label class="small">Fecha de aprobacion</label>
            <div class="input-group">
                <span class="input-group-addon"> <span class="fa fa-calendar"></span> </span>
                <input type="text"
                       id="fecha_aprobacion_proyecto_colab" 
                       name="fecha_aprobacion_proyecto_colab" 
                       class="form-control input-sm" 
                       autocomplete="off"
                       readonly
                       value="${proyecto_colab_Mb.fecha_aprobacion}"> 
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="form-group">
            <label class="small">Fecha de liberacion</label>
            <div class="input-group">
                <span class="input-group-addon"> <span class="fa fa-calendar"></span> </span>
                <input type="text" 
                       id="fecha_primer_seguimiento_proyecto_colab" 
                       name="fecha_primer_seguimiento_proyecto_colab" 
                       class="form-control input-sm" 
                       autocomplete="off"
                       readonly
                       value="${proyecto_colab_Mb.fecha_liberacion}"> 
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('#nombre_colaborador').change(function () {
            var correo = $(this).find(':selected').data('correo');
            $('#correoColaborador_proyecto_colab').val(correo);
        });
    });
</script>