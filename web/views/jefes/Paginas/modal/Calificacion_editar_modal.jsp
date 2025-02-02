
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link href="/CDN-ITT/css/botones.estandarITT.css" rel="stylesheet">
<link href="/CDN-ITT/css/font-awesome.estandarITT.css" rel="stylesheet">
<link href="/CDN-ITT/css/base.estandarITT.css" rel="stylesheet">
<link href="/CDN-ITT/css/general.estandarITT.css" rel="stylesheet">

<div class="row">
    <div class="col-md-5">
        <div class="form-group">
            <label class="small">Calificación</label>
            <div class="input-group">
                <input type="hidden" id="idNoFolio" name="idNoFolio" value="${requestScope.noFolio}"/>
                <span class="input-group-addon"><i class="fa fa-hashtag"></i></span>
                <input type="text" 
                       class="form-control input-sm" 
                       placeholder="00.00" 
                       maxlength="5" 
                       id="porcentaje" 
                       name="porcentaje" 
                       required 
                       autocomplete="off"
                       oninput="validarInputs(porcentaje)">
            </div>
        </div>                                  
    </div>
</div>