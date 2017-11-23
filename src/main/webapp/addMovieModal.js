$(document).on("submit", "#add_form", function(event) {
    var $form = $(this);
    var id = document.getElementById("movie_id").value;
    $('#addMovieModal').modal('hide');
    $.post($form.attr("action"), $form.serialize(), function(response) {
        var btnadd = $('#' + id);
        btnadd.removeClass("btn-success").addClass("btn-default");
        btnadd.find('span').toggleClass('glyphicon-plus').toggleClass('glyphicon-check');
        btnadd.attr('disabled','disabled');
    });
    event.preventDefault();
});