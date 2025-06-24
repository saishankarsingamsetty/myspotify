<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>${title}</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }

        .back-button {
            display: inline-block;
            margin-bottom: 20px;
            padding: 10px 16px;
            background-color: #1DB954;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }

        .back-button:hover {
            background-color: #169c44;
        }

        h2 {
            text-align: center;
            color: #1DB954;
            margin-bottom: 30px;
        }

        .message {
            text-align: center;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 5px;
            font-size: 15px;
            width: 60%;
            margin-left: auto;
            margin-right: auto;
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

        .track-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
        }

        .track-card {
            background-color: #fff;
            border-radius: 8px;
            border: 1px solid #ddd;
            padding: 15px;
            width: 250px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.05);
            text-align: center;
            transition: transform 0.2s;
        }

        .track-card:hover {
            transform: translateY(-5px);
        }

        .track-card img {
            border-radius: 6px;
            width: 100%;
            height: auto;
        }

        .track-card p {
            margin: 10px 0 5px;
            color: #333;
        }

        .duration {
            font-size: 13px;
            color: #555;
            margin-bottom: 5px;
        }

        .spotify-link {
            display: inline-block;
            margin-top: 5px;
            text-decoration: none;
            color: #1DB954;
            font-weight: bold;
        }

        .spotify-link:hover {
            text-decoration: underline;
        }

        .remove-button {
            margin-top: 10px;
            background-color: #e74c3c;
            color: white;
            border: none;
            padding: 8px 12px;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
        }

        .remove-button:hover {
            background-color: #c0392b;
        }
    </style>
</head>
<body>

    <a class="back-button" href="/profile">🔙 Back to Search</a>

    <!-- Success or Error Flash Message Display -->
    <c:if test="${not empty message}">
        <div class="message success">${message}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="message error">${error}</div>
    </c:if>

    <h2>${title}</h2>

    <div class="track-container">
        <c:forEach items="${results}" var="track">
            <div class="track-card">
                <img src="${track.imageUrl}" alt="Album Cover"/>
                <p><strong>${track.name}</strong> (${track.type})</p>
                <p class="duration">⏱ ${track.formattedDuration}</p>
                <p><a class="spotify-link" href="${track.externalUrl}" target="_blank">🎧 Listen on Spotify</a></p>

                <form action="removeTrack" method="post">
                    <input type="hidden" name="trackId" value="${track.id}" />
                    <button type="submit" class="remove-button">🗑️ Remove</button>
                </form>
            </div>
        </c:forEach>
    </div>

</body>
</html>
