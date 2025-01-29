//    Created on : 21/08/2024
//    Author     : Alex Antonio Suárez Sánchez
function MostarMensaje(icono, titulo, mensaje) {
 bootbox.alert({
        title: icono+" "+titulo ,
        message: '<div class="row" style="text-align: justify">' +
                '<div class=col-md-12>' +
                '<h6 class=panel-title>' + mensaje + '</h6>' +
                '</div>' +
                '</div>',
        buttons: {
            ok: {
                label: 'Aceptar',
                className: "btn-primary"
            }
        }
    });
}


function MensajeRedirect(icono, titulo, mensaje, url){
    
bootbox.alert({
    title: icono+" "+titulo ,
    message: mensaje,
    button:{
        ok:{
            label:"Aceptar",
            className:"btn-primary"
        }
    },  
    callback: function (result) {
        window.location = url;
    }
});    
    
}

function ConfirmaEliminacion() {
bootbox.confirm({
    message: "¿Está seguro de elimnar el registro?",
    buttons: {
        confirm: {
            label: 'Sí',
            className: 'btn-primary',
        },
        cancel: {
            label: 'No',
            className: 'btn-danger'
        }
    },  
    callback: function (result) {
        
    }
});    
}