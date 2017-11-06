$(document).ready( function (){
    $('#resultsTable').dataTable( {
        "aoColumnDefs": [
            { "bSortable": false, "aTargets": [ 1, 4, 5 ] }
        ],
        "columns": [
            { "width": "5%" },
            { "width": "20%" },
            { "width": "20%" },
            { "width": "10%" },
            { "width": "10%" },
            { "width": "10%" },
            { "width": "10%" },
            { "width": "5%" },
        ],
        "aaSorting": [],
        "bPaginate": true,
        "searching": true
    } );
});