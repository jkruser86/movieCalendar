<%@include file="../taglib.jsp"%>

<!--<script type="text/javascript" src="javascript/dataTable.js"></script>-->

<script type="text/javascript" class="init">
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
</script>

<script>
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
</script>

<div class="container-fluid">
    <div class="row">
        <h4>Search Results</h4>
        <table id="resultsTable" class="display" cellspacing="0" width="100%">
            <thead>
                <th>Movie ID</th>
                <th>Poster</th>
                <th>Title</th>
                <th>Description</th>
                <th>Theatrical Release Date</th>
                <th>Digital Release Date</th>
                <th>Physical Release Date</th>
                <th></th>
            </thead>
            <tbody>
                <c:forEach var="movie" items="${movies}">
                    <tr>
                        <td>${movie.id}</td>
                        <td><img src="https://image.tmdb.org/t/p/w185_and_h278_bestv2${movie.image}"></td>
                        <td>${movie.title}</td>
                        <td>${movie.description}</td>
                        <c:choose>
                            <c:when test="${empty movie.theatricalRelease}">
                                <td>No Info</td>
                            </c:when>
                            <c:otherwise>
                                <td>${movie.theatricalRelease}</td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${empty movie.digitalRelease}">
                                <td>No Info</td>
                            </c:when>
                            <c:otherwise>
                                <td>${movie.digitalRelease}</td>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${empty movie.physicalRelease}">
                                <td>No Info</td>
                            </c:when>
                            <c:otherwise>
                                <td>${movie.physicalRelease}</td>
                            </c:otherwise>
                        </c:choose>
                        <td>
                            <button type="button" id="${movie.id}" class="btnadd btn btn-xs btn-success"><span class="glyphicon glyphicon-plus"></span></button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div id="addMovieModal" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Set Reminder?</h4>
                </div>
                <div class="modal-body" style="padding: 10px;">
                    <form class="form-horizontal" action="movieSave" method="post"  id="add_form">
                        <input type="HIDDEN" name="poster_path" id="poster_path">
                        <div class="row">
                            <label class="col-md-2 control-label">ID</label>
                            <div class="col-lg-2 col-md-2 col-sm-2" style="padding-bottom: 10px;" style="padding-top: 10px;">
                                <input id="movie_id" name="movie_id" class="form-control"  type="text" readonly>
                            </div>
                        </div>
                        <div class="row">
                            <label class="col-md-2 control-label">Poster</label>
                            <div class="col-lg-2 col-md-2 col-sm-2" style="padding-bottom: 10px;" style="padding-top: 10px;">
                                <img src="" name="movie_poster" id="movie_poster">
                            </div>
                        </div>
                        <div class="row">
                            <label class="col-md-2 control-label">Title</label>
                            <div class="col-lg-9 col-md-9 col-sm-9" style="padding-bottom: 10px;" style="padding-top: 10px;">
                                <input id="movie_title" name="movie_title" class="form-control"  type="text" readonly>
                            </div>
                        </div>
                        <div class="row">
                            <label class="col-md-2 control-label">Description</label>
                            <div class="col-lg-9 col-md-9 col-sm-9" style="padding-bottom: 10px;" style="padding-top: 10px;">
                                <textarea style="resize:vertical;" class="form-control" rows="8" name="movie_desc" id="movie_desc" readonly></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <label class="col-md-2 control-label">Theatrical Date</label>
                            <div class="col-lg-2 col-md-2 col-sm-2" style="padding-bottom: 10px;" style="padding-top: 10px;">
                                <input id="movie_theater" name="movie_theater" class="form-control"  type="text" readonly>
                            </div>
                            <label class="col-md-2 control-label">Enter Number</label>
                            <div class="col-lg-2 col-md-2 col-sm-2" style="padding-bottom: 10px;" style="padding-top: 10px;">
                                <input id="theater_number" name="theater_number" class="form-control" type="number" min="0" max="12">
                            </div>
                            <label class="col-md-2 control-label">Timeframe</label>
                            <div class="col-lg-2 col-md-2 col-sm-2" style="padding-bottom: 10px;" style="padding-top: 10px;">
                                <select id="theater_timeframe" name="theater_timeframe" class="form-control">
                                    <option>Day</option>
                                    <option>Week</option>
                                    <option>Month</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <label class="col-md-2 control-label">Digital Date</label>
                            <div class="col-lg-2 col-md-2 col-sm-2" style="padding-bottom: 10px;" style="padding-top: 10px;">
                                <input id="movie_digital" name ="movie_digital" class="form-control"  type="text" readonly>
                            </div>
                            <label class="col-md-2 control-label">Enter Number</label>
                            <div class="col-lg-2 col-md-2 col-sm-2" style="padding-bottom: 10px;" style="padding-top: 10px;">
                                <input id="digital_number" name="digital_number" class="form-control" type="number" min="0" max="12">
                            </div>
                            <label class="col-md-2 control-label">Timeframe</label>
                            <div class="col-lg-2 col-md-2 col-sm-2" style="padding-bottom: 10px;" style="padding-top: 10px;">
                                <select id="digital_timeframe" name="digital_timeframe" class="form-control">
                                    <option>Day</option>
                                    <option>Week</option>
                                    <option>Month</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <label class="col-md-2 control-label">Physical Date</label>
                            <div class="col-lg-2 col-md-2 col-sm-2" style="padding-bottom: 10px;" style="padding-top: 10px;">
                                <input id="movie_physical" name ="movie_physical" class="form-control"  type="text" readonly>
                            </div>
                            <label class="col-md-2 control-label">Enter Number</label>
                            <div class="col-lg-2 col-md-2 col-sm-2" style="padding-bottom: 10px;" style="padding-top: 10px;">
                                <input id="physical_number" name="physical_number" class="form-control" type="number" min="0" max="12">
                            </div>
                            <label class="col-md-2 control-label">Timeframe</label>
                            <div class="col-lg-2 col-md-2 col-sm-2" style="padding-bottom: 10px;" style="padding-top: 10px;">
                                <select id="physical_timeframe" name="physical_timeframe" class="form-control">
                                    <option>Day</option>
                                    <option>Week</option>
                                    <option>Month</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-2 col-lg-offset-2 col-md-2 col-md-offset-2 col-sm-2 col-sm-offset-2" style="padding-bottom: 10px;">
                                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                                <button type="submit" class="btn btn-success" >Yes </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>