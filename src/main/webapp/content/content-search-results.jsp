<%@include file="../taglib.jsp"%>

<!--<script type="text/javascript" src="javascript/dataTable.js"></script>-->
<script type="text/javascript" src="init">
    $(document).ready( function (){
        $('#resultsTable').dataTable( {
            "aoColumnDefs": [
                { "bSortable": false, "aTargets": [ 1, 4, 5 ] }
            ],
            "columns": [
                { "width": "5%" },
                { "width": "20%" },
                { "width": "20%" },
                { "width": "10%" },
                { "width": "10%" },
                { "width": "10%" },
                { "width": "10%" },
                { "width": "5%" },
            ],
            "aaSorting": [],
            "bPaginate": true,
            "searching": true
        } );
    } );
</script>
<h3>Search Results</h3>
<br/>
<table class="table" id="resultsTable">
    <thead>
        <tr>
            <th id="id">Movie ID</th>
            <th id="poster">Poster</th>
            <th id="title">Title</th>
            <th id="description">Description</th>
            <th id="theatrical">Theatrical Release Date</th>
            <th id="digital">Digital Release Date</th>
            <th id="physical">Physical Release Date</th>
            <th id="button"></th>
        </tr>
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

