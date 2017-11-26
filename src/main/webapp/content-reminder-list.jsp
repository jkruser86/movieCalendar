<%@include file="taglib.jsp"%>

<script type="text/javascript" src="dataTable.js"></script>
<script type="text/javascript" src="addMovieModal.js"></script>

<div class="container-fluid">
    <div class="row">
        <h2>All Saved Movies</h2>
        <br/>
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
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal == null}">
                            <td>
                                <button type="button" id="${movie.id}" class="btndel btn btn-xs btn-default disabled"><span class="glyphicon glyphicon-remove"></span></button>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <button type="button" id="${movie.id}" class="btnadd btn btn-xs btn-success"><span class="glyphicon glyphicon-plus"></span></button>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <c:import url="addMovieModal.jsp" />
</div>