<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Spotify Search Results</title>
</head>
<body>
<h2>Top 5 Tracks (Search Result)</h2>

<c:forEach items="${results}" var="item">
    <div style="margin: 15px; padding: 10px; border: 1px solid #ccc;">
        <img src="${item.imageUrl}" alt="Album Cover" width="100" height="100"/>
        <p><strong>${item.name}</strong> (${item.type})</p>
        <p><a href="${item.externalUrl}" target="_blank">Listen on Spotify</a></p>
    </div>
</c:forEach>

</body>
</html>
