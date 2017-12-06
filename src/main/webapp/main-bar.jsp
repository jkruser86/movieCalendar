<%@include file="taglib.jsp"%>
<div id="header">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/movieCalendar">Movie Calendar</a>
            </div>

            <form class="navbar-form navbar-left" method="GET" action="search-results">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search Movie" id="searchTerm" name="term">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>

            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal == null}">
                        <li><a href="create-acct"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                        <li><a href="account"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="account"><span class="glyphicon glyphicon-user"></span> Account</a></li>
                        <li><a href="logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </nav>
</div>