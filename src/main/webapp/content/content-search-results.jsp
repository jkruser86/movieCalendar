<%@include file="../taglib.jsp"%>
<h3>Search Results</h3>
<br/>
<table class="table">
    <thead>
        <tr>
            <th>Poster</th>
            <th>Title</th>
            <th>Description</th>
            <th>Theatrical Release Date</th>
            <th>Digital Release Date</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="movie" items="${movies}">
            <tr>
                <td><img src="https://image.tmdb.org/t/p/w185_and_h278_bestv2${movie.posterPath}"></td>
                <td>${movie.title}</td>
                <td>${movie.overview}</td>
                <td>"unknown"</td>
                <td>"unknown"</td>
            </tr>
        </c:forEach>
    </tbody>
</table>