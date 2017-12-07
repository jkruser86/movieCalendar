$(document).on("submit", "#add_form", function(event) {
    var $form = $(this);
    var id = document.getElementById("movie_id").value;
    $('#editMovieModal').modal('hide');
    $('#deleteMovieModal').modal('hide');
    $.post($form.attr("action"), $form.serialize(), function(response) {
        var btnadd = $('#' + id);
        btnadd.removeClass("btn-success").addClass("btn-default");
        btnadd.find('span').toggleClass('glyphicon-edit').toggleClass('glyphicon-check');
        btnadd.attr('disabled','disabled');
    });
    $('#deleteMovieModal').on('hidden.bs.modal', function () {
        location.reload();
    });
    event.preventDefault();
});