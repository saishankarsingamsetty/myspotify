<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Spotify Track Search</title>
</head>
<body>
    <h2>Search Spotify Tracks</h2>
    <form action="searchtracks" method="get">
        <input type="text" name="query" placeholder="Enter artist or song name" required/>
        <button type="submit">Search</button>
    </form>
</body>
</html>
