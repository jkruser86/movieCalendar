$(document).ready( function () {

    $('#resultsTable').dataTable( {
        "aoColumnDefs": [
            { "bSortable": false, "aTargets": [ 1, 3, 7 ] }
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

    $('#resultsTable tbody').on('click', '.btnadd', function () {
        var id = $(this).closest("tr").find("td:eq(0)").text();
        var poster = $(this).closest("tr").find("img").attr("src");
        var title = $(this).closest("tr").find("td:eq(2)").text();
        var description = $(this).closest("tr").find("td:eq(3)").text();
        var theater = $(this).closest("tr").find("td:eq(4)").text();
        var digital = $(this).closest("tr").find("td:eq(5)").text();
        var physical = $(this).closest("tr").find("td:eq(6)").text();
        var mymodal = $('#addMovieModal');

        mymodal.find('.modal-title').text("When do you want to be reminded and for what release?");
        mymodal.find('#movie_id').val(id);
        $('#movie_poster').attr('src',poster);
        mymodal.find('#movie_title').val(title);
        mymodal.find('#movie_desc').val(description);
        mymodal.find('#movie_theater').val(theater);
        mymodal.find('#movie_digital').val(digital);
        mymodal.find('#movie_physical').val(physical);

        $('#addMovieModal').modal('show');
    });
} );