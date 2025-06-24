<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head> 
    <title>User Profile</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f2f2f2;
            margin: 0;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: #1DB954;
        }

        .search-container {
            display: flex;
            justify-content: center;
            margin: 20px 0;
        }

        input[type="text"] {
            padding: 10px;
            width: 300px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        button {
            padding: 10px 16px;
            background-color: #1DB954;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-left: 5px;
        }

        button:hover {
            background-color: #169c44;
        }

        .link-container {
            text-align: center;
            margin-top: 10px;
        }

        .link-container a {
            color: #1DB954;
            font-weight: bold;
            text-decoration: none;
        }

        .link-container a:hover {
            text-decoration: underline;
        }

        .track-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 15px;
        }

        .track-card {
            background-color: white;
            border: 1px solid #ddd;
            border-radius: 8px;
            padding: 10px;
            width: 250px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            text-align: center;
        }

        .track-card img {
            width: 100%;
            border-radius: 6px;
        }

        .track-card p {
            margin: 10px 0 5px;
        }

        .spotify-link {
            text-decoration: none;
            color: #1DB954;
            font-weight: bold;
        }

        .spotify-link:hover {
            text-decoration: underline;
        }

        .like-button {
            background: none;
            border: none;
            font-size: 20px;
            cursor: pointer;
            color: #1DB954;
        }

        .like-button:hover {
            transform: scale(1.1);
        }

        .message-box {
            text-align: center;
            padding: 10px;
            margin-bottom: 10px;
            border-radius: 5px;
        }

        .success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        .duration {
            font-size: 13px;
            color: #555;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>

    <h2>Welcome, ${name}!</h2>

    <!-- Success / Error messages -->
    <c:if test="${not empty message}">
        <div class="message-box success">${message}</div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="message-box error">${error}</div>
    </c:if>

    <!-- Search bar -->
    <div class="search-container">
        <form action="profile" method="get">
            <input type="text" name="query" placeholder="Search tracks..." value="${searchQuery}" required />
            <button type="submit">Search</button>
        </form>
    </div>

    <!-- Saved Tracks link -->
    <div class="link-container">
        <a href="/saved-tracks">🎵 View Your Saved Tracks</a>
    </div>

    <!-- Search Results -->
    <c:if test="${not empty results}">
        <h3 style="text-align: center;">Top Tracks for "<c:out value="${searchQuery}"/>"</h3>
        <div class="track-container">
            <c:forEach items="${results}" var="track">
                <div class="track-card">
                    <img src="${track.imageUrl}" alt="Album Cover"/>
                    <p><strong>${track.name}</strong> (${track.type})</p>
                    <p class="duration">⏱ ${track.formattedDuration}</p>
                    <p><a class="spotify-link" href="${track.externalUrl}" target="_blank">Listen on Spotify</a></p>

                    <form action="likeTrack" method="post" style="margin-top: 5px;">
                        <input type="hidden" name="trackId" value="${track.id}" />
                        <button class="like-button" title="Like this track">❤️</button>
                    </form>
                </div>
            </c:forEach>
        </div>
    </c:if>

</body>
</html>
