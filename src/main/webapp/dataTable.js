$(document).ready( function (){
    $('#resultsTable').dataTable( {
        "aoColumnDefs": [
            { "bSortable": false, "aTargets": [ 1, 3 ] }
        ],
        "columns": [
            { "width": "5%" },
            { "width": "10%" },
            { "width": "15%" },
            { "width": "25%" },
            { "width": "10%" },
            { "width": "10%" },
            { "width": "10%" },
            { "width": "5%" },
        ],
        "aaSorting": [],
        "bPaginate": true,
        "searching": true
    } );

    $('#resultsTable tbody').on('click', '.btnadd', function() {
        var id = $(this).closest("tr").find("td:eq(0)").text();
        var mymodal = $('#addMovieModal');

        mymodal.find('#movie_id').val(id);

        $('addMovieModal').modal('show');
    });
} );