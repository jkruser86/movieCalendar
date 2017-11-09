<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>

<div class="container">
    <form class="form-horizontal" action="saveEdit" method="post" id="reg_form">
        <fieldset>
            <!-- Form Name -->
            <legend> Edit Account </legend>

            <!-- Text Input -->
            <div class="form-group">
                <label class="col-md-4 control-label">User Name</label>
                <div class="col-md-6 inputGroupContainer">
                    <div class="input-group"><span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        <input name="userName" placeholder="User Name" class="form-control" type="text" disabled>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label">E-Mail</label>
                <div class="col-md-6 inputGroupContainer">
                    <div class="input-group"> <span class="input-group-addon"><i class="glyphicon-envelope"></i></span>
                        <input name="email" placeholder="E-Mail Address" class="form-control" type="text">
                    </div>
                </div>
            </div>

            <div class="form-group has-feedback">
                <label for="password" class="col-md-4 control-label">Password</label>
                <div class="col-md-6 inputGroupContainer">
                    <div class="input-group"> <span class="input-group-addon"><i class="glyphicon glyphicon-home"></i></span>
                        <input class="form-control" id="password" type="password" placeholder="password" name="password"
                               data-minLength="5" data-error="some error" required/>
                        <span class="glyphicon form-control-feedback"></span>
                        <span class="help-block with-errors"></span>
                    </div>
                </div>
            </div>

            <div class="form-group has-feedback">
                <label for="confirmPassword" class="col-md-4 control-label">Confirm Password</label>
                <div class="col-md-6 inputGroupContainer">
                    <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-home"></i>
                                </span>
                        <input class="form-control {$borderColor}" id="confirmPassword" type="password" placeholder="Confirm password"
                               name="confirmPassword" data-match="#confirmPassword" data-minLength="5"
                               data-match-error="some error 2"
                               required />
                        <span class="glyphicon form-control-feedback"></span>
                        <span class="help-block with-errors"></span>
                    </div>
                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-warning">Send<span class="glyphicon glyphicon-send"></span></button>
                </div>
            </div>

        </fieldset>
    </form>
</div>

<script type="text/javascript" src="javascript/validateSignup.js"></script>