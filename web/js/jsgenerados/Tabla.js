$(document).ready(function () {

    $('#pageLoader').hide();
    proyectos = $('#proyectos').DataTable({
        dom: 'Bfrtip',
        "language": {
            "paginate": {
                "next": "Siguiente",
                "previous": "Anterior"
            },
            "search": "Buscar: ",
            "zeroRecords": "No se encontraron coincidencias"
        },
        "responsive": {
            details: {
                display: $.fn.dataTable.Responsive.display.childRowImmediate,
                type: ''
            }
        },
        "destroy": true

    });
    proyectos1 = $('#proyectos1').DataTable({
        dom: 'Bfrtip',
        "language": {
            "paginate": {
                "next": "Siguiente",
                "previous": "Anterior"
            },
            "search": "Buscar: ",
            "zeroRecords": "No se encontraron coincidencias"
        },
        "responsive": {
            details: {
                display: $.fn.dataTable.Responsive.display.childRowImmediate,
                type: ''
            }
        },
        "destroy": true

    });
    proyectos2 = $('#dataTable').DataTable({
        dom: 'Bfrtip',
        "language": {
            "paginate": {
                "next": "Siguiente",
                "previous": "Anterior"
            },
            "search": "Buscar: ",
            "zeroRecords": "No se encontraron coincidencias"
        },
        "responsive": {
            details: {
                display: $.fn.dataTable.Responsive.display.childRowImmediate,
                type: ''
            }
        },
        "destroy": true

    });
    proyect3 = $('#hist').DataTable({
        dom: 'Bfrtip',
        "language": {
            "paginate": {
                "next": "Siguiente",
                "previous": "Anterior"
            },
            "search": "Buscar: ",
            "zeroRecords": "No se encontraron coincidencias"
        },
        "responsive": {
            details: {
                display: $.fn.dataTable.Responsive.display.childRowImmediate,
                type: ''
            }
        },
        "destroy": true

    });
    proyect4 = $('#dataTableCeder').DataTable({
        dom: 'Bfrtip',
        "language": {
            "paginate": {
                "next": "Siguiente",
                "previous": "Anterior"
            },
            "search": "Buscar: ",
            "zeroRecords": "No se encontraron coincidencias"
        },
        "responsive": {
            details: {
                display: $.fn.dataTable.Responsive.display.childRowImmediate,
                type: ''
            }
        },
        "destroy": true

    });
    proyect5 = $('#proyectosD').DataTable({
        dom: 'Bfrtip',
        "language": {
            "paginate": {
                "next": "Siguiente",
                "previous": "Anterior"
            },
            "search": "Buscar: ",
            "zeroRecords": "No se encontraron coincidencias"
        },
        "responsive": {
            details: {
                display: $.fn.dataTable.Responsive.display.childRowImmediate,
                type: ''
            }
        },
        "destroy": true

    });
    
        proyect6 = $('#equipoA').DataTable({
        dom: 'Bfrtip',
        "language": {
            "paginate": {
                "next": "Siguiente",
                "previous": "Anterior"
            },
            "search": "Buscar: ",
            "zeroRecords": "No se encontraron coincidencias"
        },
        "responsive": {
            details: {
                display: $.fn.dataTable.Responsive.display.childRowImmediate,
                type: ''
            }
        },
        "destroy": true

    });
});