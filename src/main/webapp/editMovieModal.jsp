<div id="editMovieModal" class="modal fade" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Edit Reminder?</h4>
            </div>
            <div class="modal-body" style="padding: 10px;">
                <form class="form-horizontal" action="editSave" method="post"  id="add_form">
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
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-success" >Save </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>