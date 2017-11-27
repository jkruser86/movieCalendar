<%@include file="taglib.jsp"%>

<div class="container-fluid">
    <div class="row">
        <h2>Account Page</h2>
        <br/>
        <table id="acctTable" class="display" cellspacing="0" width="50%">
            <tbody>
                <tr>
                    <td width="5%"><span class="glyphicon glyphicon-user"></span></td>
                    <td width="10%">User Name</td>
                    <td width="35%">${user.userName}</td>
                </tr>
                <tr>
                    <td width="5%"><span class="glyphicon glyphicon-envelope"></span></td>
                    <td width="10%">Email</td>
                    <td width="35%">${user.userEmail}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
</p>
<br/>
<FORM ACTION="editAccount" METHOD="POST">
    <TABLE>
        <TR><TH><INPUT TYPE="SUBMIT" VALUE="Edit Account"></TH></TR>
    </TABLE>
</FORM>
<FORM ACTION="reminderList" METHOD="GET">
    <TABLE>
        <TR><TH><INPUT TYPE="SUBMIT" VALUE="Edit Reminders"></TH></TR>
    </TABLE>
</FORM>
<a href="deleteAcct">Click here to delete account</a>